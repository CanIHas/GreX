package can.i.has.grex.latex.tables


interface Sheet<T> {
    /**
     * Assert that number of columns is the same in each row, and that dimension sizes are positive.
     */
    void validate()

    T getAt(int row, int column)
    T getAt(List<Integer> position)

    int getRows()

    int getColumns()

    Sheet<T> getCopy()

    Sheet<T> fill(int rows)
//    Sheet<T> fill(T defaultValue)
    Sheet<T> fill(int rows, int cols)
//    Sheet<T> fill(int rows, T defaultValue)
    Sheet<T> fill(int rows, int cols, T defaultValue)

    Sheet<T> shiftRight(int shift)
    Sheet<T> shiftRight(int shift, T defaultValue)

    Sheet<T> shiftDown(int shift)
    Sheet<T> shiftDown(int shift, T defaultValue)
}