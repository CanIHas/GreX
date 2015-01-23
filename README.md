# Latex

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
* raw - containing files with raw LaTeX code to be inserted into document
* dsl - containing files with Builder DSL (see below) to be evaluated
* results - this will contain all results of experiments, grouped by experiment name
* render - all renders will be saved here

Then you write your script. Start with setting up [*Workspace*](/src/main/groovy/can/i/has/latex/experiments/Workspace.groovy), using its manager:

    Workspace workspace = new Workspace("/some/path")
    Workspace.Manager.withWorkspace(workspace) {
        ... // rest of script
    }

Then you'll probably want to perform experiments. This part is not touched yet, so this is a big **TODO**.

Next step is writing down your document. Use [*Builder*](/src/main/groovy/can/i/has/latex/model/builder/Builder.groovy) for that:

    Builder.instance.with {
        ... // document definition here
    }

Tutorial for document builder will come when API will stabilize. Until then, see [*can.i.has.latex.model.builder* package](/src/main/groovy/can/i/has/latex/model)
and [its test](/src/test/groovy/can/i/has/latex/model/builder/BuilderTest.groovy).

When you've got your *Document*, you can render it. Rendering means generating String with LaTeX code from
data. At the moment render() returns String, but it will be saved in workspace.

# Experiments

As stated above - big TODO

# Builder

Waiting until API stabilizes.