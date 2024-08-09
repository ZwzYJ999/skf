package org.sky.flow.run;

import org.sky.flow.combination.NodeCombination;
import org.sky.flow.combination.ProcessCombination;
import org.sky.flow.engine.ProcessEngine;
import org.sky.flow.engine.manager.ProcessEngineManager;
import org.sky.flow.generate.TestDelegate;
import org.sky.flow.manager.NodeManager;
import org.sky.flow.node.definition.enums.NodeType;

/**
 * @author yj
 */
public class RunMuIndex2 {
    public static void main(String[] args) {
        ProcessCombination processCombination =
                ProcessEngineManager.generateProcessManager().generate("问题2");

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

        NodeCombination endEvent =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("虚拟结束节点")
                        .delegate(testDelegate)
                        .build();

        NodeCombination custom1 =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("Custom1")
                        .delegate(testDelegate)
                        .build();

        NodeCombination function3 =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("function3")
                        .delegate(testDelegate)
                        .build();

        NodeCombination parallel =
                nodeManager
                        .generate()
                        .type(NodeType.PARALLEL)
                        .name("并行网关")
                        .delegate(testDelegate)
                        .build();

        NodeCombination filter3 =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("filter3")
                        .delegate(testDelegate)
                        .build();

        NodeCombination inclusive =
                nodeManager
                        .generate()
                        .type(NodeType.INCLUSIVE)
                        .name("汇聚网关")
                        .delegate(testDelegate)
                        .build();

        parallel.addTargetNode(filter3.addTargetNode(inclusive));

        inclusive.addTargetNode(endEvent);

        startEvent.addTargetNode(custom1.addTargetNode(function3.addTargetNode(parallel)));

        processCombination.addStartNode(startEvent);

        ProcessEngineManager.generateProcessStoreManager().save(processCombination);

        generate.startProcessById(processCombination.flowId());
    }
}
