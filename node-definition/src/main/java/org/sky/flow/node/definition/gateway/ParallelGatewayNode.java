package org.sky.flow.node.definition.gateway;

import org.sky.flow.node.definition.Node;
import org.sky.flow.node.definition.enums.NodeType;

/**
 * 并行网关节点
 *
 * @author yj
 */
public class ParallelGatewayNode extends AbstractGatewayNode implements Node {

    /**
     * description: 并行网关
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 16:18   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 16:18
     * @param id id
     * @param name name
     */
    public ParallelGatewayNode(String id, String name) {
        super(id, name, NodeType.PARALLEL);
    }

    @Override
    public boolean isParallel() {
        return true;
    }
}
