package org.sky.flow.sit;

import org.sky.flow.combination.NodeCombination;
import org.sky.flow.combination.ProcessCombination;
import org.sky.flow.context.Context;
import org.sky.flow.delegate.Delegate;
import org.sky.flow.generate.BaseManager;
import org.sky.flow.node.definition.enums.NodeType;

/**
 * Sit test: flow ID
 *
 * @author YJ
 */
public class SitFlowIdIndex1 extends BaseManager {
    public static void main(String[] args) {
        // step1. create flow
        ProcessCombination flow = processManager.generate("ABC", "Flow 测试");

        // step2. create delegate
        Delegate delegate =
                new Delegate() {
                    @Override
                    public void execute(Context context) {
                        System.out.println(context.flowId());
                        System.out.println(context.flowName());
                    }

                    @Override
                    public boolean specificStop(Context context) {
                        return false;
                    }
                };

        // step3. create a--->b
        NodeCombination a =
                nodeManager
                        .generate()
                        .id("A")
                        .name("A")
                        .delegate(delegate)
                        .type(NodeType.SERVICE_TASK)
                        .build();

        NodeCombination b =
                nodeManager
                        .generate()
                        .id("B")
                        .name("B")
                        .delegate(delegate)
                        .type(NodeType.SERVICE_TASK)
                        .build();

        a.addTargetNode(b);

        flow.addStartNode(a);
        processStoreManager.save(flow);

        processEngine.startProcessById(flow.flowId());
    }
}
