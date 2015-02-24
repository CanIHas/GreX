package can.i.has.latex

import can.i.has.latex.model.Document

import static can.i.has.latex.model.fluent.StructureAPI.env

class FluentAPITest extends GroovyTestCase {

    void testFullDocument(){
        assertEquals("""\\documentclass{article}
\\someCommand[a,b]{c}
\\begin{document}
Hello, world
\\end{document}""", new Document().withPreamble {
            command("someCommand", "c", "a", "b")
        }.withContent {
            text("Hello, world")
        }.render().trim())
    }

    void testEnvironment(){
        assertEquals("""\\begin{z}
\\emph{Ala ma kota}
\\\\
a kot ma Ale
\\end{z}""", env("z").with {
            emph("Ala ma kota")
            newline()
            text("a kot ma Ale")
        }.render().trim())
    }
}
