package org.sky.flow.node.definition.gateway;

import org.sky.flow.node.definition.Node;
import org.sky.flow.node.definition.enums.NodeType;

/**
 * 排他网关
 *
 * @author yj
 */
public class ExclusiveGatewayNode extends AbstractGatewayNode implements Node {

    /**
     * description: generate
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/10 15:34   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/10 15:34
     * @param id id
     * @param name name
     */
    public ExclusiveGatewayNode(String id, String name) {
        super(id, name, NodeType.EXCLUSIVE);
    }
}
