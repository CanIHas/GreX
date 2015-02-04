package can.i.has.tables


class Border {
    static enum Style {
        NONE("", ""),
        SINGLE("|", "\\hline"),
        DOUBLE("||", "\\hline\n\\hline"),
        TRIPLE("|||", "\\hline\n\\hline\n\\hline")

        final String vertical
        final String horizontal

        Style(String vertical, String horizontal){
            this.vertical = vertical
            this.horizontal = horizontal
        }
    }

    static enum Part {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
        ROWS,
        COLUMNS,
        HEADER_ROW,
        HEADER_COLUMN,
        MIDHEADER_ROW,
        MIDHEADER_COLUMN
    }

    static Map<Part, Style> defaultBorder() {
        return [
            (Part.TOP): Style.SINGLE,
            (Part.BOTTOM): Style.SINGLE,
            (Part.LEFT): Style.SINGLE,
            (Part.RIGHT): Style.SINGLE,
            (Part.ROWS): Style.SINGLE,
            (Part.COLUMNS): Style.SINGLE,
            (Part.HEADER_ROW): Style.DOUBLE,
            (Part.HEADER_COLUMN): Style.DOUBLE,
            (Part.MIDHEADER_ROW): Style.SINGLE,
            (Part.MIDHEADER_COLUMN): Style.SINGLE,
        ]
    }

    static void injectDefaults(Map<Part, Style> borderStyle){
        defaultBorder().each { k, v -> borderStyle[k] = v }
    }

}
