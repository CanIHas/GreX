package can.i.has.grex

import static can.i.has.grex.TestConstants.*

class GreXCompilationTest extends GroovyTestCase {

    void testE2E(){
        assert workspaceWithEntities == workspaceWithGreX
    }
}
