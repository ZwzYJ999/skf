package org.sky.flow.run;

import java.time.Duration;
import java.time.LocalDateTime;

import org.sky.flow.combination.NodeCombination;
import org.sky.flow.combination.ProcessCombination;
import org.sky.flow.engine.ProcessEngine;
import org.sky.flow.engine.manager.ProcessEngineManager;
import org.sky.flow.generate.TestDelegate;
import org.sky.flow.manager.NodeManager;
import org.sky.flow.node.definition.enums.NodeType;

/**
 * 5000顺序节点
 *
 * @author yj
 */
public class Run5000 {
    public static void main(String[] args) {
        // 创建流程
        ProcessCombination processCombination =
                ProcessEngineManager.generateProcessManager().generate("第一条流程");

        NodeManager nodeManager = ProcessEngineManager.generateNodeManager();

        // 测试使用delegate
        TestDelegate delegate = new TestDelegate();

        int i = 5000;

        NodeCombination event = nodeManager.generate().type(NodeType.SERVICE_TASK).build();
        processCombination.addStartNode(event);

        while (i-- >= 0) {
            NodeCombination serviceTask =
                    nodeManager
                            .generate()
                            .type(NodeType.SERVICE_TASK)
                            .name("Node" + i)
                            .delegate(delegate)
                            .build();
            event.addTargetNode(serviceTask);
            event = serviceTask;
        }

        NodeCombination build = nodeManager.generate().type(NodeType.SERVICE_TASK).build();
        event.addTargetNode(build);

        ProcessEngineManager.generateProcessStoreManager().save(processCombination);
        ProcessEngine generate = ProcessEngineManager.generate();

        // int i1 = 10;
        // while (i1-- >= 1) {
        //     new Thread(
        //                     () -> {
        //                         LocalDateTime now = LocalDateTime.now();
        //                         generate.startProcessById(processCombination.flowId());
        //                         System.out.println(
        //                                 +Duration.between(now, LocalDateTime.now()).toMillis());
        //                     })
        //             .start();
        // }
        LocalDateTime now = LocalDateTime.now();
        generate.startProcessById(processCombination.flowId());
        System.out.println(+Duration.between(now, LocalDateTime.now()).toMillis());
    }
}
