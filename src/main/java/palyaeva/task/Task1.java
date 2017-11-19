package palyaeva.task;

import palyaeva.automaton.DFA;
import palyaeva.util.DataReader;
import palyaeva.automaton.NFA;
import palyaeva.translator.OldTranslator;

import java.util.Scanner;

public class Task1 {
    public void dfa() {
        Scanner in = new Scanner(System.in);
        String inputWord = in.next();

        //palyaeva.translator.OldTranslator palyaeva.translator = new palyaeva.translator.OldTranslator();
        String line = OldTranslator.translate(inputWord);

        DFA a = new DFA();
        DataReader dataReader = new DataReader();
        dataReader.read("data_2_task.yaml", a);
        a.refresh();
        System.out.println(a.run(line));
    }

    public void nfa() {
        Scanner in = new Scanner(System.in);
        String inputWord = in.next();
        NFA a = new NFA();
        DataReader dataReader = new DataReader();
        dataReader.read("data_1_task_NFA.yaml", a);
        a.refresh();
        System.out.println(a.run(inputWord));
    }
}
