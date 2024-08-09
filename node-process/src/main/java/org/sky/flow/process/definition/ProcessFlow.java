package org.sky.flow.process.definition;

import java.util.UUID;

import org.sky.flow.process.invoke.AbstractProcessFlow;

/**
 * Process Flow 对象
 *
 * @author yj
 */
public class ProcessFlow extends AbstractProcessFlow {

    /**
     * description: 创建Process Flow对象
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 17:02   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 17:02
     * @param name name
     */
    public ProcessFlow(String name) {
        super(UUID.randomUUID().toString(), name);
    }

    /**
     * description: 创建process flow对象
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/10/28 10:59   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/10/28 10:59
     * @param flowId flow id
     * @param name 流程名称
     */
    public ProcessFlow(String flowId, String name) {
        super(flowId, name);
    }

    @Override
    public String flowId() {
        return this.id;
    }

    @Override
    public String flowName() {
        return this.name;
    }
}
