package org.sky.flow.combination;

import java.util.List;
import java.util.Map;

import org.sky.flow.delegate.Delegate;
import org.sky.flow.listener.NodeListener;
import org.sky.flow.node.definition.Node;

/**
 * Node 组合(Delegate)
 *
 * @author yj
 */
public interface NodeCombination {

    /**
     * description: Node
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 16:05   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 16:05
     * @param node node
     * @return NodeCombination
     */
    NodeCombination holder(Node node);

    /**
     * description: type
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 17:51   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 17:51
     * @return java.lang.String
     */
    String type();

    /**
     * description: node Id
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 18:06   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 18:06
     * @return java.lang.String
     */
    String nodeId();

    /**
     * description: node name
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 17:41   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/9 17:41
     * @return java.lang.String
     */
    String name();

    /**
     * description: properties
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/11 11:29   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/11 11:29
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    Map<String, Object> properties();

    /**
     * description: setting delegate
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 16:21   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 16:21
     * @param delegate delegate
     * @return org.sky.flow.combination.NodeCombination
     */
    NodeCombination delegate(Delegate delegate);

    /**
     * description: target node
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 11:11   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/9 11:11
     * @param targetNode node
     * @return org.sky.flow.combination.NodeCombination
     */
    NodeCombination addTargetNode(NodeCombination targetNode);

    /**
     * description: 当前节点执行表达式为true才能到target node
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/10 17:23   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/10 17:23
     * @param targetNode target node
     * @param expression 表达式
     * @return org.sky.flow.combination.NodeCombination
     */
    NodeCombination addTargetNode(NodeCombination targetNode, String expression);

    /**
     * description: add target nodes
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 11:14   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/9 11:14
     * @param targetNodes target nodes
     * @return org.sky.flow.combination.NodeCombination
     */
    NodeCombination addTargetNodes(List<NodeCombination> targetNodes);

    /**
     * description: properties
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/11 14:38   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/11 14:38
     * @param properties node properties
     * @return org.sky.flow.combination.NodeCombination
     */
    NodeCombination properties(Map<String, Object> properties);

    /**
     * description: setting before listener
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/21 17:58   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/21 17:58
     * @param nodeBeforeListener node before listener
     * @return org.sky.flow.combination.NodeCombination
     */
    NodeCombination beforeListener(NodeListener nodeBeforeListener);

    /**
     * description: setting after listener
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/21 17:58   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/21 17:58
     * @param nodeAfterListener node after listener
     * @return org.sky.flow.combination.NodeCombination
     */
    NodeCombination afterListener(NodeListener nodeAfterListener);
}
