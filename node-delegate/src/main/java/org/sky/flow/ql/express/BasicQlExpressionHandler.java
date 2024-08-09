package org.sky.flow.ql.express;

import com.ql.util.express.ExpressRunner;

import org.sky.flow.context.DelegateContext;

import lombok.SneakyThrows;

/**
 * Expression Handler
 *
 * @author yj
 */
public class BasicQlExpressionHandler implements ExpressionHandler {

    /** Advanced Expression Runner */
    private final ExpressRunner expressRunner;

    /** init exp */
    public BasicQlExpressionHandler() {
        expressRunner = new ExpressRunner(true, false);
    }

    @SneakyThrows(value = Exception.class)
    @Override
    public boolean check(String expression, DelegateContext delegateContext) {
        return (boolean) expressRunner.execute(expression, delegateContext, null, true, false);
    }
}
