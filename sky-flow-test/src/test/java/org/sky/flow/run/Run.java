package org.sky.flow.run;

import java.time.Duration;
import java.time.LocalDateTime;

import org.sky.flow.combination.NodeCombination;
import org.sky.flow.combination.ProcessCombination;
import org.sky.flow.context.Context;
import org.sky.flow.delegate.Delegate;
import org.sky.flow.engine.ProcessEngine;
import org.sky.flow.engine.manager.ProcessEngineManager;
import org.sky.flow.generate.TestSleepDelegate;
import org.sky.flow.manager.NodeManager;
import org.sky.flow.node.definition.enums.NodeType;

/**
 * 流程启动以及保存
 *
 * @author yj
 */
public class Run {
    public static void main(String[] args) {
        // 创建流程
        ProcessCombination processCombination =
                ProcessEngineManager.generateProcessManager().generate("第一条流程");

        NodeManager nodeManager = ProcessEngineManager.generateNodeManager();

        // 测试使用delegate
        Delegate delegate = new TestSleepDelegate();
        // 创建开始节点
        NodeCombination start =
                nodeManager
                        .generate()
                        .name("开始节点")
                        .delegate(delegate)
                        .type(NodeType.SERVICE_TASK)
                        .build();

        // 创建并行节点
        NodeCombination pa = nodeManager.generate().type(NodeType.PARALLEL).build();
        start.addTargetNode(pa);

        // 创建Service Task节点 A
        NodeCombination a =
                nodeManager
                        .generate()
                        .name("A")
                        .delegate(delegate)
                        .type(NodeType.SERVICE_TASK)
                        .build();

        // 创建service task节点 B
        NodeCombination b =
                nodeManager
                        .generate()
                        .name("B")
                        .delegate(delegate)
                        .type(NodeType.SERVICE_TASK)
                        .build();

        pa.addTargetNode(a).addTargetNode(b);

        // 创建汇聚节点
        NodeCombination in =
                nodeManager
                        .generate()
                        .name("汇聚网关")
                        .delegate(delegate)
                        .type(NodeType.INCLUSIVE)
                        .build();

        // in 应该是两个slot.
        a.addTargetNode(in);
        b.addTargetNode(in);

        // 创建结束节点
        NodeCombination end =
                nodeManager
                        .generate()
                        .name("结束节点")
                        .delegate(delegate)
                        .type(NodeType.SERVICE_TASK)
                        .build();
        in.addTargetNode(end);

        processCombination.addStartNode(start);

        // 保存到store中

        ProcessEngineManager.generateProcessStoreManager().save(processCombination);

        // 创建流程引擎
        ProcessEngine generate = ProcessEngineManager.generate();

        LocalDateTime now = LocalDateTime.now();
        Context context = generate.startProcessById(processCombination.flowId());
        System.out.println(context.delegateContext());
        System.out.println("执行时间: " + Duration.between(now, LocalDateTime.now()).toMillis());
    }
}
