package org.sky.flow.run;

import java.util.concurrent.ConcurrentHashMap;

import org.sky.flow.combination.NodeCombination;
import org.sky.flow.combination.ProcessCombination;
import org.sky.flow.delegate.Delegate;
import org.sky.flow.generate.BaseManager;
import org.sky.flow.generate.NumRemoveDelegate;
import org.sky.flow.generate.TestDelegate;
import org.sky.flow.node.definition.enums.NodeType;

public class RunForIndex1 extends BaseManager {
    public static void main(String[] args) {
        ProcessCombination processCombination = processManager.generate("循环流程");

        Delegate delegate = new TestDelegate();
        Delegate delegate1 = new NumRemoveDelegate();

        NodeCombination a =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .delegate(delegate)
                        .name("A")
                        .build();
        NodeCombination b =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .delegate(delegate)
                        .name("B")
                        .build();
        NodeCombination c =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .delegate(delegate1)
                        .name("C")
                        .build();
        NodeCombination d =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .delegate(delegate)
                        .name("D")
                        .build();
        NodeCombination e =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .delegate(delegate)
                        .name("E")
                        .build();

        NodeCombination exclusiveNode = nodeManager.generate().type(NodeType.EXCLUSIVE).build();

        a.addTargetNode(b.addTargetNode(c));
        d.addTargetNode(b);
        c.addTargetNode(exclusiveNode);

        exclusiveNode.addTargetNode(e, "num == 1");
        exclusiveNode.addTargetNode(d, "num > 1");

        processCombination.addStartNode(a);

        processStoreManager.save(processCombination);

        ConcurrentHashMap<String, Object> context = new ConcurrentHashMap<>();
        context.put("num", 3);
        processEngine.startProcessById(processCombination.flowId(), context);
    }
}
