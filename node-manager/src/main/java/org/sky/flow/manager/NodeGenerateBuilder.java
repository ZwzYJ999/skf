package org.sky.flow.manager;

import org.sky.flow.combination.NodeCombination;
import org.sky.flow.delegate.Delegate;
import org.sky.flow.listener.NodeListener;

/**
 * Node 最终生成接口
 *
 * @author yj
 */
public interface NodeGenerateBuilder {

    /**
     * description: build为Node组合对象.
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 15:31   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 15:31
     * @return org.sky.flow.node.definition.task.ServiceTaskNode
     */
    NodeCombination build();

    /**
     * description: setting delegate
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 15:24   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 15:24
     * @param delegate delegate
     * @return builder
     */
    NodeAttributeBuilder delegate(Delegate delegate);

    /**
     * description: setting before listener
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/21 17:58   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/21 17:58
     * @param nodeBeforeListener node before listener
     * @return org.sky.flow.manager.NodeAttributeBuilder
     */
    NodeAttributeBuilder beforeListener(NodeListener nodeBeforeListener);

    /**
     * description: setting after listener
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/21 17:58   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/21 17:58
     * @param nodeAfterListener node after listener
     * @return org.sky.flow.manager.NodeAttributeBuilder
     */
    NodeAttributeBuilder afterListener(NodeListener nodeAfterListener);
}
