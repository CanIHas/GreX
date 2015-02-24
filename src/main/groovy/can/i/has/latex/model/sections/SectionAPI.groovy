package can.i.has.latex.model.sections

import can.i.has.latex.model.Renderable
import can.i.has.utils.DelegationUtils


class SectionAPI {
    protected static Renderable buildSection(SectionLevel level, String title, boolean ignored, Closure closure){
        def out = new DocumentSection(level, title, ignored, [])
        DelegationUtils.delegateTo(closure, out)
        return out
    }

    static Renderable part(String name, Closure closure){
        buildSection(SectionLevel.PART, name, false, closure)
    }

    static Renderable part(String name, boolean ignored = false, Closure closure = {}){
        buildSection(SectionLevel.PART, name, ignored, closure)
    }

    static Renderable chapter(String name, Closure closure){
        buildSection(SectionLevel.CHAPTER, name, false, closure)
    }

    static Renderable chapter(String name, boolean ignored = false, Closure closure = {}){
        buildSection(SectionLevel.CHAPTER, name, ignored, closure)
    }

    static Renderable section(String name, Closure closure){
        buildSection(SectionLevel.SECTION, name, false, closure)
    }

    static Renderable section(String name, boolean ignored, Closure closure={}){
        buildSection(SectionLevel.SECTION, name, ignored, closure)
    }

    static Renderable subsection(String name, Closure closure){
        buildSection(SectionLevel.SUBSECTION, name, false, closure)
    }

    static Renderable subsection(String name, boolean ignored = false, Closure closure = {}){
        buildSection(SectionLevel.SUBSECTION, name, ignored, closure)
    }

    static Renderable subsubsection(String name, Closure closure){
        buildSection(SectionLevel.SUBSUBSECTION, name, false, closure)
    }

    static Renderable subsubsection(String name, boolean ignored = false, Closure closure = {}){
        buildSection(SectionLevel.SUBSUBSECTION, name, ignored, closure)
    }

    static Renderable paragraph(String name, Closure closure){
        buildSection(SectionLevel.PARAGRAPH, name, false, closure)
    }

    static Renderable paragraph(String name, boolean ignored = false, Closure closure = {}){
        buildSection(SectionLevel.PARAGRAPH, name, ignored, closure)
    }

    static Renderable subparagraph(String name, Closure closure){
        buildSection(SectionLevel.SUBPARAGRAPH, name, false, closure)
    }

    static Renderable subparagraph(String name, boolean ignored = false, Closure closure = {}){
        buildSection(SectionLevel.SUBPARAGRAPH, name, ignored, closure)
    }
}
