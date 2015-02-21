package can.i.has.tables.impl

import can.i.has.tables.Border
import can.i.has.tables.Table

abstract class AbstractTable<T> implements Table<T>{
    static final String NEWLINE = "\\\\\n"


    @Override
    String render() {
        def out = "\\begin{tabular}{${'c'*sheet.columns}}\n" << ""
        (sheet.rows).times { int rowIdx ->
            out << renderAboveRow(rowIdx)
            out << renderRow(rowIdx) << NEWLINE
        }
        out << renderAboveRow(sheet.rows)
        out << "\\end{tabular}"
        out.toString()
    }

    protected String cell(String borders, String content){
        "\\multicolumn{1}{$borders}{$content}"
    }

    protected String renderRow(int row) {
        int height = sheet.rows //todo: maybe add width/height properties to Table API? ; remove sheet, add getAt(x, y)
        int width = sheet.columns
        def toJoin = []
        (width).times { int colIdx ->
            toJoin << renderCell(sheet.getAt(row, colIdx), row, colIdx, height, width)
        }
        return toJoin.join(" & ")
    }

    protected String renderCell(def content, int row, int column, int height, int width){
        Border border = sheetStyle.getBorder(row, column, height, width)
        return cell("${border.leftSeparator}c${border.rightSeparator}", contentRenderer.renderCell(content, row, column))
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
