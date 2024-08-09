package org.sky.flow.configuration;

import org.sky.flow.engine.ProcessEngine;
import org.sky.flow.engine.manager.ProcessEngineManager;
import org.sky.flow.manager.NodeManager;
import org.sky.flow.manager.ProcessManager;
import org.sky.flow.ql.express.ExpressionHandler;
import org.sky.flow.store.ProcessStoreManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 初始化configuration
 *
 * @author yj
 */
@AutoConfiguration
public class SkyFlowConfiguration {

    /**
     * Process Engine Bean
     *
     * @return Process Engine
     */
    @Bean
    public ProcessEngine processEngine() {
        return ProcessEngineManager.generate();
    }

    /** 流程管理Bean */
    @Bean
    public ProcessManager processManager() {
        return ProcessEngineManager.generateProcessManager();
    }

    /** 节点管理 */
    @Bean
    public NodeManager nodeManager(
            @Autowired(required = false) ExpressionHandler expressionHandler) {
        return ProcessEngineManager.generateNodeManager(expressionHandler);
    }

    /** store */
    @Bean
    public ProcessStoreManager processStoreManager() {
        return ProcessEngineManager.generateProcessStoreManager();
    }
}
