package palyaeva.automaton;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import palyaeva.util.AutomatonRenamer;
import palyaeva.util.DataReader;
import palyaeva.util.Functions;

/**
 * Created by alexa on 20.11.2017.
 */

@FixMethodOrder(MethodSorters.JVM)
public class AutomatonTest {
    private static Automaton automaton1;
    private static Automaton automaton2;

    @BeforeClass
    public static void testRead() {
        automaton1 = new DFA();
        DataReader reader = new DataReader();
        reader.read("Int.yaml", automaton1);
        automaton1.refresh();
        automaton2 = new DFA();
        reader.read("Real.yaml", automaton2);
        automaton2.refresh();
    }

/*    @Test
    public void testRename() {
        AutomatonRenamer renamer = new AutomatonRenamer();
        renamer.renameAutomatons(automaton1, automaton2);
        System.out.println(automaton1.transitionTable);
        System.out.println(automaton2.transitionTable);
    }*/
/*
    @Test
    public void testRun() {
        Assert.assertEquals("true", automaton1.run("123"));
        Assert.assertEquals("true", automaton2.run("123.12e-12"));
    }*/

    /*    @Test
        public void testGenerator() {
            Automaton automaton = AutomatonGenerator.generateEmptyAutomaton();
            Assert.assertEquals("true", automaton.run(""));
            Assert.assertEquals("false", automaton.run("a"));

            Automaton automaton3 = AutomatonGenerator.generateOneSymbolAutomaton("1");
            Assert.assertEquals("true", automaton3.run("1"));
            Assert.assertEquals("false", automaton3.run(""));
            Assert.assertEquals("false", automaton3.run("2"));
            Assert.assertEquals("false", automaton3.run("12"));
        }*/
    @Test
    public void testToNFA() {
        NFA nfa = Functions.toNonDeterministic(automaton1);
        System.out.println(nfa.transitionTable);
        AutomatonRenamer renamer = new AutomatonRenamer();
        renamer.renameAutomaton(nfa);
        System.out.println(nfa.transitionTable);
    }
}
