package org.sky.flow.ql.express;

import org.sky.flow.context.DelegateContext;

/**
 * Expression Handler
 *
 * @author yj
 */
public interface ExpressionHandler {

    /**
     * description: 执行表达式check
     *
     * <pre>
     * change history:
     * date             defect             person             comments
     * -----------------------------------------------------------------
     * 2023/8/10 16:23   ******              YJ                Create
     *
     * </pre>
     *
     * @author YJ
     * @date 2023/8/10 16:23
     * @param expression 表达式
     * @param delegateContext delegate context
     * @return boolean
     */
    boolean check(String expression, DelegateContext delegateContext);
}
