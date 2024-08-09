package org.sky.flow.designer.parse;

import lombok.Data;

/**
 * Relation Parse
 *
 * @author yj
 */
@Data
public class RelationParse {
    /** source id */
    private String sourceId;

    /** target id */
    private String targetId;

    /** 表达式 */
    private String expression;
}
