package org.sky.flow.generate;

import org.sky.flow.context.Context;
import org.sky.flow.delegate.AbstractDelegateInvoke;

public class NumRemoveDelegate extends AbstractDelegateInvoke {
    @Override
    protected void invoke(Context context) {
        if (context.nodeContext().nodeName().equals("C")) {
            context.delegateContext()
                    .put(
                            "num",
                            Integer.parseInt(context.delegateContext().get("num").toString()) - 1);
        }
        context.delegateContext().put(context.nodeContext().nodeName(), 1);
        System.out.println(
                Thread.currentThread().getName() + "     " + context.nodeContext().nodeName());
    }
}
