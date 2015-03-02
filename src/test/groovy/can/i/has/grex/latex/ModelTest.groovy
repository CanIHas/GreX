package can.i.has.grex.latex

import can.i.has.grex.Workspace
import can.i.has.grex.latex.model.*
import can.i.has.grex.latex.model.contents.FileRenderable
import can.i.has.grex.latex.model.contents.StringRenderable
import can.i.has.grex.latex.model.sections.DocumentSection
import can.i.has.grex.latex.model.sections.SectionLevel
import can.i.has.grex.latex.model.structure.Command
import can.i.has.grex.latex.model.structure.Environment
import can.i.has.grex.latex.model.structure.Preamble

class ModelTest extends GroovyTestCase {

    Document document
    Workspace workspace
    String expected

    void setUpWorkspace(){
        workspace = new Workspace()
        workspace.root = File.createTempDir()
        workspace.rawFile("x", "y").text = "Some text \nin raw file"
        workspace.root.deleteOnExit()
    }

    void setUpDocument(){
        //todo: it is literal copy from GreXCompilationTest
        document = new Document()
        document.documentclass = new Command("documentclass", [], [new StringRenderable("clazz")])
        document.preamble = new Preamble()
        document.preamble.content.add new Command("usepackage", [], [new StringRenderable("somepkg")])
        document.preamble.content.add new Command("usepackage", ["op1", "op2"], [new StringRenderable("anotherpkg")])
        document.preamble.title = "Title"
        document.preamble.author = "Author"
        document.preamble.date = "Date"
        document.body = new Environment("document")
        document.body.content.add new Command("maketitle")
        def abstr = new Environment("abstract")
        abstr.content.add new StringRenderable("abstract text")
        document.body.content.add abstr
        document.body.content.add new Command("tableofcontents")
        def env = new Environment("someEnv", [new StringRenderable("ba")])
        env.content = []
        env.content.add new StringRenderable("ABC\\newlineDEF")
        env.content.add new FileRenderable(["x", "y"], true, ["pkg1"])
        env.content.add new Command("c", ["opt"], [new StringRenderable("arg")], ["pkg2"].toSet())
        document.body.content.add env
        def section = new DocumentSection(SectionLevel.SECTION, "Section", false)
        section.content.add new StringRenderable("section text")
        def paragraph = new DocumentSection(SectionLevel.PARAGRAPH, "Paragraph", true)
        paragraph.content.add new StringRenderable("paragraph text")
        section.content.add paragraph
        document.body.content.add section
    }

    void setUp(){
        setUpWorkspace()
        setUpDocument()
        expected = this.class.classLoader.getResourceAsStream("expectedForModel.tex").text
    }

    void testRender() {
        assertEquals(expected, document.render(workspace))
    }
}
