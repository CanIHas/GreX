package can.i.has.latex.model.builder.impl

import can.i.has.latex.model.Document
import can.i.has.latex.model.DocumentElement
import can.i.has.latex.model.DocumentStyle
import can.i.has.latex.model.builder.Builder

import groovy.transform.Canonical

@Singleton
class DocumentBuilder extends AbstractTypeBuilder<Document>{

    @Override
    Document factory() {
        new Document()
    }

    @Override
    AbstractTypeBuilder.Helper<Document> getHelper(Document out) {
        return new DocumentHelper(out)
    }

    @Canonical
    static protected class DocumentHelper extends AbstractTypeBuilder.AbstractHelper<Document> {
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

        void elements(List<DocumentElement> elements){
            object.elements = elements
        }
    }

}
