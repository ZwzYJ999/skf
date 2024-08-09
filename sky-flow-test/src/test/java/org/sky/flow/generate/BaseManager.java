package org.sky.flow.generate;

import org.sky.flow.engine.ProcessEngine;
import org.sky.flow.engine.manager.ProcessEngineManager;
import org.sky.flow.manager.NodeManager;
import org.sky.flow.manager.ProcessManager;
import org.sky.flow.store.ProcessStoreManager;

/** Base manager */
public class BaseManager {

    /** process engine */
    protected static ProcessEngine processEngine = ProcessEngineManager.generate();

    /** process flow manager */
    protected static ProcessManager processManager = ProcessEngineManager.generateProcessManager();

    /** node manager */
    protected static NodeManager nodeManager = ProcessEngineManager.generateNodeManager();

    /** process store manager */
    protected static ProcessStoreManager processStoreManager =
            ProcessEngineManager.generateProcessStoreManager();
}
