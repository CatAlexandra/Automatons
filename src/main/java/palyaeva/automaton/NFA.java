package palyaeva.automaton;

import palyaeva.exception.MyException;

import java.util.*;

public class NFA extends Automaton<List<String>> {

    // HashMap<String, HashMap<String, List<String>>> transitionTable
    // HashMap<состояние, HashMap<символ алфавита, List переходов>>

    public NFA() {
        this.isDeterministic = false;
        this.currentState = beginState;
        this.transitionTable = new HashMap<String, Map<String, List<String>>>();
    }

    public void nextState(String symbol) {
        if (!alphabet.contains(symbol) || currentState.isEmpty()) {
            throw new MyException();
        }
        Set<String> newStates = new HashSet<>();
        for (String s : currentState) {
            List<String> newStatesTmp = transitionTable.get(s).get(symbol);
            newStates.addAll(newStatesTmp);
        }
        currentState = new ArrayList<>(newStates);
    }

    // HashMap<String, HashMap<String, List<String>>> transitionTable
    // HashMap<состояние, HashMap<символ алфавита, List переходов>>
    public String run(String word) {
        refresh();
        String letter = "";
        for (int i = 0; i < word.length(); i++) {
            letter = Character.toString(word.charAt(i));
            try {
                nextState(letter);
            } catch (MyException e) {
                break;
            }
            boolean isCurrent = false;
            for (String state : currentState) {
                if (states.contains(state)) {
                    isCurrent = true;
                    break;
                }
            }
            if (!isCurrent) {
                return ("false");
            }
        }
        return isFinite() ? "true" : "false";
    }

    public boolean isFinite() {
        for (String s : currentState) {
            if (finiteStates.contains(s)) {
                return true;
            }
        }
        return false;
    }
}
