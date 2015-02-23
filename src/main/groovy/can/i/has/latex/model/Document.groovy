package can.i.has.latex.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import static can.i.has.latex.CommonAPI.documentEnv
import static can.i.has.latex.ContentCommands.documentClass as DC

@EqualsAndHashCode
@ToString
class Document implements Renderable{
    final Command documentClass
    final Preamble preamble = new Preamble()
    final Environment env = documentEnv()

    Document(Command documentClass=DC(), List<Command> preamble=[], List<Renderable> content=[]) {
        this.documentClass = documentClass
        this.preamble.commands += preamble
        this.env.content += content
    }

    @Override
    String render() {
            (
                [ documentClass ] +
                neededPackages.collect {
                    usePackage(it)
                } +
                preamble
            ).collect {
                it.render()
            }.join("\n")+
            "\n"+
            env.render()
    }

    @Override
    Set<String> getNeededPackages() {
        env.neededPackages
    }

    Document withPreamble(Closure c){
//        def oldDelegate = c.delegate
//        try {
//            c.resolveStrategy = Closure.DELEGATE_FIRST
//            c.delegate = preamble
//            this
//        } finally {
//            c.delegate = oldDelegate
//        }
        Closure toCall = c.clone()
        toCall.delegate = preamble
        toCall.resolveStrategy = Closure.DELEGATE_FIRST
        toCall()
        this
    }

    Document withContent(Closure c){
//        def oldDelegate = c.delegate
//        try {
//            c.resolveStrategy = Closure.DELEGATE_FIRST
//            c.delegate = env
//            this
//        } finally {
//            c.delegate = oldDelegate
//        }
        Closure toCall = c.clone()
        toCall.delegate = env
        toCall.resolveStrategy = Closure.DELEGATE_FIRST
        toCall()
        this
    }
}
