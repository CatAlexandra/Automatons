package palyaeva.util;//import javafx.util.palyaeva.util.Pair;

import palyaeva.automaton.Automaton;
import palyaeva.automaton.NFA;
import palyaeva.exception.MyException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alexa on 07.11.2017.
 */
public class Functions {

    public static Pair<Boolean, Integer> findLongestFromPos(Automaton automaton, String str, int k) {
        boolean isFound = false;
        int tempLength = 0;
        int numberLength = 0;
        String symbol = "";
        //palyaeva.translator.OldTranslator palyaeva.translator = new palyaeva.translator.OldTranslator();

        automaton.refresh();
        for (int i = k; i < str.length(); i++) {
            try {
                symbol = String.valueOf(str.charAt(i));
                automaton.nextState(symbol);
                tempLength++;
                if (automaton.isFinite()) {
                    numberLength += tempLength;
                    tempLength = 0;
                    isFound = true;
                }
            } catch (MyException e) {
                break;
            }
        }
        return new Pair<>(isFound, numberLength);
    }

    public static <T> NFA toNonDeterministic(Automaton<T> automaton) {
        if (automaton instanceof NFA) {
            return (NFA) automaton;
        } else {
            NFA nfa = new NFA();
            nfa.alphabet = new ArrayList<>(automaton.alphabet);
            nfa.priority = automaton.priority;
            nfa.finiteStates = new ArrayList<>(automaton.finiteStates);
            nfa.beginState = new ArrayList<>();
            nfa.beginState.add((String) automaton.beginState);
            nfa.states = new ArrayList<>(automaton.states);
            nfa.name = automaton.name;
            nfa.translator = automaton.translator;
            nfa.transitionTable = new HashMap<>();
            for (Map.Entry<String, Map<String, T>> entry : automaton.transitionTable.entrySet()) {
                Map<String, List<String>> transitions = new HashMap<>();

                for (Map.Entry<String, T> trans : entry.getValue().entrySet()) {
                    List<String> states = new ArrayList<>();
                    String tmp = (String) trans.getValue();
                    if (tmp != null && !tmp.equals("-")) {
                        states.add(tmp);
                    }

                    transitions.put(trans.getKey(), states);
                }
                nfa.transitionTable.put(entry.getKey(), transitions);
            }
            return nfa;
        }

    }
}
