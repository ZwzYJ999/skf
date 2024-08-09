package org.sky.flow.store;

import org.sky.flow.combination.ProcessCombination;

/**
 * Process Store Manager
 *
 * @author yj
 */
public interface ProcessStoreManager {

    /**
     * description: save process flow
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/21 17:15   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/21 17:15
     * @param processCombination process flow
     */
    void save(ProcessCombination processCombination);

    /**
     * description: remove process flow
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/21 17:16   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/21 17:16
     * @param processFlowId process flow id
     */
    void remove(String processFlowId);

    /**
     * description: 清理所有的process flow
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/9/5 15:42   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/9/5 15:42
     */
    void clear();

    /**
     * description: process flow count
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/10/28 00:59   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/10/28 00:59
     * @return int
     */
    int count();
}
