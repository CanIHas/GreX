package can.i.has.grex.latex.model.sections


enum SectionLevel {
    PART, CHAPTER, SECTION, SUBSECTION, SUBSUBSECTION, PARAGRAPH, SUBPARAGRAPH

    String toString(){
        return super.toString().toLowerCase()
    }
}