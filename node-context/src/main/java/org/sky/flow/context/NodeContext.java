package org.sky.flow.context;

import java.util.Map;

/**
 * Node Context
 *
 * @author yj
 */
public interface NodeContext extends Context {

    /**
     * description: 当前Node名称
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 17:28   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/9 17:28
     * @return java.lang.String
     */
    String nodeName();

    /**
     * description: node ID
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 17:43   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/9 17:43
     * @return java.lang.String
     */
    String nodeId();

    /**
     * description: node type
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 17:53   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/9 17:53
     * @return java.lang.String
     */
    String nodeType();

    /**
     * description: 通过单个Key获取属性
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/11 14:51   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/11 14:51
     * @param key key
     * @return java.lang.Object
     */
    Object properties(String key);

    /**
     * description: 获取properties map
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/11 14:52   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/11 14:52
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    Map<String, Object> properties();
}
