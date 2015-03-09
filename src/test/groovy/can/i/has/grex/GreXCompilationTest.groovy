package can.i.has.grex

import can.i.has.grex.latex.model.Document
import can.i.has.grex.latex.model.contents.FileRenderable
import can.i.has.grex.latex.model.contents.StringRenderable
import can.i.has.grex.latex.model.sections.DocumentSection
import can.i.has.grex.latex.model.sections.SectionLevel
import can.i.has.grex.latex.model.structure.Command
import can.i.has.grex.latex.model.structure.Environment
import can.i.has.grex.latex.model.structure.Preamble

class GreXCompilationTest extends GroovyTestCase {
    Workspace expected

    void setUp(){
        expected = new Workspace()
        expected.setRoot("root")
        def doc = new Document()
        doc.documentclass = new Command("documentclass", [], [new StringRenderable("clazz")])
        doc.preamble = new Preamble()
        doc.preamble.content.add new Command("usepackage", [], [new StringRenderable("somepkg")])
        doc.preamble.content.add new Command("usepackage", ["op1", "op2"], [new StringRenderable("anotherpkg")])
        doc.preamble.title = "Title"
        doc.preamble.author = "Author"
        doc.preamble.date = "Date"
        doc.body = new Environment("document")
        doc.body.content.add new Command("maketitle")
        def abstr = new Environment("abstract")
        abstr.content.add new StringRenderable("abstract text")
        doc.body.content.add abstr
        doc.body.content.add new Command("tableofcontents")
        def env = new Environment("someEnv", [new StringRenderable("ba")])
        env.content = []
        env.content.add new StringRenderable("ABC\\newlineDEF")
        env.content.add new FileRenderable(["x", "y"], true, ["pkg1"])
        env.content.add new Command("c", ["opt"], [new StringRenderable("arg")], ["pkg2"].toSet())
        doc.body.content.add env
        def section = new DocumentSection(SectionLevel.SECTION, "Section", false)
        section.content.add new StringRenderable("section text")
        def paragraph = new DocumentSection(SectionLevel.PARAGRAPH, "Paragraph", true)
        paragraph.content.add new StringRenderable("paragraph text")
        section.content.add paragraph
        doc.body.content.add section
        expected.documents.add(doc)
    }

    void testE2E(){
        assertEquals(expected, new GreX().eval {
                workspace(root: "root") {
                    documents {
                        document(documentclass: "clazz") {
                            preamble(title: "Title") {
                                author = "Author"
                                date = "Date"
                                usePackage("somepkg")
                                usePackage(package: "anotherpkg", options: ["op1", "op2"])
                            }
                            body {
                                title()
                                paperAbstract {
                                    text "abstract text"
                                }
                                tableOfContents()
                                env(name: "someEnv", beginArgs: ["ba"]) {
                                    text("ABC\nDEF", escape: true)
                                    raw(path: ["x", "y"], escape: true, neededPackages: ["pkg1"])
                                    command("c", options: ["opt"], args: ["arg"], neededPackages: ["pkg2"])
                                }
                                documentSection(level: SectionLevel.SECTION, title: "Section"){
                                    text "section text"
                                    paragraph(ignored: true, title: "Paragraph"){
                                        text "paragraph text"
                                    }
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}
