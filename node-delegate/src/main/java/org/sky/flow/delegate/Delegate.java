package org.sky.flow.delegate;

import org.sky.flow.context.Context;

/**
 * Delegate Interface
 *
 * @author yj
 */
public interface Delegate {

    /**
     * description: 执行delegate
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 16:44   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 16:44
     * @param context context
     */
    void execute(Context context);

    /**
     * description: 终止
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 15:15   ******              YJ                Create
     *
     * </pre>
     *
     * @param context Node context
     * @author YJ
     * @date 2023/8/9 15:15
     * @return boolean
     */
    boolean specificStop(Context context);
}
