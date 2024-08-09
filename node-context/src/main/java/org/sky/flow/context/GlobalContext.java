package org.sky.flow.context;

import java.util.UUID;

/**
 * 全局Context
 *
 * @author yj
 */
public class GlobalContext implements Context {

    /** flow ID */
    private final String flowId;

    /** flow name */
    private final String flowName;

    /** 实例ID */
    private final String instanceId;

    /** 参数context. */
    private final DelegateContext delegateContext;

    /**
     * generate
     *
     * @param flowId flow id
     * @param flowName flow name
     * @param delegateContext delegate context
     */
    public GlobalContext(String flowId, String flowName, DelegateContext delegateContext) {
        this.flowId = flowId;
        this.flowName = flowName;
        this.instanceId = UUID.randomUUID().toString();
        this.delegateContext = delegateContext;
    }

    @Override
    public String flowId() {
        return this.flowId;
    }

    @Override
    public String flowName() {
        return this.flowName;
    }

    @Override
    public String instanceId() {
        return instanceId;
    }

    @Override
    public NodeContext nodeContext() {
        return new ExtendNodeContext(this);
    }

    @Override
    public DelegateContext delegateContext() {
        return delegateContext;
    }
}
