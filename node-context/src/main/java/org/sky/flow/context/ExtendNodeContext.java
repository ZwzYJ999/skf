package org.sky.flow.context;

import java.util.Map;

/**
 * Node Context
 *
 * @author yj
 */
public class ExtendNodeContext extends AbstractSlotNodeContext implements NodeContext {

    /** properties */
    private Map<String, Object> properties;

    /** node name */
    private String nodeName;

    /** node id */
    private String nodeId;

    /** node type */
    private String nodeType;

    /**
     * description: create extend node context
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 17:45   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/9 17:45
     * @param globalContext global context
     */
    public ExtendNodeContext(GlobalContext globalContext) {
        super(globalContext);
    }

    /**
     * description: 初始化值
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/9 17:47   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/9 17:47
     * @param name name
     * @param id id
     * @param nodeType node type
     * @param properties node 属性
     */
    public void init(String name, String id, String nodeType, Map<String, Object> properties) {
        this.nodeName = name;
        this.nodeId = id;
        this.nodeType = nodeType;
        this.properties = properties;
    }

    @Override
    public NodeContext nodeContext() {
        return this;
    }

    @Override
    public String nodeName() {
        return nodeName;
    }

    @Override
    public String nodeId() {
        return nodeId;
    }

    @Override
    public String nodeType() {
        return nodeType;
    }

    @Override
    public Object properties(String key) {
        return properties.get(key);
    }

    @Override
    public Map<String, Object> properties() {
        return properties;
    }
}
