package org.sky.flow.engine.strategy;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import org.sky.flow.combination.AbstractNodeCombination;
import org.sky.flow.combination.AbstractProcessCombinationInvoke;
import org.sky.flow.combination.NodeCombination;
import org.sky.flow.combination.ProcessCombination;
import org.sky.flow.combination.specific.SlotNodeCombinationInvoke;
import org.sky.flow.context.Context;
import org.sky.flow.context.ExtendNodeContext;
import org.sky.flow.delegate.Delegate;
import org.sky.flow.engine.exception.EngineException;
import org.sky.flow.listener.NodeListener;

/**
 * Node 节点执行
 *
 * @author yj
 */
public abstract class AbstractNodeProcessEngine extends AbstractContextProcessEngine {

    /**
     * description: 节点执行
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 14:31   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/9 14:31
     * @param nodeCombination start event
     * @param processCombination 流程
     * @param contextMap 流程上下文
     * @return global context
     */
    protected Context execute(
            ProcessCombination processCombination,
            NodeCombination nodeCombination,
            ConcurrentHashMap<String, Object> contextMap) {
        // 实例中的context
        Context context = generateContext(processCombination, contextMap);

        doExecute(nodeCombination, context);

        return context;
    }

    /**
     * description: delegate context use
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/18 18:00   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/18 18:00
     * @param processCombination process
     * @param context delegate context use
     * @return org.sky.flow.context.Context
     */
    protected Context execute(
            ProcessCombination processCombination, ConcurrentHashMap<String, Object> context) {
        // 执行节点.
        AbstractProcessCombinationInvoke invoke =
                (AbstractProcessCombinationInvoke) processCombination;
        List<NodeCombination> startEventNode = invoke.startEventNodeGet();

        return execute(processCombination, startEventNode.get(0), context);
    }

    /**
     * description: 节点执行
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 16:46   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/9 16:46
     * @param nodeCombination invoke
     * @param context 上下文信息
     */
    protected void doExecute(NodeCombination nodeCombination, Context context) {
        // 汇聚网关处理
        AbstractNodeCombination nodeCombinationInvoke = (AbstractNodeCombination) nodeCombination;

        // 获取node context
        ExtendNodeContext nodeContext = init(nodeCombination, context);

        // Get Delegate
        Delegate delegate = nodeCombinationInvoke.delegateGet();

        // 是否停止
        if (delegate.specificStop(nodeContext)) {
            return;
        }

        // 执行
        triggerListener(nodeCombinationInvoke.beforeListener(), nodeContext);
        delegate.execute(nodeContext);
        triggerListener(nodeCombinationInvoke.afterListener(), nodeContext);

        // 是否需要执行表达式
        List<NodeCombination> targetNodes = nodeCombinationInvoke.targetNodeGet();
        String sourceNodeId = nodeCombination.nodeId();
        if (targetNodes == null) {
            return;
        }

        boolean expressionExist = nodeCombinationInvoke.isExpression();

        // 存在表达式加入filter
        if (expressionExist) {
            // 过滤完成后得到的节点
            List<NodeCombination> filters =
                    targetNodes.stream()
                            .filter(
                                    targetNode ->
                                            nodeCombinationInvoke.expressionExecute(
                                                    sourceNodeId.concat(targetNode.nodeId()),
                                                    context))
                            .toList();

            // 只能有一个出口
            if (filters.size() > 1) {
                throw new EngineException("Exclusive gateway cannot have multiple target nodes.");
            }

            if (filters.isEmpty()) {
                throw new EngineException("Exclusive gateway must have a target node.");
            }

            // 将过滤完成的数组赋予target node
            targetNodes = filters;
        }

        // 继续向下执行
        nextProcess(targetNodes, nodeCombinationInvoke.isParallel(), context);
    }

    /**
     * description: 触发listener
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/22 11:09   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/22 11:09
     * @param nodeListener node listener
     * @param nodeContext node context
     */
    protected abstract void triggerListener(
            NodeListener nodeListener, ExtendNodeContext nodeContext);

    /**
     * description: 执行下一个节点组
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/18 15:11   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/18 15:11
     * @param targetNodes 下一个节点组
     * @param isAsync 同步异步执行
     * @param context process上下文
     */
    protected void nextProcess(
            List<NodeCombination> targetNodes, boolean isAsync, Context context) {
        Stream<NodeCombination> stream;
        if (isAsync) {
            stream = targetNodes.parallelStream();
        } else {
            stream = targetNodes.stream();
        }

        stream.forEach(targetNode -> doExecute(targetNode, context));
    }

    /**
     * description: 初始化node context
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/10 11:39   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/10 11:39
     * @param nodeCombination node 组合对象
     * @param context 全局上下文
     * @return org.sky.flow.context.ExtendNodeContext
     */
    private ExtendNodeContext init(NodeCombination nodeCombination, Context context) {
        // 声明一个node context
        ExtendNodeContext nodeContext = (ExtendNodeContext) context.nodeContext();
        nodeContext.init(
                nodeCombination.name(),
                nodeCombination.nodeId(),
                nodeCombination.type(),
                nodeCombination.properties());

        // 放入缓存. 作为多个node的target节点存在缓存.
        ExtendNodeContext cacheNodeContext = nodeContextPut(nodeContext);

        // 如果不一样. 就直接返回缓存对象
        if (nodeContext.equals(cacheNodeContext)) {
            if (nodeCombination instanceof SlotNodeCombinationInvoke) {
                nodeContext.slotSetting(((SlotNodeCombinationInvoke) nodeCombination).slot());
            }

        } else {
            return cacheNodeContext;
        }

        return nodeContext;
    }
}
