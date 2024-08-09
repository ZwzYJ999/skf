package org.sky.flow.designer.parse;

import java.util.Map;

import lombok.Data;

/**
 * Node Parse
 *
 * @author yj
 */
@Data
public class NodeParse {

    /** Node ID */
    private String id;

    /** Node Name */
    private String name;

    /** type */
    private String type;

    /** delegate */
    private String delegate;

    /** before listener */
    private String beforeListener;

    /** after listener */
    private String afterListener;

    /** properties */
    private Map<String, Object> properties;
}
