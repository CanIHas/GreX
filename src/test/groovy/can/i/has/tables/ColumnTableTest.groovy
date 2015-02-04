package can.i.has.tables


class ColumnTableTest extends GroovyTestCase {
    void testToLaTeX() {
        assert new ColumnTable(
            header: ["a", "b", "c", "d"],
            data: [
                [1, 2, 3, 4],
                [5, 6, 7, "X"],
                ["X", "Y", 8, 9]
            ]
        ).toLaTeX().render().trim().replaceAll("\\s+", " ")=="""\\begin{tabular}{|c|c|c|c|}
\\hline

a & b & c & d \\\\

\\hline
\\hline

1 & 2 & 3 & 4 \\\\

\\hline

5 & 6 & 7 & X \\\\

\\hline

X & Y & 8 & 9 \\\\

\\hline

\\end{tabular}""".trim().replaceAll("\\s+", " ")
    }
}
