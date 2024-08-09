package org.sky.flow.run;

import org.sky.flow.combination.NodeCombination;
import org.sky.flow.combination.ProcessCombination;
import org.sky.flow.generate.BaseManager;
import org.sky.flow.generate.TestDelegate;
import org.sky.flow.node.definition.enums.NodeType;

/**
 * a -> (B,C) -> D
 *
 * @author YJ
 */
public class RunMuIndex5 extends BaseManager {
    public static void main(String[] args) {
        ProcessCombination processCombination = processManager.generate("测试一下");

        TestDelegate testDelegate = new TestDelegate();

        NodeCombination a =
                nodeManager
                        .generate()
                        .type(NodeType.PARALLEL)
                        .delegate(testDelegate)
                        .name("A")
                        .build();
        NodeCombination b =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .delegate(testDelegate)
                        .name("B")
                        .build();
        NodeCombination c =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .delegate(testDelegate)
                        .name("C")
                        .build();
        NodeCombination d =
                nodeManager
                        .generate()
                        .type(NodeType.INCLUSIVE)
                        .delegate(testDelegate)
                        .name("D")
                        .build();

        a.addTargetNode(b);
        a.addTargetNode(c);
        b.addTargetNode(d);
        c.addTargetNode(d);

        processCombination.addStartNode(a);
        processStoreManager.save(processCombination);
        processEngine.startProcessById(processCombination.flowId());
    }
}
