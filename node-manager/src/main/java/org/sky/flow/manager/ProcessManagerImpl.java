package org.sky.flow.manager;

import org.sky.flow.combination.ProcessCombination;
import org.sky.flow.combination.ProcessCombinationInvoke;
import org.sky.flow.process.definition.ProcessFlow;

/**
 * 创建process flow
 *
 * @author yj
 */
public class ProcessManagerImpl implements ProcessManager {

    @Override
    public ProcessCombination generate(String name) {
        return new ProcessCombinationInvoke().holder(new ProcessFlow(name));
    }

    @Override
    public ProcessCombination generate(String flowId, String name) {
        return new ProcessCombinationInvoke().holder(new ProcessFlow(flowId, name));
    }
}
