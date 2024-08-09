package org.sky.flow.manager;

import java.util.Map;

import org.sky.flow.node.definition.enums.NodeType;

/**
 * Node Manager
 *
 * @author yj
 */
public interface NodeAttributeBuilder extends NodeGenerateBuilder {

    /**
     * description: Setting Id
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 15:24   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 15:24
     * @param id id
     * @return builder
     */
    NodeAttributeBuilder id(String id);

    /**
     * description: setting name
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 15:24   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 15:24
     * @param name name
     * @return builder
     */
    NodeAttributeBuilder name(String name);

    /**
     * description: setting node type
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 15:35   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 15:35
     * @param nodeType node类型
     * @return builder
     */
    NodeAttributeBuilder type(NodeType nodeType);

    /**
     * description: properties. 会覆盖掉原本的properties属性
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/11 14:38   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/11 14:38
     * @param properties node properties
     * @return org.sky.flow.manager.NodeAttributeBuilder
     */
    NodeAttributeBuilder properties(Map<String, Object> properties);

    /**
     * description: 单个属性设置
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/11 14:48   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/11 14:48
     * @param key key
     * @param value value
     * @return org.sky.flow.manager.NodeAttributeBuilder
     */
    NodeAttributeBuilder properties(String key, Object value);
}
