package org.sky.flow.context;

/**
 * Node抽象类
 *
 * @author YJ
 */
public abstract class AbstractNodeContext implements Context {

    /** global context */
    protected final GlobalContext globalContext;

    /**
     * description: 构造方法. 保存global context
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/10/28 10:54   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/10/28 10:54
     * @param globalContext 流程上下文
     */
    protected AbstractNodeContext(GlobalContext globalContext) {
        this.globalContext = globalContext;
    }

    @Override
    public String flowId() {
        return globalContext.flowId();
    }

    @Override
    public String flowName() {
        return globalContext.flowName();
    }

    @Override
    public String instanceId() {
        return globalContext.instanceId();
    }

    @Override
    public DelegateContext delegateContext() {
        return globalContext.delegateContext();
    }
}
