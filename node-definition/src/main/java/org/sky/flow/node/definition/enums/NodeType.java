package org.sky.flow.node.definition.enums;

import lombok.Getter;

/**
 * Node 类型
 *
 * @author yj
 */
@Getter
public enum NodeType {

    /** Service Task */
    SERVICE_TASK(NodeGroup.TASK),

    /** 包含网关. */
    INCLUSIVE(NodeGroup.GATEWAY),
    /** 并行网关 */
    PARALLEL(NodeGroup.GATEWAY),
    /** 排他网关 */
    EXCLUSIVE(NodeGroup.GATEWAY),
    /** 汇聚网关&并行网关 */
    INCLUSIVE_PARALLEL(NodeGroup.GATEWAY),
    ;

    /** 类型所属组 */
    private final NodeGroup group;

    /**
     * Node Type
     *
     * @param group 组
     */
    NodeType(NodeGroup group) {
        this.group = group;
    }
}
