package org.sky.flow.combination;

import java.util.List;

/**
 * Process Combination
 *
 * @author yj
 */
public abstract class AbstractProcessCombinationInvoke implements ProcessCombination {

    /**
     * description: start event get
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 14:41   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/9 14:41
     * @return java.util.List<org.sky.flow.combination.NodeCombination>
     */
    public abstract List<NodeCombination> startEventNodeGet();
}
