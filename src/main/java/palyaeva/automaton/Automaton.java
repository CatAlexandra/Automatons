package palyaeva.automaton;

import palyaeva.translator.Translator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Automaton<T> {

    public String name;

    public boolean isDeterministic;

    // Map<состояние, Map<символ алфавита, состояние перехода ИЛИ список состояний>>
    public HashMap<String, HashMap<String, T>> transitionTable;

    public Translator translator;

    public T beginState;

    public int priority;

    public List<String> finiteStates;

    public List<String> alphabet;

    public List<String> states;

    public T currentState;

    public abstract void nextState(String signal);

    public abstract boolean isFinite();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Automaton<?> automaton = (Automaton<?>) o;

        if (priority != automaton.priority) return false;
        return name.equals(automaton.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + priority;
        return result;
    }

    public void refresh() {
        currentState = beginState;
        parseAlphabet();
    }

    private void parseAlphabet() {
        if (translator != null) {
            List<String> tempAlphabet = new ArrayList<>(alphabet);
            for (String letter : tempAlphabet) {
                List<String> translations = translator.getTranslateElements(letter);
                if (translations != null) {
                    int index = alphabet.indexOf(letter);
                    alphabet.remove(index);
                    for (String translation : translations) {
                        alphabet.add(index, translation);
                        index++;
                    }
                    for (String key : transitionTable.keySet()) {
                        Map<String, T> map = transitionTable.get(key);
                        T value = map.get(letter);
                        map.remove(letter);
                        for (String translation : translations) {
                            map.put(translation, value);
                        }
                    }
                }
            }
        }
    }
}