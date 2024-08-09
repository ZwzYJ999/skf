package org.sky.flow.context;

/**
 * Context Interface
 *
 * @author yj
 */
public interface Context {

    /**
     * description: 获取流程ID
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/10/28 10:52   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/10/28 10:52
     * @return java.lang.String
     */
    String flowId();

    /**
     * description: flow 名称
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/10/28 11:09   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/10/28 11:09
     * @return java.lang.String
     */
    String flowName();

    /**
     * description: 上下文只属于一个实例
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 16:40   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/9 16:40
     * @return java.lang.String
     */
    String instanceId();

    /**
     * description: 获取node context
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 17:32   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/9 17:32
     * @return org.sky.flow.context.NodeContext
     */
    NodeContext nodeContext();

    /**
     * description: 上下文数据存放
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/10 16:19   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/10 16:19
     * @return org.sky.flow.context.DelegateContext
     */
    DelegateContext delegateContext();
}
