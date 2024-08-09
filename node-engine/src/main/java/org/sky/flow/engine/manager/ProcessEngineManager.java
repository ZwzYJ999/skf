package org.sky.flow.engine.manager;

import org.sky.flow.engine.ProcessEngine;
import org.sky.flow.engine.exception.EngineException;
import org.sky.flow.engine.strategy.NormalProcessEngine;
import org.sky.flow.manager.NodeManager;
import org.sky.flow.manager.NodeManagerImpl;
import org.sky.flow.manager.ProcessManager;
import org.sky.flow.manager.ProcessManagerImpl;
import org.sky.flow.ql.express.BasicQlExpressionHandler;
import org.sky.flow.ql.express.ExpressionHandler;
import org.sky.flow.store.ProcessStoreManager;
import org.sky.flow.store.ProcessStoreManagerImpl;

/**
 * 流程引擎创建
 *
 * @author yj
 */
public class ProcessEngineManager {

    /** 不能创建对象 */
    private ProcessEngineManager() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * description: 创建引擎对象
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 17:08   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 17:08
     * @return org.sky.flow.engine.ProcessEngine
     */
    public static ProcessEngine generate() {
        return new NormalProcessEngine();
    }

    /**
     * description: 创建Process Manager
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 17:31   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 17:31
     * @return org.sky.flow.process.manager.ProcessManager
     */
    public static ProcessManager generateProcessManager() {
        return new ProcessManagerImpl();
    }

    /**
     * description: Default Node Manager Builder
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 15:22   ******              YJ                Create
     *
     * </pre>
     *
     * @param expressionHandler 表达式执行引擎
     * @author YJ
     * @date 2023/8/8 15:22
     * @return org.sky.flow.manager.NodeManager
     */
    public static NodeManager generateNodeManager(ExpressionHandler expressionHandler) {
        if (expressionHandler == null) {
            throw new EngineException(
                    "The expression engine cannot be empty when creating a node manager.");
        }
        return new NodeManagerImpl(expressionHandler);
    }

    /**
     * description: create basic exp . node manager
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/10/28 12:01   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/10/28 12:01
     * @return org.sky.flow.manager.NodeManager
     */
    public static NodeManager generateNodeManager() {
        return new NodeManagerImpl(new BasicQlExpressionHandler());
    }

    /**
     * description: generate process store manager
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/21 17:17   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/21 17:17
     * @return org.sky.flow.store.ProcessStoreManager
     */
    public static ProcessStoreManager generateProcessStoreManager() {
        return new ProcessStoreManagerImpl();
    }
}
