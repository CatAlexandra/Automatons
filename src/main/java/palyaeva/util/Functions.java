package palyaeva.util;//import javafx.util.palyaeva.util.Pair;

import palyaeva.automaton.Automaton;
import palyaeva.exception.MyException;

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
}
