package can.i.has.tables


class TableTest extends GroovyTestCase {
    void testSimpleScenario(){
        println new SimpleTable(
            new Sheet([['x','y','z'], [1,2,3], [4,5,6], [7,8,9]]),
            new SimpleHeaderStyle(),
            new ToStringRenderer()
        ).render()
    }
}
