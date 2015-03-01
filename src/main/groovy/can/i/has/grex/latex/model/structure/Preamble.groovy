package can.i.has.grex.latex.model.structure

import can.i.has.grex.Workspace
import can.i.has.grex.latex.model.contents.StringRenderable
import can.i.has.utils.WithContent

import groovy.transform.Canonical

@Canonical
class Preamble implements WithContent<List<Command>>{
    List<Command> commands = []

    void setTitle(String title){
        commands.removeAll { it.name == "title" }
        commands.add new Command("title", [], [new StringRenderable(title)])
    }

    void setAuthor(String author){
        commands.removeAll { it.name == "author" }
        commands.add new Command("author", [], [new StringRenderable(author)])
    }

    void setDate(String date){
        commands.removeAll { it.name == "date" }
        commands.add new Command("date", [], [new StringRenderable(date)])
    }

    String render(Workspace workspace){
        commands.collect { it.render() }.join("\n")
    }

    @Override
    List<Command> getContent() {
        commands
    }

    @Override
    void setContent(List<Command> content) {
        commands = content
    }
}
