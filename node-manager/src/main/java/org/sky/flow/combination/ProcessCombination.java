package org.sky.flow.combination;

import org.sky.flow.process.invoke.Process;

/**
 * Process Combination
 *
 * @author yj
 */
public interface ProcessCombination {

    /**
     * description: process
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 17:17   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 17:17
     * @param process process
     * @return process
     */
    ProcessCombination holder(Process process);

    /**
     * description: flow id
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 17:25   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 17:25
     * @return java.lang.String
     */
    String flowId();

    /**
     * description: flow name
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/10/28 11:06   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/10/28 11:06
     * @return java.lang.String
     */
    String flowName();

    /**
     * description: node combination
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 17:19   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 17:19
     * @param nodeCombination node combination
     * @return process
     */
    ProcessCombination addStartNode(NodeCombination nodeCombination);

    /**
     * description: 初始化Flow
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 17:50   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 17:50
     */
    void init();
}
