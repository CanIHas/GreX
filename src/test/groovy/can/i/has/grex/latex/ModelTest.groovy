package can.i.has.grex.latex

import can.i.has.grex.latex.model.*
import can.i.has.grex.latex.model.contents.StringRenderable
import can.i.has.grex.latex.model.structure.Command
import can.i.has.grex.latex.model.structure.Environment
import org.junit.Ignore

//todo: fix constructor issues
@Ignore
class ModelTest extends GroovyTestCase {
    Map<String, Renderable> fixtures

    void setUp(){
        fixtures = [
                '''
\\documentclass{article}
\\someCommand[a,b]{c}
\\begin{document}
Hello, world
\\end{document}
''': new Document(
                    new Command("documentclass", [], [new StringRenderable("article")]),
                    [ new Command("someCommand", ["a", "b"], [new StringRenderable("c")]) ],
                    [ new StringRenderable("Hello, world") ]
                ),
            '''
\\begin{z}
\\emph{Ala ma kota}
\\\\
a kot ma Ale
\\end{z}
''': new Environment("z", [
    new Command("emph", [], [new StringRenderable("Ala ma kota")]),
    new Command("\\", [], []),
    new StringRenderable("a kot ma Ale")
])
        ]
    }

    void testRender() {
        fixtures.each { String expected, Renderable renderable ->
            assert expected.trim() == renderable.render().trim()
        }
    }
}
