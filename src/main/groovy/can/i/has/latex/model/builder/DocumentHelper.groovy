package can.i.has.latex.model.builder

import can.i.has.latex.model.Document
import can.i.has.latex.model.DocumentElement
import can.i.has.latex.model.DocumentStyle

import groovy.transform.Canonical

@Canonical
class DocumentHelper extends AbstractTypeBuilder.AbstractHelper<Document> {
    Builder builder = Builder.instance

    DocumentHelper(Document document){
        this.object = document
    }

    void style(Map props, Closure closure){
        object.style = Builder.instance.style(props, closure)
    }

    void element(DocumentElement element){
        object.elements.add(element)
    }

    List<DocumentStyle> getElements(){
        object.elements
    }
}