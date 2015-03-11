package can.i.has.grex

import static can.i.has.grex.TestConstants.*

class WorkspaceTest extends GroovyTestCase {
    void testRenderingToFile(){
        def temp = tempWorkspace
        def doc = documentWithEntites
        temp.documents.add doc
        temp.render(doc.name)
        assertEquals(expectedLatex, temp.renderFile(doc.name+".tex").text)
    }
}
