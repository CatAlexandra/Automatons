package palyaeva.task;//import javafx.util.palyaeva.util.Pair;

import palyaeva.automaton.DFA;
import palyaeva.util.DataReader;
import palyaeva.util.Functions;
import palyaeva.exception.MyException;

import java.util.ArrayList;
import java.util.List;

public class Task2 {

    // поиск наиболее длинного вещественного числа начиная с позиции k
/*    public palyaeva.util.Pair<Boolean, Integer> findLongestFromPos(palyaeva.automaton.DFA automaton, String str, int k) {
        boolean isFound = false;
        int tempLength = 0;
        int numberLength = 0;
        String symbol = "";
        //palyaeva.translator.OldTranslator palyaeva.translator = new palyaeva.translator.OldTranslator();

        automaton.refresh();
        for (int i = k; i < str.length(); i++) {
            try {
                symbol = palyaeva.translator.OldTranslator.translateChar(str.charAt(i));
                automaton.nextState(symbol);
                tempLength++;
                if (automaton.isFinite()) {
                    numberLength += tempLength;
                    tempLength = 0;
                    isFound = true;
                }
            } catch (palyaeva.exception.MyException e) {
                break;
            }
        }
        return new palyaeva.util.Pair<>(isFound, numberLength);
    }*/

    // поиск всех вещественных чисел строки
    public List<String> findNumbers(DFA automaton, String string) {
        int position = 0;
        List<String> allNumbers = new ArrayList<String>();
        while (position < string.length()) {
            int lengthOfNumber = Functions.findLongestFromPos(automaton, string, position).getValue();
            if (lengthOfNumber == 0) {
                position++;
            } else {
                allNumbers.add(string.substring(position, position + lengthOfNumber));
                position += lengthOfNumber;
            }
        }
        return allNumbers;
    }

    public void runTask(String inputWord) throws MyException {
       /* Scanner in = new Scanner(System.in);
        String inputWord = in.next();*/

        DFA a = new DFA();
        DataReader dataReader = new DataReader();
        dataReader.read("data_2_task.yaml", a);
        a.refresh();

        System.out.println(findNumbers(a, inputWord).toString());
    }

}
