package org.sky.flow.delegate;

import org.sky.flow.context.Context;

/**
 * 执行代码
 *
 * @author yj
 */
public abstract class AbstractDelegateInvoke implements Delegate {

    @Override
    public void execute(Context context) {
        invoke(context);
    }

    /**
     * description: 子类实现invoke方法.
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 16:45   ******              YJ                Create
     *
     * </pre>
     *
     * @param context context上下文
     * @author YJ
     * @date 2023/8/8 16:45
     */
    protected abstract void invoke(Context context);

    @Override
    public boolean specificStop(Context context) {
        return false;
    }
}
