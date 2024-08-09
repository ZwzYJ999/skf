package org.sky.flow.generate;

import org.sky.flow.combination.NodeCombination;
import org.sky.flow.engine.manager.ProcessEngineManager;
import org.sky.flow.node.definition.enums.NodeType;

/**
 * @author yj
 */
public class NodeGenerateNormal {
    public static void main(String[] args) {
        NodeCombination combination =
                ProcessEngineManager.generateNodeManager()
                        // .id("测试")
                        // .name("测试名称")
                        .generate()
                        .type(NodeType.SERVICE_TASK)
                        .delegate(new TestDelegate())
                        .build();
        System.out.println("构建对象");
    }
}
