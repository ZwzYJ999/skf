package org.sky.flow.combination;

import java.util.List;

import com.google.common.collect.Lists;

import org.sky.flow.exception.NodeManagerBuilderException;
import org.sky.flow.process.invoke.Process;

/**
 * Invoke
 *
 * @author yj
 */
public class ProcessCombinationInvoke extends AbstractProcessCombinationInvoke
        implements ProcessCombination {

    /** Process */
    private Process process;

    /** 初始化节点 */
    private final List<NodeCombination> nodeCombinations = Lists.newArrayList();

    @Override
    public ProcessCombination holder(Process process) {
        this.process = process;
        return this;
    }

    @Override
    public String flowId() {
        return process.flowId();
    }

    @Override
    public String flowName() {
        return process.flowName();
    }

    @Override
    public ProcessCombination addStartNode(NodeCombination nodeCombination) {
        nodeCombinations.add(nodeCombination);
        return this;
    }

    @Override
    public void init() {
        // 初始化flow. 验证数据有效性
        if (nodeCombinations.isEmpty()) {
            throw new NodeManagerBuilderException("Start Event Can`t Be Empty.");
        }
    }

    /**
     * description: 获取start event
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 14:37   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/9 14:37
     * @return java.util.List<org.sky.flow.combination.NodeCombination>
     */
    @Override
    public List<NodeCombination> startEventNodeGet() {
        return nodeCombinations;
    }
}
