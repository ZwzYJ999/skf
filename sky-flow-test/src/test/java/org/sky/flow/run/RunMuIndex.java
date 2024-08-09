package org.sky.flow.run;

import org.sky.flow.combination.NodeCombination;
import org.sky.flow.combination.ProcessCombination;
import org.sky.flow.engine.ProcessEngine;
import org.sky.flow.engine.manager.ProcessEngineManager;
import org.sky.flow.generate.TestDelegate;
import org.sky.flow.manager.NodeManager;
import org.sky.flow.node.definition.enums.NodeType;

/**
 * 多线并行处理
 *
 * @author yj
 */
public class RunMuIndex {
    public static void main(String[] args) {

        ProcessCombination processCombination =
                ProcessEngineManager.generateProcessManager().generate("问题1");

        NodeManager nodeManager = ProcessEngineManager.generateNodeManager();

        ProcessEngine generate = ProcessEngineManager.generate();

        TestDelegate testDelegate = new TestDelegate();

        NodeCombination startEvent =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("虚拟开始节点")
                        .delegate(testDelegate)
                        .build();

        NodeCombination parallel =
                nodeManager
                        .generate()
                        .name("虚拟并行网关")
                        .type(NodeType.PARALLEL)
                        .delegate(testDelegate)
                        .build();

        // start event --- parallel
        startEvent.addTargetNode(parallel);
        processCombination.addStartNode(startEvent);

        NodeCombination endEvent =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("虚拟结束节点")
                        .delegate(testDelegate)
                        .build();

        NodeCombination importBlock =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("Import1")
                        .delegate(testDelegate)
                        .build();

        NodeCombination cumulate1 =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("Cumulate1")
                        .delegate(testDelegate)
                        .build();
        // parallel ---> import
        parallel.addTargetNode(importBlock);

        // import ---> cumulate
        importBlock.addTargetNode(cumulate1);

        NodeCombination function1 =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("function1")
                        .delegate(testDelegate)
                        .build();
        NodeCombination function11 =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("function1.1")
                        .delegate(testDelegate)
                        .build();
        // parallel ---> function11 --- function1
        parallel.addTargetNode(function11.addTargetNode(function1));

        NodeCombination function2 =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("function2")
                        .delegate(testDelegate)
                        .build();
        NodeCombination function21 =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("function2.1")
                        .delegate(testDelegate)
                        .build();

        // parallel ---> function2.1 ----> function2
        parallel.addTargetNode(function21.addTargetNode(function2));

        NodeCombination index1 =
                nodeManager
                        .generate()
                        .type(NodeType.INCLUSIVE)
                        .name("index1")
                        .delegate(testDelegate)
                        .build();

        cumulate1.addTargetNode(index1);
        function1.addTargetNode(index1);

        NodeCombination index2 =
                nodeManager
                        .generate()
                        .type(NodeType.INCLUSIVE)
                        .name("index2")
                        .delegate(testDelegate)
                        .build();

        index1.addTargetNode(index2);
        function2.addTargetNode(index2);

        NodeCombination filter1 =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("filter1")
                        .delegate(testDelegate)
                        .build();

        index2.addTargetNode(filter1);

        filter1.addTargetNode(endEvent);
        ProcessEngineManager.generateProcessStoreManager().save(processCombination);

        generate.startProcessById(processCombination.flowId());
    }
}
