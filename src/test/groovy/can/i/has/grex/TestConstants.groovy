package can.i.has.grex

import can.i.has.grex.experiments.Result
import can.i.has.grex.experiments.new_model.Experiment
import can.i.has.grex.experiments.new_model.config.FullSearchProvider
import can.i.has.grex.experiments.runner.SingleThreadExperimentRunner
import can.i.has.grex.latex.model.Document
import can.i.has.grex.latex.model.contents.FileRenderable
import can.i.has.grex.latex.model.contents.StringRenderable
import can.i.has.grex.latex.model.sections.DocumentSection
import can.i.has.grex.latex.model.sections.SectionLevel
import can.i.has.grex.latex.model.structure.Command
import can.i.has.grex.latex.model.structure.Environment
import can.i.has.grex.latex.model.structure.Preamble


class TestConstants {
    static String getExpectedLatex(){
        TestConstants.classLoader.getResourceAsStream("expected.tex").text
    }

    static <R> Experiment<R> getExampleExperiment(Closure<R> expBody){
        new Experiment(
            "experimentAndFullSearch",
            expBody,
            new FullSearchProvider<R>(
                ["a", "b", "c"],
                [
                    a: [1, 2],
                    b: ["3", "4"],
                    c: ["x", "y"]
                ],
                [
                    c: [x: 5, y: "6"]
                ]
            ),
            new SingleThreadExperimentRunner()
        )
    }

    static Workspace getTempWorkspace(){
        Workspace out = new Workspace()
        out.root = File.createTempDir()
        out.rawFile("x", "y").text = "Some text \nin raw file"
        out.root.deleteOnExit()
        out
    }

    static Document getDocumentWithEntites(){
        Document out = new Document()
        out.name = "doc1"
        out.documentclass = new Command("documentclass", [], [new StringRenderable("clazz")])
        out.preamble = new Preamble()
        out.preamble.content.add new Command("usepackage", [], [new StringRenderable("somepkg")])
        out.preamble.content.add new Command("usepackage", ["op1", "op2"], [new StringRenderable("anotherpkg")])
        out.preamble.title = "Title"
        out.preamble.author = "Author"
        out.preamble.date = "Date"
        out.body = new Environment("document")
        out.body.content.add new Command("maketitle")
        def abstr = new Environment("abstract")
        abstr.content.add new StringRenderable("abstract text")
        out.body.content.add abstr
        out.body.content.add new Command("tableofcontents")
        def env = new Environment("someEnv", [new StringRenderable("ba")])
        env.content = []
        env.content.add new StringRenderable("ABC\\newlineDEF")
        env.content.add new FileRenderable(["x", "y"], true, ["pkg1"])
        env.content.add new Command("c", ["opt"], [new StringRenderable("arg")], ["pkg2"].toSet())
        out.body.content.add env
        def section = new DocumentSection(SectionLevel.SECTION, "Section", false)
        section.content.add new StringRenderable("section text")
        def paragraph = new DocumentSection(SectionLevel.PARAGRAPH, "Paragraph", true)
        paragraph.content.add new StringRenderable("paragraph text")
        section.content.add paragraph
        out.body.content.add section
        out
    }

    static <R extends Result> Workspace getWorkspaceWithEntities(Closure<R> expBody=null){
        Workspace out = new Workspace()
        out.setRoot("root")
        out.documents.add documentWithEntites
        out.experiments.add getExampleExperiment(expBody)
        out
    }

    static <R extends Result> Workspace getWorkspaceWithGreX(Closure<R> expBody=null){
        new GreX().eval {
            workspace(root: "root") {
                experiments {
                    experiment(name: "experimentAndFullSearch") {
                        experimentBody = expBody
                        config = fullSearch {
                            params = ["a", "b", "c"]
                            domains = [
                                a: [1, 2],
                                b: ["3", "4"],
                                c: ["x", "y"]
                            ]
                            values = [
                                c: [x: 5, y: "6"]
                            ]
                        }
                        runner = singleThread()
                    }
                }
                documents {
                    document(name: "doc1", documentclass: "clazz") {
                        preamble(title: "Title") {
                            author = "Author"
                            date = "Date"
                            usePackage("somepkg")
                            usePackage(package: "anotherpkg", options: ["op1", "op2"])
                        }
                        documentBody {
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
    }
}
