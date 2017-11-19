package palyaeva.task;//import javafx.util.palyaeva.util.Pair;

import palyaeva.automaton.Automaton;
import palyaeva.exception.MyException;
import palyaeva.util.DataReader;
import palyaeva.util.Functions;
import palyaeva.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alexa on 07.11.2017.
 */
public abstract class Task3 {

    public static Automaton getPriorityToken(Map<Automaton, Pair<Boolean, Integer>> tokens) {
        int maxLen = 0;
        for (Pair<Boolean, Integer> pair : tokens.values()) {
            int len = pair.getValue();
            if (len > maxLen) {
                maxLen = len;
            }
        }
        List<Automaton> maxLenAutomats = new ArrayList<>();
        for (Map.Entry<Automaton, Pair<Boolean, Integer>> entry : tokens.entrySet()) {
            if (entry.getValue().getValue() == maxLen) {
                maxLenAutomats.add(entry.getKey());
            }
        }
        if (maxLenAutomats.size() == 1) {
            return maxLenAutomats.get(0);
        }
        List<Automaton> maxPriorityAutomats = new ArrayList<>();
        int maxPriority = 0;
        for (Automaton a : maxLenAutomats) {
            if (a.priority > maxPriority) {
                maxPriority = a.priority;
            }
        }
        for (Automaton a : maxLenAutomats) {
            if (a.priority == maxPriority) {
                maxPriorityAutomats.add(a);
            }
        }
        if (maxPriorityAutomats.size() > 1) {
            throw new MyException("Same priority found");
        }
        return maxPriorityAutomats.get(0);
    }

    public static List<Pair<String, String>> tokenize(List<Automaton> automatons, String filename) {
        List<Pair<String, String>> tokens = new ArrayList<>();
        int position = 0;
        DataReader reader = new DataReader();
        String string = reader.getData(filename);
        while (position < string.length()) {
            Map<Automaton, Pair<Boolean, Integer>> tmpTokens = new HashMap<>();
            for (Automaton automaton : automatons) {
                automaton.refresh();
                Pair<Boolean, Integer> pair = Functions.findLongestFromPos(automaton, string, position);
                if (pair.getKey()) {
                    tmpTokens.put(automaton, pair);
                }
            }
            if (tmpTokens.isEmpty()) {
                position++;
                continue;
                //throw new palyaeva.exception.MyException("No tokens found");
            }
            // System.out.println(tmpTokens);
            Automaton a = getPriorityToken(tmpTokens);
            Integer length = tmpTokens.get(a).getValue();
            Pair<String, String> resultToken = new Pair<>(a.name, string.substring(position, position + length));
            tokens.add(resultToken);
            position += length;
        }
        return tokens;
    }
}
