package org.sky.flow.box;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.collect.Maps;

import org.sky.flow.context.Context;
import org.sky.flow.context.NodeContext;

/**
 * Node Context Box
 *
 * @author yj
 */
public class NodeContextBox implements ContextBox {

    /** Node 盒子 */
    private final Map<String, Map<String, Context>> nodeBox = Maps.newConcurrentMap();

    /**
     * description: node盒子初始化
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/10 13:23   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/10 13:23
     * @param context context
     */
    public void init(Context context) {
        nodeBox.put(context.instanceId(), new ConcurrentHashMap<>(16));
    }

    @Override
    public void put(Context context) {
        nodeBox.get(context.instanceId()).put(key(context), context);
    }

    /**
     * description: node context
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/10 15:14   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/10 15:14
     * @param context node context
     * @return org.sky.flow.context.Context
     */
    public Context putIfAbsent(Context context) {
        Context nodeContext = nodeBox.get(context.instanceId()).putIfAbsent(key(context), context);
        return Objects.requireNonNullElse(nodeContext, context);
    }

    @Override
    public Context get(String key) {
        String[] keys = key.split("@");
        return nodeBox.get(keys[0]).get(keys[1]);
    }

    /**
     * description: get(key) 性能缓慢
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/10 14:51   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/10 14:51
     * @param key instance
     * @param node node
     * @return org.sky.flow.context.Context
     */
    public Context get(String key, String node) {
        return nodeBox.get(key).get(node);
    }

    /**
     * description: key 实例ID-Node ID
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/10 12:08   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/10 12:08
     * @param context context
     * @return java.lang.String
     */
    private String key(Context context) {
        return ((NodeContext) context).nodeId();
    }
}
