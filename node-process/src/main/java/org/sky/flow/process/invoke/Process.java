package org.sky.flow.process.invoke;

/**
 * Process Flow
 *
 * @author yj
 */
public interface Process {

    /**
     * description: 获取flow ID
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 17:06   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 17:06
     * @return java.lang.String
     */
    String flowId();

    /**
     * description: 获取flow name
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/10/28 11:07   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/10/28 11:07
     * @return java.lang.String
     */
    String flowName();
}
