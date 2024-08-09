package org.sky.flow.node.definition.gateway;

import org.sky.flow.node.definition.AbstractNode;
import org.sky.flow.node.definition.Node;
import org.sky.flow.node.definition.enums.NodeType;

/**
 * Gateway Node
 *
 * @author yj
 */
public abstract class AbstractGatewayNode extends AbstractNode implements Node {
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
     * @author YJ
     * @date 2023/10/28 12:12
     * @param id node id
     * @param name node name
     * @param type node type
     */
    protected AbstractGatewayNode(String id, String name, NodeType type) {
        super(id, name, type);
    }
}
