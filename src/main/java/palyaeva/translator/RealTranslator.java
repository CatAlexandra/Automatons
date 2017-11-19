package palyaeva.translator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alexa on 08.11.2017.
 */
public class RealTranslator implements Translator {

    Map<String, List<String>> map = new HashMap<>();

    public RealTranslator() {
        List<String> nums = new ArrayList<>();
        fillListOfSymbols(nums, '0', '9');
        map.put("N", nums);
        List<String> sign = new ArrayList<>();
        sign.add("+");
        sign.add("-");
        map.put("S", sign);
        List<String> dot = new ArrayList<>();
        dot.add(".");
        map.put("D", dot);
        List<String> e = new ArrayList<>();
        e.add("e");
        e.add("E");
        map.put("E", e);
    }

    @Override
    public List<String> getTranslateElements(String character) {
        return map.get(character);
    }
}
