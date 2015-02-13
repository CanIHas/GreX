package can.i.has.tables.trash

import groovy.transform.Canonical

@Canonical
class CrossStyle {
    @Delegate final Short mask

    static final List<String> mappings = [
        "", //(!LEFT & !RIGHT & !TOP & !BOTTOM)
        "|:", //(LEFT & !RIGHT & !TOP & !BOTTOM)
        ":|", //(!LEFT & RIGHT & !TOP & !BOTTOM)
        "||",//(LEFT & RIGHT & !TOP & !BOTTOM)
        ":t:", //(!LEFT & !RIGHT & TOP & !BOTTOM)
        "|t:", //(LEFT & !RIGHT & TOP & !BOTTOM)
        ":t|", //(!LEFT & RIGHT & TOP & !BOTTOM)
        "|t|", //(LEFT & RIGHT & TOP & !BOTTOM)
        ":b:", //(!LEFT & !RIGHT & !TOP & BOTTOM)
        "|b:", //(LEFT & !RIGHT & !TOP & BOTTOM)
        ":b|", //(!LEFT & RIGHT & !TOP & BOTTOM)
        "|b|", //(LEFT & RIGHT & !TOP & BOTTOM)
        ":=:", //(!LEFT & !RIGHT & TOP & BOTTOM)
        "|=:", //(LEFT & !RIGHT & TOP & BOTTOM)
        ":=|", //(!LEFT & RIGHT & TOP & BOTTOM)
        "#", //(LEFT & RIGHT & TOP & BOTTOM)
    ]

    String toString(){
        mappings[mask]
    }

}
