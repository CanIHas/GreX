package can.i.has.grex.latex


import static can.i.has.grex.TestConstants.*

class ModelTest extends GroovyTestCase {

    void testRender() {
        assert expectedLatex == documentWithEntites.render(tempWorkspace)
    }
}
