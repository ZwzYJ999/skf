package org.sky.flow.designer.parse.json;

import java.util.List;

import org.sky.flow.designer.parse.NodeParse;
import org.sky.flow.designer.parse.RelationParse;

import lombok.Data;

/**
 * Json Parse
 *
 * @author yj
 */
@Data
public class JsonParse {
    /** 节点信息 */
    private List<NodeParse> nodes;

    /** 关系信息 */
    private List<RelationParse> relations;
}
