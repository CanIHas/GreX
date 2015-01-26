package can.i.has.latex

import can.i.has.latex.model.Command
import can.i.has.latex.model.Document
import can.i.has.latex.model.Renderable
import can.i.has.latex.model.StringRenderable


class FluentAPI {
    static Document document(String style="article", List<String> options=[]){
        new Document(new Command("documentclass", options, [text(style)]))
    }

    static StringRenderable text(String txt){
        new StringRenderable(txt)
    }

    static Command command(String name, List<String> options=[], List<Renderable> args=[]){
        new Command(name, options, args)
    }

    static Command command(String name, String arg, String... options){
        new Command(name, options.toList(), [text(arg)])
    }



}
