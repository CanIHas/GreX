package can.i.has.experiments.example.model

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

@CompileStatic
@Slf4j
class ARFFLoader {

    private void parseAttribute(String line, List<String> names, Map<String, List<String>> domains) {
        assert line.toLowerCase().startsWith("attribute")
        line = line.substring("attribute".size()).trim()
        def name = line.tokenize()[0]
        def declaration = line.substring(name.size()).trim()
        names.add(name)
        // if typedef doesn't start with "{" we assume its numeric
        if (declaration.startsWith("{")) {
            assert declaration.endsWith("}")
            declaration = declaration.substring(1, declaration.size()-1)
            def domain = declaration.split(/,/)
            domains[name] = domain.collect { String value -> value.trim() }
        }
    }

    private List<List> parseData(BufferedReader reader, List<String> names, Map<String, List<String>> domains){
        def out = []
        reader.eachLine { String line ->
            if (!line.startsWith("%")) {
                def values = line.split(/,/)
                assert values.size() == names.size()
                def vector = []
                values.eachWithIndex { String value, int i ->
                    if (domains.containsKey(names[i]))
                        vector.add value
                    else
                        vector.add new Double(value)
                }
                out.add vector
            }
        }
        return out
    }

    DataSet load(int classIdx, BufferedReader reader){
        String name = null
        List<String> names = []
        Map<String, List<String>> domains = [:]
        List<List> instances = null
        String line = null
        while ((line = reader?.readLine()) != null){
            if (line) {
                line = line.trim()
                switch (line[0]) {
                    case null:
                    case "%": break
                    case "@":
                        line = line.substring(1)
                        if (line.toLowerCase().startsWith("relation")) {
                            assert name == null
                            name = line.substring("relation".size()).trim()
                        } else if (line.toLowerCase().startsWith("attribute")) {
                            parseAttribute(line, names, domains)
                        } else if (line.toLowerCase().startsWith("data")) {
                            assert instances == null
                            instances = parseData(reader, names, domains)
                            reader = null // to stop "while" loop
                        }
                        break
                    default: assert "Either line is either empty or it starts with @ or % - this does not!" && line =="xyz "&& false
                }
            }
        }
        new DataSet(name, new DataScheme(classIdx, names, domains), instances)
    }
}
