package org.sky.flow.generate;

import org.sky.flow.combination.NodeCombination;
import org.sky.flow.node.definition.Node;
import org.sky.flow.ql.express.ExpressionHandler;

/**
 * Node Convert Combination
 *
 * @author yj
 */
@FunctionalInterface
public interface NodeCombinationSupplier {

    /**
     * description: get node combination
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 17:23   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/9 17:23
     * @param node node
     * @param expressionHandler expression handler
     * @return org.sky.flow.combination.NodeCombination
     */
    NodeCombination get(ExpressionHandler expressionHandler, Node node);
}
