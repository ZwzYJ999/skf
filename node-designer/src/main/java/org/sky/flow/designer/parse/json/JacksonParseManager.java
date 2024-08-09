package org.sky.flow.designer.parse.json;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

import org.apache.commons.lang3.StringUtils;
import org.sky.flow.combination.NodeCombination;
import org.sky.flow.combination.ProcessCombination;
import org.sky.flow.delegate.Delegate;
import org.sky.flow.designer.parse.NodeParse;
import org.sky.flow.designer.parse.RelationParse;
import org.sky.flow.designer.parse.exception.NodeParseException;
import org.sky.flow.listener.NodeListener;
import org.sky.flow.manager.NodeAttributeBuilder;
import org.sky.flow.manager.NodeManager;
import org.sky.flow.manager.ProcessManager;
import org.sky.flow.node.definition.enums.NodeType;

/**
 * Jackson 解析
 *
 * @author yj
 */
public class JacksonParseManager {

    /** Node Manager */
    private final NodeManager nodeManager;

    /** Process Manager */
    private final ProcessManager processManager;

    /**
     * generate
     *
     * @param nodeManager node manager
     * @param processManager process manager
     */
    public JacksonParseManager(NodeManager nodeManager, ProcessManager processManager) {
        this.nodeManager = nodeManager;
        this.processManager = processManager;
    }

    /**
     * description: 根据文件地址加载
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/23 14:27   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/23 14:27
     * @param jsonFileUrl url
     * @param processName process name
     * @return org.sky.flow.combination.ProcessCombination
     */
    public ProcessCombination parseToProcessCombination(String jsonFileUrl, String processName)
            throws IOException {
        JsonParse jsonParse = new ObjectMapper().readValue(new File(jsonFileUrl), JsonParse.class);
        List<NodeParse> nodes = jsonParse.getNodes();
        if (nodes == null || nodes.isEmpty()) {
            throw new NodeParseException("Flow File : Nodes Can`t Be Null.");
        }

        List<RelationParse> relations = jsonParse.getRelations();
        boolean isEmpty = relations == null || relations.isEmpty();
        if (isEmpty && nodes.size() == 1) {
            throw new NodeParseException("Flow File : Nodes Can`t Be Null.");
        }

        // 解析为组合对象
        List<NodeCombination> nodeCombinations = parseNodes(nodes);

        Map<String, List<RelationParse>> relationParseMap =
                Objects.isNull(relations)
                        ? Maps.newHashMap()
                        : relations.stream()
                                .collect(Collectors.groupingBy(RelationParse::getSourceId));

        Map<String, String> targetKeyMap = Maps.newHashMap();

        Map<String, NodeCombination> nodeMaps = Maps.newHashMap();
        nodeCombinations.forEach(
                node -> {
                    targetKeyMap.put(node.nodeId(), node.nodeId());
                    nodeMaps.put(node.nodeId(), node);
                });

        nodeMaps.forEach(
                (id, node) -> {
                    List<RelationParse> relationParseList = relationParseMap.get(id);

                    if (relationParseList != null && !relationParseList.isEmpty()) {

                        relationParseList.forEach(
                                relationParse -> {
                                    NodeCombination targetNode =
                                            nodeMaps.get(relationParse.getTargetId());

                                    if (StringUtils.isBlank(relationParse.getExpression())) {
                                        node.addTargetNode(targetNode);
                                    } else {
                                        node.addTargetNode(
                                                targetNode, relationParse.getExpression());
                                    }

                                    targetKeyMap.remove(relationParse.getTargetId());
                                });
                    }
                });

        Optional<String> first = targetKeyMap.values().stream().findFirst();
        if (first.isEmpty()) {
            throw new NodeParseException("Start Node Can`t Be Null");
        }
        return processManager.generate(processName).addStartNode(nodeMaps.get(first.get()));
    }

    /**
     * description: node 转换为执行对象
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/24 16:59   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/24 16:59
     * @param nodeParses node parse list
     * @return java.util.List<org.sky.flow.combination.NodeCombination>
     */
    private List<NodeCombination> parseNodes(List<NodeParse> nodeParses) {
        return nodeParses.stream()
                .map(
                        nodeParse -> {
                            try {
                                String afterListener = nodeParse.getAfterListener();
                                String beforeListener = nodeParse.getBeforeListener();
                                String delegate = nodeParse.getDelegate();
                                NodeAttributeBuilder nodeBuilder =
                                        nodeManager
                                                .generate()
                                                .name(nodeParse.getName())
                                                .id(nodeParse.getId())
                                                .type(NodeType.valueOf(nodeParse.getType()))
                                                .properties(nodeParse.getProperties());

                                if (StringUtils.isNotBlank(afterListener)) {
                                    nodeBuilder.afterListener(
                                            (NodeListener)
                                                    Class.forName(nodeParse.getAfterListener())
                                                            .getDeclaredConstructor()
                                                            .newInstance());
                                }

                                if (StringUtils.isNotBlank(beforeListener)) {
                                    nodeBuilder.beforeListener(
                                            (NodeListener)
                                                    Class.forName(nodeParse.getBeforeListener())
                                                            .getDeclaredConstructor()
                                                            .newInstance());
                                }

                                if (StringUtils.isNotBlank(delegate)) {
                                    nodeBuilder.delegate(
                                            (Delegate)
                                                    Class.forName(nodeParse.getDelegate())
                                                            .getDeclaredConstructor()
                                                            .newInstance());
                                }

                                return nodeBuilder.build();
                            } catch (Exception e) {
                                throw new NodeParseException(
                                        "parse node exception: " + e.getMessage());
                            }
                        })
                .toList();
    }
}
