package can.i.has.latex.tables

import can.i.has.latex.tables.builder.MultiTable
import can.i.has.latex.tables.builder.SheetArea
import can.i.has.latex.tables.builder.TableStack

import groovy.transform.Canonical

/**
 * Not thread safe! And shouldn't be!
 */
@Canonical
class MultiTableBuilder {
    static enum HorizontalAlignment { LEFT, RIGHT }
    static enum VerticalAlginment { TOP, BOTTOM }

    MultiTableBuilder() {
        this.stack = null
    }

    TableStack stack = null

    MultiTableBuilder to(Table table){
        assert stack == null
        stack = new TableStack()
        stack.push(table)
        this
    }

    MultiTableBuilder appendLeft(Table table, VerticalAlginment alignment=VerticalAlginment.TOP){
        assert stack
        SheetArea area = alignment == VerticalAlginment.TOP ?
            new SheetArea(0, table.sheet.rows-1, 0, table.sheet.columns-1)
            : new SheetArea(stack.maxRow-table.sheet.rows+1, stack.maxRow, 0, table.sheet.columns-1)
        stack.shiftRight(table.sheet.columns)
        stack.push(area, table)
        this
    }

    MultiTableBuilder appendTop(Table table, HorizontalAlignment alignment=HorizontalAlignment.LEFT){
        assert stack
        SheetArea area = alignment == HorizontalAlignment.LEFT ?
            new SheetArea(0, table.sheet.rows-1, 0, table.sheet.columns-1)
            : new SheetArea(0, table.sheet.columns-1, stack.maxColumn-table.sheet.columns+1, stack.maxColumn)
        stack.shiftDown(table.sheet.rows)
        stack.push(area, table)
        this
    }

    MultiTableBuilder appendRight(Table table, VerticalAlginment alignment=VerticalAlginment.TOP){
        assert stack
        SheetArea area = alignment == VerticalAlginment.TOP ?
            new SheetArea(0, table.sheet.rows-1, stack.maxColumn, stack.maxColumn+table.sheet.columns)
            : new SheetArea(stack.maxRow-table.sheet.rows+1, stack.maxRow, stack.maxColumn+1, stack.maxColumn+table.sheet.columns)
        stack.push(area, table)
        this
    }

    MultiTableBuilder appendBottom(Table table, HorizontalAlignment alignment=HorizontalAlignment.LEFT){
        assert stack
        SheetArea area = alignment == HorizontalAlignment.LEFT ?
            new SheetArea(stack.maxRow+1, stack.maxRow+table.sheet.rows, 0, table.sheet.columns-1)
            : new SheetArea(stack.maxRow+1, stack.maxRow+table.sheet.rows, table.sheet.columns-1, stack.maxColumn-table.sheet.columns+1, stack.maxColumn)
        stack.shiftDown(table.sheet.rows)
        stack.push(area, table)
        this
    }

    MultiTableBuilder append(SheetArea area, Table table){
        assert stack
        stack.push(area, table)
        this
    }

    Table build(def defaultValue=null){
        assert stack
//        stack.content.reverse(true)
        def out = new MultiTable(stack)
        out.defaultValue = defaultValue
        stack = null
        return out
    }
}
