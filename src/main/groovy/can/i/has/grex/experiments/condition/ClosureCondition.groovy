package can.i.has.grex.experiments.condition

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(excludes = ["closure"])
@ToString(excludes = ["closure"])
class ClosureCondition implements Condition{
    final String expression
    final Closure<Boolean> closure

    ClosureCondition(String expression) {
        this.expression = expression
        closure = Eval.me(expression)
    }

    @Override
    boolean isSatisfied() {
        closure.call()
    }

    @Override
    String toPrettyString() {
        expression
    }

    static Condition condition(String expr){
        new ClosureCondition(expr)
    }
}
