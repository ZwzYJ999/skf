package org.sky.flow.combination.specific;

import java.util.concurrent.atomic.AtomicInteger;

import org.sky.flow.combination.NodeCombination;
import org.sky.flow.combination.NodeCombinationInvoke;
import org.sky.flow.delegate.Delegate;
import org.sky.flow.delegate.slot.SlotDelegate;
import org.sky.flow.node.definition.Node;
import org.sky.flow.ql.express.ExpressionHandler;

/**
 * slot node
 *
 * @author yj
 */
public class SlotNodeCombinationInvoke extends NodeCombinationInvoke {

    /** 只有slot为0才能执行 */
    private final AtomicInteger slot = new AtomicInteger(0);

    /**
     * description: slot 数量
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/10 12:00   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/10 12:00
     * @return int
     */
    public int slot() {
        return slot.get();
    }

    /**
     * description: slot invoke
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 17:07   ******              YJ                Create
     *
     * </pre>
     *
     * @param expressionHandler 表达式执行引擎
     * @author YJ
     * @date 2023/8/9 17:07
     */
    public SlotNodeCombinationInvoke(ExpressionHandler expressionHandler) {
        super(expressionHandler);
        // 创建新的slot
        super.delegate(new SlotDelegate());
    }

    @Override
    public NodeCombination holder(Node node) {
        this.node = node;
        return this;
    }

    @Override
    public void referencedDeploy() {
        slot.incrementAndGet();
    }

    @Override
    public NodeCombination delegate(Delegate delegate) {
        SlotDelegate slotDelegate = (SlotDelegate) this.delegateGet();
        slotDelegate.setDelegate(delegate);
        return this;
    }
}
