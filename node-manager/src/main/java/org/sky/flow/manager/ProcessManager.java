package org.sky.flow.manager;

import org.sky.flow.combination.ProcessCombination;

/**
 * Process Manager
 *
 * @author yj
 */
public interface ProcessManager {

    /**
     * description: 创建Process Flow
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 17:05   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 17:05
     * @param name process flow 名称
     * @return org.sky.flow.process.definition.ProcessFlow
     */
    ProcessCombination generate(String name);

    /**
     * description: 创建process flow
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
     * @param name process flow name
     * @param flowId flow id
     * @return org.sky.flow.combination.ProcessCombination
     */
    ProcessCombination generate(String flowId, String name);
}
