> Disclaimer: I focus on development, not documenting it on-line, while writing. This README will always have summary
> up-to-date, but usage and other details may be outdated. I'll try my best to make this as informative as possible,
> but best way to see what's what is reading tests - I'm usually providing test suite that is meant to cover
> usual scenarios, so it can also be base for snippets.

# LaTeX

Sometimes you need to implement some method, and you would like to research it a little.

You'll need some framework for running experiments in which you'd like to try out different parameter values.
Result persistence may be really important, if your experiments take some time.

Then, you'd like to sum your results in some paper. You'd probably use LaTeX, so you'll need
data table structures, together with possibility of dumping them to LaTeX format.

So, why not go further? Here I am, trying to provide those LaTeX possibilities that I know (I'm quite a newbie
to LaTeX, unfortunately) in form of Groovy library with builder API, together with some LaTeX document data model.

# Usage

You start with setting up project and cloning this repo into it as GIT submodule (I'll try to publish on some
maven repo in the future).

Next step is choosing workspace. Workspace is a directory in which all data will be kept. It is by default
set up with subdirectories:
* raw - containing files with raw LaTeX code to be inserted into document,
* dsl - containing files with Builder DSL (see below) to be evaluated,
* results - this will contain all results of experiments, grouped by experiment name,
* render - all renders will be saved here,
* build - while compiling renders, output will be stored here.

Then you write your script. Start with setting up [*Workspace*](/src/main/groovy/can/i/has/latex/experiments/Workspace.groovy), using its manager:

    Workspace workspace = new Workspace("/some/path")
    Workspace.Manager.withWorkspace(workspace) {
        ... // rest of script
    }

Then you'll probably want to perform experiments. This part is not touched yet, so this is a big **TODO**.

Next, you'll want to create your LaTeX Document. At this point there are a few features implemented, but you can
see where this will go in future. You can create your Document with
[simple data model](/src/test/groovy/can/i/has/latex/ModelTest.groovy) or with set of
[utility methods](/src/test/groovy/can/i/has/latex/FluentAPITest.groovy),
written with fluent API in mind. See linked tests, to get a grip.

When you've got your *Document*, you can render it. Rendering means generating String with LaTeX code from
data. At the moment render() returns String, but it will be saved in workspace.

# Example

Just see [example package](/src/main/groovy/can/i/has/experiments/example), especially
[Experiments script](/src/main/groovy/can/i/has/experiments/example/Experiments.groovy).

It will generate LaTeX document containing report for one of my university courses. It isn't really
well-written, neither it is optimal, but it shows main use case in raw code.

At this moment it is not complete, as many needed functionalities (table and graph models, their conversion
to LaTeX, etc) are lacking, but I'm gonna fill it in.