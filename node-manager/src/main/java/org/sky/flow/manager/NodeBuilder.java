package org.sky.flow.manager;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.sky.flow.combination.NodeCombination;
import org.sky.flow.combination.NodeCombinationInvoke;
import org.sky.flow.delegate.Delegate;
import org.sky.flow.exception.NodeManagerBuilderException;
import org.sky.flow.generate.NodeGenerate;
import org.sky.flow.listener.NodeListener;
import org.sky.flow.node.definition.Node;
import org.sky.flow.node.definition.enums.NodeType;
import org.sky.flow.ql.express.ExpressionHandler;

/**
 * 创建Node Builder
 *
 * @author yj
 */
public class NodeBuilder implements NodeAttributeBuilder {

    /** 表达式执行引擎 */
    private final ExpressionHandler expressionHandler;

    /** ID */
    private String id;

    /** Name */
    private String name;

    /** Delegate */
    private Delegate delegate;

    /** Node Type */
    private NodeType nodeType;

    /** target node */
    private final List<NodeCombination> targetNodes = Lists.newArrayList();

    /** Node 属性 */
    private Map<String, Object> properties = Maps.newHashMap();

    /** before listener */
    private NodeListener nodeBeforeListener;

    /** after listener */
    private NodeListener nodeAfterListener;

    /**
     * create node builder
     *
     * @param expressionHandler exp
     */
    public NodeBuilder(ExpressionHandler expressionHandler) {
        this.expressionHandler = expressionHandler;
    }

    @Override
    public NodeAttributeBuilder id(String id) {
        this.id = id;
        return this;
    }

    @Override
    public NodeAttributeBuilder name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public NodeAttributeBuilder delegate(Delegate delegate) {
        this.delegate = delegate;
        return this;
    }

    @Override
    public NodeAttributeBuilder beforeListener(NodeListener nodeBeforeListener) {
        this.nodeBeforeListener = nodeBeforeListener;
        return this;
    }

    @Override
    public NodeAttributeBuilder afterListener(NodeListener nodeAfterListener) {
        this.nodeAfterListener = nodeAfterListener;
        return this;
    }

    @Override
    public NodeAttributeBuilder type(NodeType nodeType) {
        this.nodeType = nodeType;
        return this;
    }

    @Override
    public NodeAttributeBuilder properties(Map<String, Object> properties) {
        if (Objects.isNull(properties)) {
            return this;
        }
        this.properties = properties;
        return this;
    }

    @Override
    public NodeAttributeBuilder properties(String key, Object value) {
        this.properties.put(key, value);
        return this;
    }

    @Override
    public NodeCombination build() {
        if (nodeType == null) {
            throw new NodeManagerBuilderException("Node Type Can`t Be Null. ");
        }

        if (targetNodes.stream().anyMatch(Objects::isNull)) {
            throw new NodeManagerBuilderException("Target Node Can`t Be Null. ");
        }

        NodeCombinationInvoke nodeCombinationInvoke = new NodeCombinationInvoke(expressionHandler);

        Node node = NodeGenerate.generate(id, name, nodeType);
        return nodeCombinationInvoke
                .holder(node)
                .delegate(delegate)
                .addTargetNodes(targetNodes)
                .beforeListener(nodeBeforeListener)
                .afterListener(nodeAfterListener)
                .properties(properties);
    }
}
