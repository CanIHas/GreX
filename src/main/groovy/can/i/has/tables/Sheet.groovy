package can.i.has.tables

import groovy.transform.Canonical

/**
 * TODO: wrap exceptions
 * Generic 2D array.
 * @param < T > Type of cell values
 */
@Canonical
class Sheet<T> implements List<List<T>>{
    @Delegate List<List<T>> delegate

    Sheet(int rows, int columns, T defaultValue=null) {
        delegate = []
        rows.times {
            def row = []
            delegate << row
            columns.times {
                row << defaultValue
            }
        }
    }

    Sheet(List<List<T>> delegate) {
        this.delegate = delegate
    }
/**
     * Assert that number of columns is the same in each row, and that dimension sizes are positive.
     */
    void validate(){
        assert delegate.collect { it.size() }.toSet().size() == 1
    }

    T getAt(int row, int column){
        delegate[row][column]
    }

    T getAt(List position){
        getAt(position[0], position[1])
    }

    int getRows(){
        delegate.size()
    }

    int getColumns(){
        validate()
        delegate.head().size()
    }

    Sheet<T> getCopy(){
        new Sheet<T>(delegate.collect { List<T> row ->
            def newRow = []
            newRow.addAll(row)
            newRow
        })
    }

    Sheet<T> fill(int rows=0, int cols=0, T defaultValue=null){
        def out = copy
        assert rows>=0 && cols>=0
        int rowsToCreate = rows == 0 ? 0 : rows-out.size()
        //min... because some rows may be shorter than longest
        int newCols = ([cols]+(out.collect { it.size() })).max()
        out.each { List<T> row ->
            row.addAll([defaultValue]*(newCols-row.size()))

        }
        out.addAll([[defaultValue] * newCols]*rowsToCreate)
    }

    Sheet<T> shiftRight(int shift, defaultValue=null){
        def toPrepend = [ defaultValue ]*shift
        new Sheet<T>(delegate.collect { toPrepend + it })
    }

    Sheet<T> shiftDown(int shift, defaultValue=null){
        def out = copy
        copy.fill()
        out.delegate = new Sheet<T>(shift, out.columns, defaultValue)
        out
    }

}
