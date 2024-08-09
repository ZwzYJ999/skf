package org.sky.flow.engine.strategy;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.sky.flow.combination.ProcessCombination;
import org.sky.flow.context.Context;
import org.sky.flow.context.ExtendNodeContext;
import org.sky.flow.engine.ProcessEngine;
import org.sky.flow.engine.exception.EngineException;
import org.sky.flow.listener.NodeListener;

/**
 * Basic Process Engine
 *
 * @author yj
 */
public class NormalProcessEngine extends AbstractNodeProcessEngine implements ProcessEngine {

    @Override
    public Context startProcessById(String id) {
        // 获取process flow
        ProcessCombination processCombination = PROCESS_STORE.get(id);
        if (processCombination == null) {
            throw new EngineException("Process Flow Not Found. Can`t Be Start Process : " + id);
        }

        // process flow start
        return execute(processCombination, new ConcurrentHashMap<>(16));
    }

    @Override
    public Context startProcessById(String id, ConcurrentHashMap<String, Object> context) {
        // 获取process flow
        ProcessCombination processCombination = PROCESS_STORE.get(id);
        if (processCombination == null) {
            throw new EngineException("Process Flow Not Found. Can`t Be Start Process : " + id);
        }

        return execute(processCombination, context);
    }

    @Override
    protected void triggerListener(NodeListener nodeListener, ExtendNodeContext nodeContext) {
        Optional.ofNullable(nodeListener).ifPresent(listener -> listener.listener(nodeContext));
    }
}
