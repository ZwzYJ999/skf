package org.sky.flow.node.definition;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.sky.flow.node.definition.enums.NodeType;

/**
 * Node顶层抽象
 *
 * @author yj
 */
public abstract class AbstractNode implements Node {
    /** Node ID */
    protected String id;

    /** Node Name */
    protected String name;

    /** Node 类型 */
    protected NodeType type;

    /**
     * description: All Generate
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 14:21   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 14:21
     * @param id Id
     * @param name Name
     * @param type Type
     */
    protected AbstractNode(String id, String name, NodeType type) {
        id = StringUtils.isBlank(id) ? UUID.randomUUID().toString() : id;
        this.id = id;
        name = StringUtils.isBlank(name) ? id : name;
        this.name = name;
        this.type = type;
    }

    /**
     * description: Type Generate
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/8 14:21   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/8 14:21
     * @param type type
     */
    protected AbstractNode(NodeType type) {
        // Id = Name
        this.id = UUID.randomUUID().toString();
        this.name = this.id;
        this.type = type;
    }

    @Override
    public NodeType type() {
        return this.type;
    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public boolean isParallel() {
        return false;
    }
}
