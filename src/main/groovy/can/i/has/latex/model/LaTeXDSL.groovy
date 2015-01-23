package can.i.has.latex.model

import can.i.has.latex.model.builder.Builder


class LaTeXDSL implements DocumentElement{
    File file

    @Override
    String render() {
        String out
        Builder.instance.with {
            out = ((LaTeXRenderable) Eval.me(file.text)).render()
        }
        out
    }
}
