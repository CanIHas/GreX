package can.i.has.tables.trash


enum CrossPart {
    LEFT((1<<0) as Short),
    RIGHT((1 << 1) as Short),
    TOP((1 << 2) as Short),
    BOTTOM((1 << 3) as Short)

    Short mask

    CrossPart(Short mask) {
        this.mask = mask
    }
}
