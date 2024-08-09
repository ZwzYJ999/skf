package org.sky.flow.box;

import org.sky.flow.context.Context;

/**
 * 上下文盒子
 *
 * @author yj
 */
public interface ContextBox {

    /**
     * description: put context
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 16:38   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/9 16:38
     * @param context 流程上下文
     */
    void put(Context context);

    /**
     * description: 通过key获取context
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/10 12:10   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/10 12:10
     * @param key key
     * @return org.sky.flow.context.Context
     */
    Context get(String key);
}
