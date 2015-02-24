package can.i.has.latex.tables.builder

import can.i.has.latex.tables.Table
import can.i.has.utils.Pair

import groovy.transform.Canonical

@Canonical
class TableStack<T> {
    List<Pair<SheetArea, Table<T>>> content = []

    void push(Table<T> table){
        content = [new Pair<SheetArea, Table<T>>(
            new SheetArea(0, table.sheet.rows-1, 0, table.sheet.columns-1),
            table
        )] + content
    }

    void push(SheetArea area, Table<T> table){
        content = [new Pair<SheetArea, Table<T>>(
            area,
            table
        )] + content
    }

    Pair<SheetArea, Table<T>> resolve(int x, int y){
        def point = new Pair<Integer, Integer>(x, y);
        content.find { Pair<SheetArea, Table<T>> pair ->
            pair.first.contains(point)
        }
    }

    int getMinColumn(){
        content.min { it.first.firstColumn }.first.firstColumn
    }

    int getMaxColumn(){
        content.max { it.first.lastColumn }.first.lastColumn
    }

    int getMinRow(){
        content.min { it.first.firstRow }.first.firstRow
    }

    int getMaxRow(){
        content.max { it.first.lastRow }.first.lastRow
    }

    void shiftRight(int offset){
        content.each { Pair<SheetArea, Table<T>> pair ->
            pair.first = pair.first.shiftRight(offset)
        }
    }

    void shiftDown(int offset){
        content.each { Pair<SheetArea, Table<T>> pair ->
            pair.first = pair.first.shiftDown(offset)
        }
    }

}
