package palyaeva.automaton;

import palyaeva.exception.MyException;

import java.util.HashMap;

public class DFA extends Automaton<String> {

    public DFA() {
        this.isDeterministic = true;
        this.currentState = beginState;
        this.transitionTable = new HashMap<String, HashMap<String, String>>();
    }

    // HashMap<String, HashMap<String, String>> transitionTable
    // HashMap<состояние, HashMap<символ алфавита, состояние перехода>>
    public void nextState(String symbol) throws MyException {
        if (alphabet.contains(symbol) || alphabet.contains("?")) {
            if (currentState == null || currentState.equals("-")) {
                throw new MyException("- get");
            } else {
                String tmpCurrentState = transitionTable.get(currentState).get(symbol);
                if (tmpCurrentState == null || tmpCurrentState.equals("-")) {
                    tmpCurrentState = transitionTable.get(currentState).get("?");
                }
                currentState = tmpCurrentState;
            }
        } else {
            throw new MyException("alphabet doesn't contain given symbol!");
        }
    }

    public String run(String word) {
        String letter = "";
        for (int i = 0; i < word.length(); i++) {
            letter = Character.toString(word.charAt(i));
            try {
                nextState(letter);
            } catch (MyException e) {
                break;
            }
            if (!states.contains(currentState)) {
                return "the word is NOT allowed for automaton";
            }
        }
        return isFinite() ? "the word is allowed for automaton" : "the word is NOT allowed for automaton";
    }

    public boolean isFinite() {
        return (finiteStates.contains(this.currentState));
    }

}
