package org.sky.flow.run;

import java.time.Duration;
import java.time.LocalDateTime;

import org.sky.flow.combination.NodeCombination;
import org.sky.flow.combination.ProcessCombination;
import org.sky.flow.engine.manager.ProcessEngineManager;
import org.sky.flow.generate.TestDelegate;
import org.sky.flow.manager.NodeManager;
import org.sky.flow.node.definition.enums.NodeType;

/**
 * Run Mu Index
 *
 * @author yj
 */
public class RunMuIndex4 {

    public static void main(String[] args) {
        ProcessCombination processCombination =
                ProcessEngineManager.generateProcessManager().generate("问题4");

        NodeManager nodeManager = ProcessEngineManager.generateNodeManager();
        TestDelegate testDelegate = new TestDelegate();

        NodeCombination startEvent =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("repository")
                        .delegate(testDelegate)
                        .build();

        NodeCombination function2 =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("Function2")
                        .delegate(testDelegate)
                        .build();

        startEvent.addTargetNode(function2);

        NodeCombination parallel =
                nodeManager
                        .generate()
                        .type(NodeType.PARALLEL)
                        .name("虚拟并行网关")
                        .delegate(testDelegate)
                        .build();

        function2.addTargetNode(parallel);

        NodeCombination index5 =
                nodeManager
                        .generate()
                        .type(NodeType.INCLUSIVE)
                        .name("index5")
                        .delegate(testDelegate)
                        .build();

        parallel.addTargetNode(index5);

        NodeCombination cumulate1 =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("cumulate1")
                        .delegate(testDelegate)
                        .build();
        NodeCombination compress3 =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("compress3")
                        .delegate(testDelegate)
                        .build();
        NodeCombination filter10 =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("filter10")
                        .delegate(testDelegate)
                        .build();

        parallel.addTargetNode(
                cumulate1.addTargetNode(compress3.addTargetNode(filter10.addTargetNode(index5))));

        NodeCombination cumulate2 =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("cumulate2")
                        .delegate(testDelegate)
                        .build();

        NodeCombination filter9 =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("filter9")
                        .delegate(testDelegate)
                        .build();

        NodeCombination index3 =
                nodeManager
                        .generate()
                        .type(NodeType.INCLUSIVE)
                        .name("index3")
                        .delegate(testDelegate)
                        .build();

        parallel.addTargetNode(cumulate2.addTargetNode(index3));
        parallel.addTargetNode(filter9.addTargetNode(index3));

        NodeCombination filter6 =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("filter6")
                        .delegate(testDelegate)
                        .build();

        index3.addTargetNode(filter6);

        NodeCombination compress1 =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("compress1")
                        .delegate(testDelegate)
                        .build();

        filter6.addTargetNode(compress1);

        NodeCombination compress5 =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("compress5")
                        .delegate(testDelegate)
                        .build();

        index5.addTargetNode(compress5);

        NodeCombination union2 =
                nodeManager
                        .generate()
                        .type(NodeType.INCLUSIVE)
                        .name("union2")
                        .delegate(testDelegate)
                        .build();

        compress5.addTargetNode(union2);
        compress1.addTargetNode(union2);

        NodeCombination sort3 =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("sort3")
                        .delegate(testDelegate)
                        .build();

        union2.addTargetNode(sort3);

        NodeCombination endEvent =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("虚拟结束节点")
                        .delegate(testDelegate)
                        .build();

        sort3.addTargetNode(endEvent);

        processCombination.addStartNode(startEvent);

        ProcessEngineManager.generateProcessStoreManager().save(processCombination);

        LocalDateTime now = LocalDateTime.now();
        ProcessEngineManager.generate().startProcessById(processCombination.flowId());

        System.out.println(Duration.between(now, LocalDateTime.now()).toMillis());
    }
}
