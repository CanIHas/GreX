package can.i.has.latex.model.builder

import can.i.has.latex.model.DocumentStyle
import can.i.has.latex.model.builder.AbstractTypeBuilder.AbstractHelper

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

        void clazz(String clazz){
            object.clazz = clazz
        }

        String getClazz(){
            object.clazz
        }
    }
}
