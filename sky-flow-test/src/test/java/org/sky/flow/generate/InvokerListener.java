package org.sky.flow.generate;

import org.sky.flow.context.Context;
import org.sky.flow.listener.NodeListener;

public class InvokerListener implements NodeListener {
    @Override
    public void listener(Context context) {
        System.out.println("测试一下");
    }
}
