package palyaeva.util;

import com.esotericsoftware.yamlbeans.YamlReader;
import palyaeva.automaton.Automaton;
import palyaeva.automaton.DFA;
import palyaeva.translator.Translator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataReader {

    public static String getData(String fileName) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        InputStream resourceAsStream = contextClassLoader.getResourceAsStream(fileName);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream))) {
            return br.lines().collect(Collectors.joining("\n"));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @SuppressWarnings("unchecked")
    public void read(String fileName, Automaton automaton) {
        YamlReader reader = new YamlReader(getData(fileName));
        try {
            if (automaton instanceof DFA) {
                HashMap<String, String> beginState = (HashMap) reader.read();
                automaton.beginState = beginState.get("begin_state");
            } else {
                HashMap<String, List<String>> beginStates = (HashMap) reader.read();
                automaton.beginState = beginStates.get("begin_state");
            }

            HashMap<String, String> metaData = (HashMap) reader.read();
            automaton.name = metaData.get("name");
            automaton.priority = Integer.parseInt(metaData.get("priority"));
            String translator = metaData.get("translator");
            if (translator != null) {
                Class clazz = Class.forName(translator);
                if (Translator.class.isAssignableFrom(clazz)) {
                    automaton.translator = (Translator) clazz.newInstance();
                }
            }

            HashMap<String, List<String>> data = (HashMap) reader.read();
            automaton.alphabet = data.get("alphabet");
            automaton.finiteStates = data.get("finite_state");
            automaton.states = data.get("states");

            if (automaton instanceof DFA) {
                // Map<String, Map<String, String>>
                // Map<состояние, Map<символ алфавита, состояние перехода>>
                Map<String, List<List<String>>> tableData = (HashMap<String, List<List<String>>>) reader.read();
                List<List<String>> table = tableData.get("transitions");
                for (int i = 0; i < table.size(); i++) {
                    Map<String, String> map = new HashMap<>();
                    for (int j = 0; j < (automaton.alphabet).size(); j++) {
                        map.put((automaton.alphabet.get(j)).toString(), table.get(i).get(j));
                    }
                    automaton.transitionTable.put(automaton.states.get(i), map);
                }
            } else {
                // Map<String, Map<String, List<String>>>
                // Map<состояние, Map<символ алфавита, List переходов>>
                Map<String, List<List<List<String>>>> tableData =
                        (Map<String, List<List<List<String>>>>) reader.read();
                List<List<List<String>>> table = tableData.get("transitions");
                for (int i = 0; i < table.size(); i++) {
                    Map<String, List<String>> map = new HashMap<>();
                    for (int j = 0; j < (automaton.alphabet).size(); j++) {
                        map.put((automaton.alphabet.get(j)).toString(), table.get(i).get(j));
                    }
                    automaton.transitionTable.put(automaton.states.get(i), map);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
