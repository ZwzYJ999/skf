package org.sky.flow.delegate.slot;

import org.sky.flow.context.AbstractSlotNodeContext;
import org.sky.flow.context.Context;
import org.sky.flow.delegate.AbstractDelegateInvoke;
import org.sky.flow.delegate.Delegate;

/**
 * Slot Delegate
 *
 * @author yj
 */
public class SlotDelegate extends AbstractDelegateInvoke implements Delegate {

    /** custom delegate */
    private Delegate delegate;

    /**
     * description: 设置builder追加的delegate
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 17:11   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/9 17:11
     * @param delegate custom delegate
     */
    public void setDelegate(Delegate delegate) {
        this.delegate = delegate;
    }

    @Override
    protected void invoke(Context context) {
        if (delegate != null) {
            delegate.execute(context);
        }
    }

    @Override
    public boolean specificStop(Context context) {
        // context减少
        AbstractSlotNodeContext abstractSlotNodeContext = (AbstractSlotNodeContext) context;
        return abstractSlotNodeContext.decrementAndGet() > 0;
    }
}
