package org.sky.flow.engine.strategy;

import java.util.concurrent.ConcurrentHashMap;

import org.sky.flow.box.GlobalContextBox;
import org.sky.flow.box.NodeContextBox;
import org.sky.flow.combination.ProcessCombination;
import org.sky.flow.context.Context;
import org.sky.flow.context.DefaultDelegateContext;
import org.sky.flow.context.ExtendNodeContext;
import org.sky.flow.context.GlobalContext;
import org.sky.flow.store.ProcessStoreManagerImpl;

/**
 * Context
 *
 * @author yj
 */
public abstract class AbstractContextProcessEngine extends ProcessStoreManagerImpl {

    /** 上下文盒子 */
    private static final GlobalContextBox BOX = new GlobalContextBox();

    /** Node 上下文盒子 */
    private static final NodeContextBox NODE_BOX = new NodeContextBox();

    /**
     * description: 创建流程上下文
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 16:46   ******              YJ                Create
     *
     * </pre>
     *
     * @param processCombination 流程组合对象
     * @param context 上下文map
     * @return org.sky.flow.context.Context
     * @author YJ
     * @date 2023/8/9 16:46
     */
    protected Context generateContext(
            ProcessCombination processCombination, ConcurrentHashMap<String, Object> context) {
        GlobalContext globalContext =
                new GlobalContext(
                        processCombination.flowId(),
                        processCombination.flowName(),
                        new DefaultDelegateContext(context));
        // 放入盒子
        BOX.put(globalContext);
        // node初始化
        NODE_BOX.init(globalContext);

        return globalContext;
    }

    /**
     * description: node context put
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/10 13:31   ******              YJ                Create
     *
     * </pre>
     *
     * @param context node context
     * @author YJ
     * @date 2023/8/10 13:31
     */
    protected ExtendNodeContext nodeContextPut(Context context) {
        return (ExtendNodeContext) NODE_BOX.putIfAbsent(context);
    }
}
