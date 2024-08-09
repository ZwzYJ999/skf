package org.sky.flow.store;

import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.Maps;

import org.sky.flow.combination.ProcessCombination;
import org.sky.flow.exception.NodeManagerBuilderException;

/**
 * process store manager
 *
 * @author yj
 */
public class ProcessStoreManagerImpl implements ProcessStoreManager {

    /** Process Store */
    protected static final ConcurrentMap<String, ProcessCombination> PROCESS_STORE =
            Maps.newConcurrentMap();

    @Override
    public synchronized void save(ProcessCombination processCombination) {
        String flowId = processCombination.flowId();
        if (PROCESS_STORE.get(flowId) == null) {
            processCombination.init();
            PROCESS_STORE.put(flowId, processCombination);
        } else {
            throw new NodeManagerBuilderException(
                    "Process Flow " + flowId + "already exists, please save after deletion.");
        }
    }

    @Override
    public void remove(String processFlowId) {
        PROCESS_STORE.remove(processFlowId);
    }

    @Override
    public void clear() {
        PROCESS_STORE.clear();
    }

    @Override
    public int count() {
        return PROCESS_STORE.size();
    }
}
