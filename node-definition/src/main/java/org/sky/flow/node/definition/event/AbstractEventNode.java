package org.sky.flow.node.definition.event;

import org.sky.flow.node.definition.AbstractNode;
import org.sky.flow.node.definition.Node;
import org.sky.flow.node.definition.enums.NodeType;

/**
 * Abstract Event Node
 *
 * @author yj
 */
public abstract class AbstractEventNode extends AbstractNode implements Node {
    protected AbstractEventNode(String id, String name, NodeType type) {
        super(id, name, type);
    }

    protected AbstractEventNode(NodeType type) {
        super(type);
    }
}
