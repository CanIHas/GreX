package can.i.has.latex

import can.i.has.latex.model.Renderable

import static can.i.has.latex.Commands.*
import static can.i.has.latex.FluentAPI.*
import static can.i.has.latex.Groups.*


class FluentAPITest extends GroovyTestCase {
    Map<String, Renderable> fixtures

    void setUp(){
        fixtures = [
                '''
\\documentclass{article}
\\someCommand[a,b]{c}
\\begin{document}
Hello, world
\\end{document}
''': document().withPreamble { preamble ->
                    preamble.add command("someCommand", "c", "a", "b")
                }.withContent { content ->
                    content.add text("Hello, world")
                },
            '''
\\begin{z}
\\emph{Ala ma kota}
\\\\
a kot ma Ale
\\end{z}
''': env("z", [
    emph("Ala ma kota"),
    newline(),
    text("a kot ma Ale")
])
        ]
    }

    void testRender() {
        fixtures.each { String expected, Renderable renderable ->
            assert expected.trim() == renderable.render().trim()
        }
    }
}
