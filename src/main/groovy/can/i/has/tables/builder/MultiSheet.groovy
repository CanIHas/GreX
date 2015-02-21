package can.i.has.tables.builder

import can.i.has.tables.Sheet

import groovy.transform.Canonical

//@Immutable // fixme: figure out immutability and sheet copying
@Canonical
class MultiSheet<T> implements Sheet<T> {
    MultiTable<T> multiTable

    @Override
    void validate() {
    }

    @Override
    T getAt(int row, int column) {
        def pair = multiTable.tableStack.resolve(column, row)
        if (pair == null)
            return multiTable.defaultValue
        def coord = pair.first.relative(column, row)
        def table = pair.second
        return (table ? table.sheet.getAt(coord.second, coord.first) : multiTable.defaultValue)

    }

    @Override
    T getAt(List<Integer> position) {
        getAt(position[0], position[1])
    }

    @Override
    int getRows() {
        multiTable.tableStack.maxRow+1
    }

    @Override
    int getColumns() {
        multiTable.tableStack.maxColumn+1
    }

    @Override
    Sheet<T> getCopy() {
        //todo: figure out better approach; still, multitable is immutable, so whats the difference?
        this
    }

//    @Override
    Sheet<T> fill(int rows=0, int columns=0, T defaultValue=null) {
        //todo: figure out better approach; still, multitable is immutable, so whats the difference?
        this
    }


    @Override
    Sheet<T> shiftRight(int shift, T defaultValue=null) {
        //todo: figure out better approach; still, multitable is immutable, so whats the difference?
        this
    }

    @Override
    Sheet<T> shiftDown(int shift, T defaultValue=null) {
        //todo: figure out better approach; still, multitable is immutable, so whats the difference?
        this
    }


    @Override
    public String toString() {
        return "MultiSheet{" +
            "multiTable=" + multiTable +
            '}';
    }
}
