package org.sky.flow.manager;

import org.sky.flow.ql.express.ExpressionHandler;

/**
 * node manager
 *
 * @author yj
 */
public class NodeManagerImpl implements NodeManager {

    /** 表达式执行引擎 */
    private final ExpressionHandler expressionHandler;

    /**
     * create node manager
     *
     * @param expressionHandler expression handler
     */
    public NodeManagerImpl(ExpressionHandler expressionHandler) {
        this.expressionHandler = expressionHandler;
    }

    @Override
    public NodeAttributeBuilder generate() {
        return new NodeBuilder(expressionHandler);
    }
}
