package can.i.has.latex.tables

import can.i.has.latex.tables.impl.SimpleSheet
import can.i.has.latex.tables.impl.SimpleTable
import can.i.has.latex.tables.impl.ToStringRenderer
import can.i.has.latex.tables.impl.UniformStyle


class MultiTableBuilderTest extends GroovyTestCase {
    /**
     *  expected something like this:
     *  ======================
     *  ||   || x || y || z ||
     *  ======================
     *  || a || 1 |  2 |  3 |
     *  ======---------------
     *  || b || 4 | 5  |  6 | c
     *  ======---------------
     *       | 7  | 8  | 9  | d
     *       ----------------
     */
    void testSimple(){
        def builder = new MultiTableBuilder()
        def table = builder.to(
            new SimpleTable(
                new SimpleSheet(
                    [
                        [1, 2, 3],
                        [4, 5, 6],
                        [7, 8, 9]
                    ]
                ), new UniformStyle(), ToStringRenderer.instance
            )
        ).appendLeft(
            new SimpleTable(
                new SimpleSheet([
                    ["a"],
                    ["b"],
                ]),
                new UniformStyle(2), ToStringRenderer.instance
            )
        ).appendRight(
            new SimpleTable(
                new SimpleSheet([
                    ["c"],
                    ["d"]
                ]),
                new UniformStyle(0, 0, 0), ToStringRenderer.instance
            ),
            MultiTableBuilder.VerticalAlginment.BOTTOM
        ).appendTop(
            new SimpleTable(
                new SimpleSheet([
                    ["", "x", "y", "z"]
                ]) , new UniformStyle(2, 2, 2), ToStringRenderer.instance
            )
        ).build()
        assertEquals(table.render().trim(), """\\begin{tabular}{ccccc}
\\hhline{====~}
\\multicolumn{1}{||c||}{} & \\multicolumn{1}{c||}{x} & \\multicolumn{1}{c||}{y} & \\multicolumn{1}{c||}{z} & \\multicolumn{1}{c}{}\\\\
\\hhline{====~}
\\multicolumn{1}{||c||}{a} & \\multicolumn{1}{|c|}{1} & \\multicolumn{1}{c|}{2} & \\multicolumn{1}{c|}{3} & \\multicolumn{1}{c}{}\\\\
\\hhline{----~}
\\multicolumn{1}{||c||}{b} & \\multicolumn{1}{|c|}{4} & \\multicolumn{1}{c|}{5} & \\multicolumn{1}{c|}{6} & \\multicolumn{1}{c}{c}\\\\
\\hhline{=---~}
\\multicolumn{1}{c}{} & \\multicolumn{1}{|c|}{7} & \\multicolumn{1}{c|}{8} & \\multicolumn{1}{c|}{9} & \\multicolumn{1}{c}{d}\\\\
\\hhline{~---~}
\\end{tabular}
""".trim())
    }
}
