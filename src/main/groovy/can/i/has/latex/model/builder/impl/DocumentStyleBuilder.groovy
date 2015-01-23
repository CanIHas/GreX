package can.i.has.latex.model.builder.impl

import can.i.has.latex.model.DocumentStyle
import can.i.has.latex.model.builder.impl.AbstractTypeBuilder.AbstractHelper
import can.i.has.latex.model.builder.Builder

import groovy.transform.Canonical

@Singleton
class DocumentStyleBuilder extends AbstractTypeBuilder<DocumentStyle>{
    @Override
    DocumentStyle factory() {
        new DocumentStyle()
    }

    @Override
    Helper<DocumentStyle> getHelper(DocumentStyle out) {
        new DocumentStyleHelper(out)
    }

    @Canonical
    static protected class DocumentStyleHelper extends AbstractHelper<DocumentStyle> {
        @Delegate Builder builder = Builder.instance

        DocumentStyleHelper(DocumentStyle documentStyle){
            this.object = documentStyle
        }

        void option(String option){
            object.options.add(option)
        }

        List<String> getOptions(){
            object.options
        }

        void options(List<String> options){
            object.options = options
        }

        void clazz(String clazz){
            object.clazz = clazz
        }

        String getClazz(){
            object.clazz
        }
    }
}
