package org.sky.flow.node.definition.task;

import org.sky.flow.node.definition.AbstractNode;
import org.sky.flow.node.definition.Node;
import org.sky.flow.node.definition.enums.NodeType;

/**
 * Task Node
 *
 * @author yj
 */
public abstract class AbstractTaskNode extends AbstractNode implements Node {

    /**
     * 抽象类
     *
     * @param id node id
     * @param name name
     * @param type type
     */
    protected AbstractTaskNode(String id, String name, NodeType type) {
        super(id, name, type);
    }
}
