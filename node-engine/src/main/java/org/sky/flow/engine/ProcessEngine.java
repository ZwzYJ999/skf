package org.sky.flow.engine;

import java.util.concurrent.ConcurrentHashMap;

import org.sky.flow.context.Context;

/**
 * 流程引擎
 *
 * @author yj
 */
public interface ProcessEngine {

    /**
     * description: 启动流程
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 17:22   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 17:22
     * @param id process id
     * @return context
     */
    Context startProcessById(String id);

    /**
     * description: start process by id
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/18 17:53   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/18 17:53
     * @param id id
     * @param context 作用到delegate context中
     * @return org.sky.flow.context.Context
     */
    Context startProcessById(String id, ConcurrentHashMap<String, Object> context);
}
