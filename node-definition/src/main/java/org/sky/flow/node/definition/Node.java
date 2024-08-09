package org.sky.flow.node.definition;

import org.sky.flow.node.definition.enums.NodeType;

/**
 * Node 顶层接口
 *
 * @author yj
 */
public interface Node {

    /**
     * description: node type
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 17:52   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 17:52
     * @return type
     */
    NodeType type();

    /**
     * description: ID
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 18:06   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 18:06
     * @return java.lang.String
     */
    String id();

    /**
     * description: name
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 17:41   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/9 17:41
     * @return java.lang.String
     */
    String name();

    /**
     * description: 是否异步
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/10/28 12:22   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/10/28 12:22
     * @return boolean
     */
    boolean isParallel();
}
