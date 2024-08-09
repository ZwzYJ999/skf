package org.sky.flow.combination.specific;

import java.util.Map;

import com.google.common.collect.Maps;

import org.sky.flow.combination.NodeCombination;
import org.sky.flow.generate.NodeCombinationSupplier;
import org.sky.flow.node.definition.Node;
import org.sky.flow.node.definition.enums.NodeType;
import org.sky.flow.ql.express.ExpressionHandler;

/**
 * 特殊combination转换
 *
 * @author yj
 */
public class SpecificCombinationConvert {

    private SpecificCombinationConvert() {
        throw new IllegalStateException("Utility class");
    }

    /** 转换工具 */
    private static final Map<String, NodeCombinationSupplier> CONVERT = Maps.newConcurrentMap();

    /* 静态处理器 */
    static {
        slot();
    }

    /**
     * description: slot 处理器
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 17:25   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/9 17:25
     */
    private static void slot() {
        // slot 处理器
        NodeCombinationSupplier nodeCombinationSupplier =
                (expressionHandler, node) -> {
                    SlotNodeCombinationInvoke slotNodeCombinationInvoke =
                            new SlotNodeCombinationInvoke(expressionHandler);
                    slotNodeCombinationInvoke.holder(node);
                    return slotNodeCombinationInvoke;
                };

        // 汇聚网关
        CONVERT.put(NodeType.INCLUSIVE.name(), nodeCombinationSupplier);
        // 汇聚&并行网关
        CONVERT.put(NodeType.INCLUSIVE_PARALLEL.name(), nodeCombinationSupplier);
    }

    /**
     * description: 数据转换
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 15:59   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/9 15:59
     * @param node node
     * @return org.sky.flow.combination.NodeCombination
     */
    public static NodeCombination apply(ExpressionHandler expressionHandler, Node node) {
        NodeCombinationSupplier nodeCombinationSupplier = CONVERT.get(node.type().name());
        return nodeCombinationSupplier == null
                ? null
                : nodeCombinationSupplier.get(expressionHandler, node);
    }
}
