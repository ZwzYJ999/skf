package org.sky.flow.generate;

import org.sky.flow.context.Context;
import org.sky.flow.delegate.AbstractDelegateInvoke;

import lombok.SneakyThrows;

/**
 * 测试使用
 *
 * @author yj
 */
public class TestDelegate extends AbstractDelegateInvoke {

    @SneakyThrows(value = Exception.class)
    @Override
    protected void invoke(Context context) {
        context.delegateContext().put(context.nodeContext().nodeName(), 1);
        System.out.println(
                Thread.currentThread().getName() + "     " + context.nodeContext().nodeName());
    }
}
