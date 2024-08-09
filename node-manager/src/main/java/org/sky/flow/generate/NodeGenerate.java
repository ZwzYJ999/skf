package org.sky.flow.generate;

import java.util.Map;

import com.google.common.collect.Maps;

import org.sky.flow.node.definition.Node;
import org.sky.flow.node.definition.enums.NodeType;
import org.sky.flow.node.definition.gateway.ExclusiveGatewayNode;
import org.sky.flow.node.definition.gateway.InclusiveGatewayNode;
import org.sky.flow.node.definition.gateway.InclusiveParallelGatewayNode;
import org.sky.flow.node.definition.gateway.ParallelGatewayNode;
import org.sky.flow.node.definition.task.ServiceTaskNode;

/**
 * Node生成
 *
 * @author yj
 */
public class NodeGenerate {

    private NodeGenerate() {
        throw new IllegalStateException("Utility class");
    }

    /** Generate */
    private static final Map<String, NodeSupplier> GENERATE = Maps.newHashMap();

    static {
        // service task
        serviceTask();

        // gateway.
        inclusiveGateway();
        parallelGateway();
        exclusiveGateway();
        inclusiveParallelGateway();
    }

    /**
     * description: 排他网关创建
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/10 15:43   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/10 15:43
     */
    private static void exclusiveGateway() {
        GENERATE.put(NodeType.EXCLUSIVE.name(), ExclusiveGatewayNode::new);
    }

    /**
     * description: service task
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 16:17   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 16:17
     */
    private static void serviceTask() {
        GENERATE.put(NodeType.SERVICE_TASK.name(), ServiceTaskNode::new);
    }

    /**
     * description: 并行网关
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 15:48   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/9 15:48
     */
    private static void parallelGateway() {
        GENERATE.put(NodeType.PARALLEL.name(), ParallelGatewayNode::new);
    }

    /**
     * description: 并行网关
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 15:48   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/9 15:48
     */
    private static void inclusiveGateway() {
        GENERATE.put(NodeType.INCLUSIVE.name(), InclusiveGatewayNode::new);
    }

    /**
     * description: 汇聚网关&并行网关
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/10/28 12:15   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/10/28 12:15
     */
    private static void inclusiveParallelGateway() {
        GENERATE.put(NodeType.INCLUSIVE_PARALLEL.name(), InclusiveParallelGatewayNode::new);
    }

    /**
     * description: 生成Node对象
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 16:10   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 16:10
     * @param id ID
     * @param name Name
     * @param nodeType node Type
     */
    public static Node generate(String id, String name, NodeType nodeType) {
        return GENERATE.get(nodeType.name()).get(id, name);
    }
}
