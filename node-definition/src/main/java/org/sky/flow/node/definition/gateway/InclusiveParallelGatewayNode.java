package org.sky.flow.node.definition.gateway;

import org.sky.flow.node.definition.Node;
import org.sky.flow.node.definition.enums.NodeType;

/**
 * 包含&并行网关
 *
 * @author YJ
 */
public class InclusiveParallelGatewayNode extends AbstractGatewayNode implements Node {
    /**
     * description: create node
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/10/28 12:12   ******              YJ                Create
     *
     * </pre>
     *
     * @param id node id
     * @param name node name
     * @author YJ
     * @date 2023/10/28 12:12
     */
    public InclusiveParallelGatewayNode(String id, String name) {
        super(id, name, NodeType.INCLUSIVE_PARALLEL);
    }

    @Override
    public boolean isParallel() {
        return true;
    }
}
