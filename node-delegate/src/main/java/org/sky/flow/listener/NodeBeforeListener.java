package org.sky.flow.listener;

import org.sky.flow.context.Context;

/**
 * Node Before Listener
 *
 * @author yj
 */
public interface NodeBeforeListener extends NodeListener {
    /**
     * description: before listener
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/21 17:54   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/21 17:54
     * @param context context
     */
    @Override
    default void listener(Context context) {
        listenerBefore(context);
    }

    /**
     * description: listener before
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/21 17:56   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/21 17:56
     * @param context context
     */
    void listenerBefore(Context context);
}
