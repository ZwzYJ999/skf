package org.sky.flow.combination;

import java.util.List;

import org.sky.flow.combination.specific.SpecificCombinationConvert;
import org.sky.flow.context.Context;
import org.sky.flow.delegate.Delegate;
import org.sky.flow.listener.NodeListener;
import org.sky.flow.node.definition.Node;
import org.sky.flow.ql.express.ExpressionHandler;

/**
 * Node Combination
 *
 * @author yj
 */
public abstract class AbstractNodeCombination implements NodeCombination {

    /** 表达式执行 */
    protected ExpressionHandler expressionHandler;

    /**
     * description: create 抽象
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/10/28 11:51   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/10/28 11:51
     * @param expressionHandler expression handler
     */
    protected AbstractNodeCombination(ExpressionHandler expressionHandler) {
        this.expressionHandler = expressionHandler;
    }

    /**
     * description: 增强combination的实现
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 15:55   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/9 15:55
     * @param node node
     * @return org.sky.flow.combination.NodeCombination
     */
    protected NodeCombination enhanceCombination(Node node) {
        NodeCombination nodeCombination = SpecificCombinationConvert.apply(expressionHandler, node);
        return nodeCombination == null ? this : nodeCombination;
    }

    /**
     * description: target node get
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 14:44   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/9 14:44
     * @return java.util.List<org.sky.flow.combination.NodeCombination>
     */
    public abstract List<NodeCombination> targetNodeGet();

    /**
     * description: 获取Delegate
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 14:45   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/9 14:45
     * @return org.sky.flow.delegate.Delegate
     */
    public abstract Delegate delegateGet();

    /**
     * description: 是否异步
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 15:10   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/9 15:10
     * @return boolean
     */
    public abstract boolean isParallel();

    /**
     * description: 当前node被作为另外一个node的target时的引用处理
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 16:10   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/9 16:10
     */
    public void referencedDeploy() {}

    /**
     * description: 是否存在表达式执行
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/10 17:33   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/10 17:33
     * @return boolean
     */
    public abstract boolean isExpression();

    /**
     * description: 表达式执行
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/10 17:39   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/10 17:39
     * @param relations source node id + target node id
     * @param context context
     * @return boolean
     */
    public abstract boolean expressionExecute(String relations, Context context);

    /**
     * description: 获取Before Listener
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/21 18:03   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/21 18:03
     * @return org.sky.flow.listener.NodeListener
     */
    public abstract NodeListener beforeListener();

    /**
     * description: 获取After Listener
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/21 18:04   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/21 18:04
     * @return org.sky.flow.listener.NodeListener
     */
    public abstract NodeListener afterListener();
}
