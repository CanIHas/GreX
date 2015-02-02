package can.i.has.experiments.condition


class ClosureConditionTest extends GroovyTestCase {
    void testToPrettyString() {
        def txt = "{ -> 2 % 2 == 0 }"
        def cond = new ClosureCondition(txt)
        assert cond.toPrettyString() == txt
        assert cond.isSatisfied()
    }
}
