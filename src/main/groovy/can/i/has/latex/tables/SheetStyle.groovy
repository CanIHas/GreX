package can.i.has.latex.tables


interface SheetStyle {
    /**
     * If row==height-1 or column==width-1 then we are looking for bottom or right (respectively) border of whole table.
     * If row==0 or column==0 then we are looking for top or left (respectively) border of whole table.
     * @param row int in [0..height)
     * @param column int in [0..width)
     * @param width
     * @param height
     * @return Border describing wanted cell
     */
    Border getBorder(int row, int column, int height, int width)
}