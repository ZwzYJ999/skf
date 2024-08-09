package org.sky.flow.test;

import java.util.concurrent.ConcurrentHashMap;

import org.sky.flow.combination.NodeCombination;
import org.sky.flow.combination.ProcessCombination;
import org.sky.flow.context.Context;
import org.sky.flow.generate.BaseManager;
import org.sky.flow.generate.TestDelegate;
import org.sky.flow.node.definition.enums.NodeType;

/** 编码测试 */
public class Normal extends BaseManager {

    public static void main(String[] args) {

        ProcessCombination processCombination = processManager.generate("测试Flow");

        NodeCombination startEvent =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("开始节点")
                        .beforeListener(context -> System.out.println("开始节点的before listener"))
                        .afterListener(context -> System.out.println("开始节点的after listener"))
                        .delegate(new TestDelegate())
                        .build();

        NodeCombination serviceTask =
                nodeManager
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .name("A")
                        .delegate(new TestDelegate())
                        .build();

        processCombination.addStartNode(startEvent.addTargetNode(serviceTask));
        processStoreManager.save(processCombination);

        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        map.put("start", "2");
        Context context = processEngine.startProcessById(processCombination.flowId(), map);
        System.out.println(context.delegateContext());
    }
}
