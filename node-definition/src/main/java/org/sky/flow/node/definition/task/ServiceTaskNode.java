package org.sky.flow.node.definition.task;

import org.sky.flow.node.definition.Node;
import org.sky.flow.node.definition.enums.NodeType;

/**
 * Service Task Node
 *
 * @author yj
 */
public class ServiceTaskNode extends AbstractTaskNode implements Node {

    /**
     * description: ID Name Generate
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 14:31   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 14:31
     * @param id id
     * @param name name
     */
    public ServiceTaskNode(String id, String name) {
        super(id, name, NodeType.SERVICE_TASK);
    }
}
