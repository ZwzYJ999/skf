package org.sky.flow.manager;

/**
 * Node Manager
 *
 * @author yj
 */
public interface NodeManager {

    /**
     * description: node generate
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 17:43   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 17:43
     * @return org.sky.flow.manager.NodeAttributeBuilder
     */
    NodeAttributeBuilder generate();
}
