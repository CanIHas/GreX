package can.i.has.tables.builder

import can.i.has.utils.Pair

import groovy.transform.Immutable

@Immutable
class SheetArea {
    int firstRow
    int lastRow
    int firstColumn
    int lastColumn

    boolean contains(int x, int y){
        contains(new Pair<Integer, Integer>(x, y));
    }

    boolean contains(Pair<Integer, Integer> coordinates){
        return coordinates.first >= firstColumn && coordinates.first <= lastColumn && \
                coordinates.second >= firstRow && coordinates.second <= lastRow
    }

    Pair<Integer, Integer> relative(int x, int y){
        new Pair<Integer, Integer>(x - firstColumn, y-firstRow)
    }

    Pair<Integer, Integer> relative(Pair<Integer, Integer> pair){
        relative(pair.first, pair.second)
    }

    int getWidth(){
        lastColumn-firstColumn+1 // +1 because it is both sides inclusive
    }

    int getHeight(){
        lastRow-firstRow+1 // +1 because it is both sides inclusive
    }

    SheetArea shiftRight(int offset){
        new SheetArea(firstRow, lastRow, firstColumn+offset, lastColumn+offset)
    }

    SheetArea shiftDown(int offset){
        new SheetArea(firstRow+offset, lastRow+offset, firstColumn, lastColumn)
    }
}
