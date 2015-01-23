package can.i.has.latex.model.builder

import can.i.has.latex.model.Document
import can.i.has.latex.model.DocumentElement
import can.i.has.latex.model.DocumentStyle
import can.i.has.latex.model.builder.AbstractTypeBuilder.AbstractHelper

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



}
