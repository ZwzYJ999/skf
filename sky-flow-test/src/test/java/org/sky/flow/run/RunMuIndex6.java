package org.sky.flow.run;

import org.sky.flow.combination.NodeCombination;
import org.sky.flow.combination.ProcessCombination;
import org.sky.flow.context.Context;
import org.sky.flow.delegate.Delegate;
import org.sky.flow.generate.BaseManager;
import org.sky.flow.node.definition.enums.NodeType;

import lombok.SneakyThrows;

/**
 * {A,B} -> C - > {D,E} 测试汇聚&并行网关
 *
 * @author YJ
 */
public class RunMuIndex6 extends BaseManager {
    public static void main(String[] args) {
        ProcessCombination processCombination = processManager.generate("测试汇聚&并行网关");
        Delegate delegate =
                new Delegate() {
                    @SneakyThrows
                    @Override
                    public void execute(Context context) {
                        System.out.println(
                                Thread.currentThread().getName()
                                        + "   "
                                        + context.nodeContext().nodeName());
                    }

                    @Override
                    public boolean specificStop(Context context) {
                        return false;
                    }
                };

        NodeCombination start =
                nodeManager
                        .generate()
                        .id("虚拟节点")
                        .name("虚拟节点")
                        .type(NodeType.PARALLEL)
                        .delegate(delegate)
                        .build();

        NodeCombination a =
                nodeManager
                        .generate()
                        .id("A")
                        .name("A")
                        .type(NodeType.SERVICE_TASK)
                        .delegate(delegate)
                        .build();

        NodeCombination b =
                nodeManager
                        .generate()
                        .id("B")
                        .name("B")
                        .type(NodeType.SERVICE_TASK)
                        .delegate(delegate)
                        .build();

        NodeCombination c =
                nodeManager
                        .generate()
                        .id("C")
                        .name("C")
                        .type(NodeType.INCLUSIVE_PARALLEL)
                        .delegate(delegate)
                        .build();

        a.addTargetNode(c);
        b.addTargetNode(c);

        NodeCombination d =
                nodeManager
                        .generate()
                        .id("D")
                        .name("D")
                        .type(NodeType.SERVICE_TASK)
                        .delegate(delegate)
                        .build();

        NodeCombination e =
                nodeManager
                        .generate()
                        .id("E")
                        .name("E")
                        .type(NodeType.SERVICE_TASK)
                        .delegate(delegate)
                        .build();

        c.addTargetNode(d);
        c.addTargetNode(e);

        start.addTargetNode(a).addTargetNode(b);

        processCombination.addStartNode(start);

        processStoreManager.save(processCombination);
        processEngine.startProcessById(processCombination.flowId());
    }
}
