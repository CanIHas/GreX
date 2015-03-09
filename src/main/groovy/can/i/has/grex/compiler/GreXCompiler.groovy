package can.i.has.grex.compiler

import can.i.has.grex.Workspace
import can.i.has.grex.experiments.Experiment
import can.i.has.grex.latex.model.Document
import can.i.has.grex.latex.model.Renderable
import can.i.has.grex.latex.model.contents.StringRenderable
import can.i.has.grex.latex.model.sections.DocumentSection
import can.i.has.grex.latex.model.sections.SectionLevel
import can.i.has.grex.latex.model.structure.Command
import can.i.has.grex.latex.model.structure.Environment
import can.i.has.grex.latex.model.structure.Preamble
import can.i.has.utils.MapArgsUtils
import can.i.has.utils.WithContent


class GreXCompiler {
    // Closures are (Node -> T); entities should have defaultConstructor
    Map<String, Closure> entityCompilers = [
        workspace: defaultCompilerMethod(Workspace),
        experiments: valueListExtractor(Experiment),
        documents: valueListExtractor(Document),
        document: defaultCompilerMethod(Document),
        preamble: defaultCompilerMethod(Preamble),
        usePackage: CompilationHelper.&usePackage,
        body: namedEnv("document"),
        text: CompilationHelper.&text,
        raw: CompilationHelper.&raw,
        title: nodeAsParameterlessFunction("maketitle"),
        tableOfContents: nodeAsParameterlessFunction("tableofcontents"),
        command: this.&command,
        env: defaultCompilerMethod(Environment),
        paperAbstract: namedEnv("abstract"),
        documentSection: defaultCompilerMethod(DocumentSection), // named sections are defined in constructor
    ]

    GreXCompiler() {
        SectionLevel.values().each { SectionLevel level ->
            entityCompilers[level.toString()] = namedSection(level)
        }
    }

//todo: development only, remove in future
    public <T> Closure<T> dummyCompilerMethod(){
        return { Node node -> null }
    }

    public <T> Closure<List<T>> valueListExtractor(Class<T> elementClass){
        return { Node node ->
            //todo: exceptions
            assert node.attributes().isEmpty()
            assert node.value() instanceof List<Node> //todo: generics will suck here, ensure elements are Nodes
            return node.value().collect { compile(it) }
        }
    }

    public Closure<Environment> namedEnv(String name){
        return { Node node ->
            assert !node.attributes().containsKey("name") //todo: exceptions
            def out = defaultCompilerMethod(Environment)(node)
            out.name = name
            return out
        }
    }

    public Closure<DocumentSection> namedSection(SectionLevel level){
        return { Node node ->
            assert !node.attributes().containsKey("level")
            def out = defaultCompilerMethod(DocumentSection)(node)
            out.level = level
            return out
        }
    }

    public <T> Closure<T> defaultCompilerMethod(Class<T> resultClass){
        return { Node node ->
            T out = resultClass.newInstance()
            if (out instanceof WithContent) {
                def content = node.value()
                if (content instanceof List)
                    content = content.collect { it instanceof Node ? compile(it) : it }
                ((WithContent) out).content = content
            } else {
                if (node.value() instanceof List<Node>) { //todo: generics will suck here, ensure elements are Nodes
                    node.value().each { Node valueNode ->
                        out."${valueNode.name()}" = compile(valueNode)
                    }
                } else {
                    assert node.value() instanceof Node //todo: exceptions
                    out."${node.name()}" = compile(node.value())
                }
            }

            node.attributes().each { String k, def v ->
                if (v instanceof Node)
                    v = compile(v)
                out."set${k.capitalize()}"(v)
            }

            return out
        }
    }

    Closure<Command> nodeAsParameterlessFunction(String commandName){
        return { Node node ->
            assert node.attributes().isEmpty() //todo: exceptions
            assert !node.value()
            new Command(commandName)
        }
    }


    Command command(Node node){
        def attrs = MapArgsUtils.leaveArgs(null, node.attributes(), "name", "options", "args", "neededPackages")
        List<String> options = attrs.options ?: []
        List args = attrs.args ?: []
        Collection<String> neededPackages = attrs.neededPackages ?: []
        neededPackages = neededPackages.toSet()
        args = args.collect { if (it instanceof String) return new StringRenderable(it) else {assert it instanceof Renderable; return it} }
        String name = node.value() ?: attrs.name
        assert name //todo: exceptions
        new Command(name, options, args, neededPackages)
    }


    def compile(Node node){
        if (entityCompilers.containsKey(node.name()))
            return entityCompilers[node.name()].call(node)
        else {
            //todo: dedicate exception
            def up = new RuntimeException("Couldn't compile node of type ${node.name()}")
            throw up
        }

    }
}
