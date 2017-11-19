package palyaeva;

import palyaeva.automaton.Automaton;
import palyaeva.automaton.DFA;
import palyaeva.task.Task3;
import palyaeva.util.DataReader;
import palyaeva.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        DataReader dataReader = new DataReader();
        List<Automaton> automatons = new ArrayList<>();

        DFA integer = new DFA();
        dataReader.read("Int.yaml", integer);
        integer.refresh();
        automatons.add(integer);

        DFA space = new DFA();
        dataReader.read("Space.yaml", space);
        space.refresh();
        automatons.add(space);

        DFA obkt = new DFA();
        dataReader.read("Openbkt.yaml", obkt);
        obkt.refresh();
        automatons.add(obkt);

        DFA cbkt = new DFA();
        dataReader.read("CloseBkt.yaml", cbkt);
        cbkt.refresh();
        automatons.add(cbkt);

        DFA kw = new DFA();
        dataReader.read("KeyWords.yaml", kw);
        kw.refresh();
        automatons.add(kw);

        DFA id = new DFA();
        dataReader.read("Identify.yaml", id);
        id.refresh();
        automatons.add(id);

        DFA real = new DFA();
        dataReader.read("Real.yaml", real);
        real.refresh();
        automatons.add(real);

        //System.out.println(Task3.tokenize(automatons, "test_task3.txt"));

        List<Pair<String, String>> tokens = new ArrayList<>();
        tokens = Task3.tokenize(automatons, "test_task3.txt");
        for (Pair<String, String> token : tokens) {
            System.out.println(token);
        }
    }
}
