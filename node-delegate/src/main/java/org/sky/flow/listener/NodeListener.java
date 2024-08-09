package org.sky.flow.listener;

import org.sky.flow.context.Context;

/**
 * Node Listener
 *
 * @author yj
 */
public interface NodeListener {
    /**
     * description: listener
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/21 17:53   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/21 17:53
     * @param context context
     */
    void listener(Context context);
}
