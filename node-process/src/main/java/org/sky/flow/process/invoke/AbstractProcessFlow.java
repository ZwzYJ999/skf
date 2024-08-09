package org.sky.flow.process.invoke;

/**
 * 流程对象
 *
 * @author yj
 */
public abstract class AbstractProcessFlow implements Process, ProcessOperator {
    /** ID */
    protected String id;

    /** 流程名称 */
    protected String name;

    protected AbstractProcessFlow(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
