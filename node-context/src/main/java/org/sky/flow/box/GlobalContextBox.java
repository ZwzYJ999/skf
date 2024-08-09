package org.sky.flow.box;

import java.util.Map;

import com.google.common.collect.Maps;

import org.sky.flow.context.Context;

/**
 * Context Box
 *
 * @author yj
 */
public class GlobalContextBox implements ContextBox {

    /** 全局盒子 */
    private static final Map<String, Context> GLOBAL_BOX = Maps.newConcurrentMap();

    @Override
    public void put(Context context) {
        GLOBAL_BOX.put(context.instanceId(), context);
    }

    @Override
    public Context get(String key) {
        return GLOBAL_BOX.get(key);
    }
}
