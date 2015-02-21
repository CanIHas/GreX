package can.i.has.tables

import can.i.has.tables.impl.SimpleSheet
import can.i.has.tables.impl.HeaderRowStyle
import can.i.has.tables.impl.SimpleTable
import can.i.has.tables.impl.ToStringRenderer


class TableTest extends GroovyTestCase {
    void testSimpleScenario(){
        assertEquals(new SimpleTable(
            new SimpleSheet([['x','y','z'], [1,2,3], [4,5,6], [7,8,9]]),
            new HeaderRowStyle(),
            new ToStringRenderer()
        ).render().trim(), """
\\begin{tabular}{ccc}
\\hhline{---}
\\multicolumn{1}{|c}{x} & \\multicolumn{1}{c}{y} & \\multicolumn{1}{c|}{z}\\\\
\\hhline{===}
\\multicolumn{1}{|c|}{1} & \\multicolumn{1}{|c|}{2} & \\multicolumn{1}{|c|}{3}\\\\
\\hhline{~~~}
\\multicolumn{1}{|c|}{4} & \\multicolumn{1}{|c|}{5} & \\multicolumn{1}{|c|}{6}\\\\
\\hhline{~~~}
\\multicolumn{1}{|c|}{7} & \\multicolumn{1}{|c|}{8} & \\multicolumn{1}{|c|}{9}\\\\
\\hhline{---}
\\end{tabular}
""".trim())
    }
}
