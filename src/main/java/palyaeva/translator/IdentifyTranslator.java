package palyaeva.translator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alexa on 08.11.2017.
 */
public class IdentifyTranslator implements Translator {

    Map<String, List<String>> map = new HashMap<>();

    public IdentifyTranslator() {
        List<String> list = new ArrayList<>();
        fillListOfSymbols(list, '0', '9');
        fillListOfSymbols(list, 'a', 'z');
        fillListOfSymbols(list, 'A', 'Z');
        list.add("+");
        list.add("-");
        list.add("%");
        list.add("*");
        list.add("/");
        list.add("_");
        list.add("!");
        map.put("S", list);
    }

    @Override
    public List<String> getTranslateElements(String character) {
        return map.get(character);
    }
}
