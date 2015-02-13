package can.i.has.tables

abstract class AbstractTable implements Table{
    static final String NEWLINE = "\\\\ \n"

    @Override
    String render() {
        def out = "\\begin{tabular}{${'c'*sheet.columns}}\n" << ""
        sheet.delegate.eachWithIndex { List row, int i ->
            out << renderAboveRow(i)
            out << renderRow(row, i) << NEWLINE
        }
        out << renderAboveRow(sheet.rows)
        out << "\\end{tabular}"
        out.toString()
    }

    protected String cell(String borders, String content){
        "\\multicolumn{1}{$borders}{$content}"
    }

    protected String renderRow(List cells, int row) {
        int height = sheet.rows //todo: maybe add width/height properties to Table API? ; remove sheet, add getAt(x, y)
        int width = sheet.columns
        def toJoin = []
        cells.eachWithIndex { def entry, int i ->
            Border border = sheetStyle.getBorder(row, i, height, width)
            toJoin << cell("${border.leftSeparator}c${border.rightSeparator}", contentRenderer.renderCell(entry, row, i))
        }
        return toJoin.join(" & ")
    }

    protected String renderAboveRow(int row){
        int height = sheet.rows
        int width = sheet.columns
        def out = "" << ""
        (width).times { int c ->
            def above = sheetStyle.getBorder(row-1, c, height, width)
            def below = sheetStyle.getBorder(row, c, height, width)
            out << above.getBorderWithCellBelow(below)
        }
        return "\\hhline{${out.toString()}}\n"
    }

    @Override
    Set<String> getNeededPackages() {
        [ "hhline", "multirow" ].toSet()
    }
}
