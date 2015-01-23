package can.i.has.latex.model.builder


class BuilderTest extends GroovyTestCase {
    void testBuilder(){
        Builder.class.instance.with {
            println document(elements: [raw("ABC")]){
                style(options: ["X", "Y"]) {
                    clazz "A"
                }
                element raw("Ala ma kota")
            }.render()
        }
    }
}
