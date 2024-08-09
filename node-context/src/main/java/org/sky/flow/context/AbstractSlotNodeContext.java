package org.sky.flow.context;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Slot Context
 *
 * @author yj
 */
public abstract class AbstractSlotNodeContext extends AbstractNodeContext implements NodeContext {

    /** slot node使用 */
    private AtomicInteger slot;

    protected AbstractSlotNodeContext(GlobalContext globalContext) {
        super(globalContext);
    }

    /**
     * description: slot context设置
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/10 11:58   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/10 11:58
     * @param slot slot
     */
    public void slotSetting(int slot) {
        this.slot = new AtomicInteger(slot);
    }

    /**
     * description: 减1 并且返回
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/10 12:02   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/10 12:02
     * @return int
     */
    public int decrementAndGet() {
        return slot.decrementAndGet();
    }
}
