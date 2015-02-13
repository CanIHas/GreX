package can.i.has.tables.trash


enum LineStyle {
    NONE("", "~"),
    SINGLE("|", "-"),
    DOUBLE("||", "=")

    final String vertical
    final String horizontal

    LineStyle(String vertical, String horizontal) {
        this.vertical = vertical
        this.horizontal = horizontal
    }
}
