package org.sky.flow.generate;

import org.sky.flow.context.Context;
import org.sky.flow.delegate.AbstractDelegateInvoke;

import lombok.SneakyThrows;

/**
 * 测试使用
 *
 * @author yj
 */
public class TestSleepDelegate extends AbstractDelegateInvoke {

    @SneakyThrows(value = Exception.class)
    @Override
    protected void invoke(Context context) {
        // Thread.sleep(1000L);
        context.delegateContext().put("a", 1);
        System.out.println(
                Thread.currentThread().getName() + "     " + context.nodeContext().nodeName());
    }
}
