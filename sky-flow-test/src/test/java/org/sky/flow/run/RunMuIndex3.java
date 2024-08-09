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
public class RunMuIndex3 {

    public static void main(String[] args) {
        ProcessCombination processCombination =
                ProcessEngineManager.generateProcessManager().generate("问题3");

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

        NodeCombination function2 =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("Function2")
                        .delegate(testDelegate)
                        .build();

        startEvent.addTargetNode(function2);

        NodeCombination parallel1 =
                nodeManager
                        .generate()
                        .type(NodeType.PARALLEL)
                        .name("虚拟并行节点")
                        .delegate(testDelegate)
                        .build();

        function2.addTargetNode(parallel1);

        NodeCombination index5 =
                nodeManager
                        .generate()
                        .type(NodeType.INCLUSIVE)
                        .name("index5")
                        .delegate(testDelegate)
                        .build();

        parallel1.addTargetNode(index5);

        NodeCombination cumulate1 =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("cumulate1")
                        .delegate(testDelegate)
                        .build();

        NodeCombination parallel2 =
                nodeManager
                        .generate()
                        .type(NodeType.PARALLEL)
                        .name("虚拟并行节点2")
                        .delegate(testDelegate)
                        .build();

        parallel1.addTargetNode(cumulate1);
        cumulate1.addTargetNode(parallel2);

        NodeCombination compress3 =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("compress3")
                        .delegate(testDelegate)
                        .build();

        parallel2.addTargetNode(compress3);

        NodeCombination parallel3 =
                nodeManager
                        .generate()
                        .type(NodeType.PARALLEL)
                        .name("虚拟并行节点3")
                        .delegate(testDelegate)
                        .build();

        compress3.addTargetNode(parallel3);

        NodeCombination filter10 =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("filter10")
                        .delegate(testDelegate)
                        .build();

        parallel3.addTargetNode(filter10);
        filter10.addTargetNode(index5);

        NodeCombination filter9 =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("filter9")
                        .delegate(testDelegate)
                        .build();

        parallel3.addTargetNode(filter9);

        NodeCombination index3 =
                nodeManager
                        .generate()
                        .type(NodeType.INCLUSIVE)
                        .name("index3")
                        .delegate(testDelegate)
                        .build();

        filter9.addTargetNode(index3);

        parallel2.addTargetNode(index3);

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

        NodeCombination compress5 =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("compress5")
                        .delegate(testDelegate)
                        .build();

        filter6.addTargetNode(compress1);
        index5.addTargetNode(compress5);

        NodeCombination union2 =
                nodeManager
                        .generate()
                        .type(NodeType.INCLUSIVE)
                        .name("union2")
                        .delegate(testDelegate)
                        .build();

        compress1.addTargetNode(union2);
        compress5.addTargetNode(union2);

        NodeCombination endEvent =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("虚拟结束节点")
                        .delegate(testDelegate)
                        .build();

        union2.addTargetNode(endEvent);

        processCombination.addStartNode(startEvent);

        ProcessEngineManager.generateProcessStoreManager().save(processCombination);
        generate.startProcessById(processCombination.flowId());
    }
}
