package can.i.has.utils


class LaTeXCompilerUtils {
    static final COMMAND = "pdflatex"

    static void compile(File toCompile, File outDir, String options=""){
        def p = "$COMMAND -output-directory ${outDir.absolutePath} $options ${toCompile.absolutePath}".execute()
        p.waitFor()
        assert p.exitValue()==0
    }
}
