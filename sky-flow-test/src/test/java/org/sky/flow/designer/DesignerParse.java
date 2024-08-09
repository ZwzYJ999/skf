package org.sky.flow.designer;

import java.io.IOException;

import org.sky.flow.combination.ProcessCombination;
import org.sky.flow.designer.parse.json.JacksonParseManager;
import org.sky.flow.generate.BaseManager;

public class DesignerParse extends BaseManager {

    public static void main(String[] args) throws IOException {
        JacksonParseManager jacksonParseManager =
                new JacksonParseManager(nodeManager, processManager);

        ProcessCombination processCombination =
                jacksonParseManager.parseToProcessCombination(
                        "/Users/yj/core/sky/sky-flow/sky-flow-test/src/test/resources/demo/p1.json",
                        "测试一下");

        processStoreManager.save(processCombination);
        processEngine.startProcessById(processCombination.flowId());
        processStoreManager.clear();
    }
}
