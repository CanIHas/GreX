package can.i.has.latex.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import static can.i.has.latex.Commands.documentClass as DC
import static can.i.has.latex.Commands.usePackage
import static can.i.has.latex.Groups.documentEnv

@EqualsAndHashCode
@ToString
class Document implements Renderable{
    final Command documentClass
    final List<Command> preamble = []
    final Environment env = documentEnv()

    Document(Command documentClass=DC(), List<Command> preamble=[], List<Renderable> content=[]) {
        this.documentClass = documentClass
        this.preamble += preamble
        this.env.content += content
    }

    @Override
    String render() {
        ([documentClass] + neededPackages.collect {usePackage(it)} + preamble).collect { it.render() }.join("""
""")+"""
"""+env.render()
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
        c(preamble)
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
        c(env.content)
        this
    }
}
