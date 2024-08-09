package org.sky.flow.combination;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.apache.commons.lang3.StringUtils;
import org.sky.flow.context.Context;
import org.sky.flow.context.DelegateContext;
import org.sky.flow.delegate.Delegate;
import org.sky.flow.exception.NodeManagerBuilderException;
import org.sky.flow.listener.NodeListener;
import org.sky.flow.node.definition.Node;
import org.sky.flow.node.definition.enums.NodeType;
import org.sky.flow.ql.express.ExpressionHandler;

/**
 * Node Combination Invoke
 *
 * @author yj
 */
public class NodeCombinationInvoke extends AbstractNodeCombination implements NodeCombination {

    /** Node对象 */
    protected Node node;

    /** 执行对象 */
    protected Delegate delegate;

    /** target node */
    protected final List<NodeCombination> targetNodes = Lists.newArrayList();

    /** 表达式存储 */
    private final Map<String, String> expressionMap = Maps.newHashMap();

    /** Node 属性 */
    private Map<String, Object> properties = Maps.newHashMap();

    /** before listener */
    private NodeListener nodeBeforeListener;

    /** after listener */
    private NodeListener nodeAfterListener;

    /**
     * description: create combination invoke
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/10/28 11:50   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/10/28 11:50
     * @param expressionHandler expression handler
     */
    public NodeCombinationInvoke(ExpressionHandler expressionHandler) {
        super(expressionHandler);
    }

    @Override
    public NodeCombination holder(Node node) {
        this.node = node;
        return enhanceCombination(node);
    }

    @Override
    public String type() {
        return node.type().name();
    }

    @Override
    public String nodeId() {
        return node.id();
    }

    @Override
    public String name() {
        return node.name();
    }

    @Override
    public Map<String, Object> properties() {
        return properties;
    }

    @Override
    public NodeCombination delegate(Delegate delegate) {
        if (delegate == null) {
            // this.delegate 如果不是Null. 就赋值给delegate
            delegate =
                    Objects.requireNonNullElseGet(
                            this.delegate,
                            () ->
                                    new Delegate() {
                                        @Override
                                        public void execute(Context context) {
                                            // no impl
                                        }

                                        @Override
                                        public boolean specificStop(Context context) {
                                            return false;
                                        }
                                    });
        }
        this.delegate = delegate;
        return this;
    }

    @Override
    public NodeCombination addTargetNode(NodeCombination targetNode) {
        if (NodeType.EXCLUSIVE.name().equals(this.type())) {
            throw new NodeManagerBuilderException(
                    "Exclusive gateway must set expression properties.");
        }
        doAddTargetNode(targetNode);
        return this;
    }

    /**
     * description: 执行target node设置
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/10/28 15:53   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/10/28 15:53
     * @param targetNode target node
     */
    private void doAddTargetNode(NodeCombination targetNode) {
        if (this.nodeId().equals(targetNode.nodeId())) {
            throw new NodeManagerBuilderException(
                    "The source node and target node cannot be the same. Node ID Is: "
                            + targetNode.nodeId());
        }

        ((AbstractNodeCombination) targetNode).referencedDeploy();
        this.targetNodes.add(targetNode);
    }

    @Override
    public NodeCombination addTargetNode(NodeCombination targetNode, String expression) {
        // 只有排他网关可以使用表达式功能，判断是否继续往下执行
        if (!NodeType.EXCLUSIVE.name().equals(this.type())) {
            throw new NodeManagerBuilderException("Only exclusive gateways can set expressions.");
        }
        doAddTargetNode(targetNode);
        expressionMap.put(this.nodeId().concat(targetNode.nodeId()), expression);
        return this;
    }

    @Override
    public NodeCombination addTargetNodes(List<NodeCombination> targetNodes) {
        if (targetNodes.stream()
                .anyMatch(targetNode -> this.nodeId().equals(targetNode.nodeId()))) {
            throw new NodeManagerBuilderException(
                    "The source node and target node cannot be the same. Node ID Is: "
                            + this.nodeId());
        }

        if (NodeType.EXCLUSIVE.name().equals(this.type())) {
            throw new NodeManagerBuilderException(
                    "Exclusive gateway must set expression properties.");
        }

        targetNodes.forEach(
                targetNode -> ((AbstractNodeCombination) targetNode).referencedDeploy());
        this.targetNodes.addAll(targetNodes);
        return this;
    }

    @Override
    public NodeCombination properties(Map<String, Object> properties) {
        this.properties = properties;
        return this;
    }

    @Override
    public NodeCombination beforeListener(NodeListener nodeBeforeListener) {
        this.nodeBeforeListener = nodeBeforeListener;
        return this;
    }

    @Override
    public NodeCombination afterListener(NodeListener nodeAfterListener) {
        this.nodeAfterListener = nodeAfterListener;
        return this;
    }

    @Override
    public List<NodeCombination> targetNodeGet() {
        return this.targetNodes;
    }

    @Override
    public Delegate delegateGet() {
        return this.delegate;
    }

    @Override
    public boolean isParallel() {
        return node.isParallel();
    }

    @Override
    public boolean isExpression() {
        return !expressionMap.isEmpty();
    }

    @Override
    public boolean expressionExecute(String relations, Context context) {
        String expression = expressionMap.get(relations);
        if (StringUtils.isBlank(expression)) {
            return true;
        }
        DelegateContext delegateContext = context.nodeContext().delegateContext();
        return expressionHandler.check(expression, delegateContext);
    }

    @Override
    public NodeListener beforeListener() {
        return nodeBeforeListener;
    }

    @Override
    public NodeListener afterListener() {
        return nodeAfterListener;
    }
}
