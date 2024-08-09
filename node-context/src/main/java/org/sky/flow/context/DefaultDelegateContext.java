package org.sky.flow.context;

import java.io.Serial;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.ql.util.express.DefaultContext;

import lombok.EqualsAndHashCode;

/**
 * Default Delegate Context
 *
 * @author yj
 */
@EqualsAndHashCode(callSuper = false)
public class DefaultDelegateContext extends DefaultContext<String, Object>
        implements DelegateContext, Serializable {

    @Serial private static final long serialVersionUID = -4361228532406107818L;

    /** default context */
    private final transient ConcurrentMap<String, Object> defaultContext;

    /** generate */
    public DefaultDelegateContext() {
        this.defaultContext = new ConcurrentHashMap<>();
    }

    /**
     * generate
     *
     * @param defaultContext context map
     */
    public DefaultDelegateContext(ConcurrentMap<String, Object> defaultContext) {
        this.defaultContext = defaultContext;
    }

    @Override
    public Object get(Object key) {
        return defaultContext.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        return defaultContext.put(key, value);
    }

    @Override
    public String toString() {
        return defaultContext.toString();
    }
}
