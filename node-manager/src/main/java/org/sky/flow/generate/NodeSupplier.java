package org.sky.flow.generate;

import org.sky.flow.node.definition.Node;

/**
 * Node Supplier
 *
 * @author yj
 */
@FunctionalInterface
public interface NodeSupplier {

    /**
     * description: Get Node
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 16:13   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 16:13
     * @param id ID
     * @param name Name
     * @return org.sky.flow.node.definition.Node
     */
    Node get(String id, String name);
}
