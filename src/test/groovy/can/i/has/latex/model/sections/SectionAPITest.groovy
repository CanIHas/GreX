package can.i.has.latex.model.sections

import static can.i.has.latex.model.fluent.StructureAPI.env


class SectionAPITest extends GroovyTestCase {
    void testSimple(){
        assertEquals('''
\\begin{X}
\\section{S}
\\paragraph{P}
\\end{X}
'''.trim(), env("X").with {
            section("S") {
                paragraph("P")
            }
        }.render().trim()
        )
    }

    void testAllLevels(){
        assertEquals("""
\\begin{E}
\\part{Part}
1
\\chapter{Chapter}
2
\\section{Section}
\\subsection{Subsection}
3
\\subsubsection{SSS}
4
\\paragraph{P}
5
\\subparagraph{SP}
6
\\end{E}
""".trim(), env("E").with {
            part("Part") {
                text("1")
                chapter("Chapter") {
                    text("2")
                    section("Section"){
                        subsection("Subsection") // closure isn't needed, we can create subsection content in section scope
                        text("3")
                        subsubsection("SSS") {
                            text("4")
                            paragraph("P") {
                                text("5")
                                subparagraph("SP") {
                                    text("6")
                                }

                            }
                        }
                    }
                }
            }
        }.render().trim()
        )
    }
}
