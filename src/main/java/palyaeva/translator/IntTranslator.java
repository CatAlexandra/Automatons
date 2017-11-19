package palyaeva.translator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alexa on 07.11.2017.
 */
public class IntTranslator implements Translator {
    Map<String, List<String>> map = new HashMap<>();

    public IntTranslator() {
        List<String> nums = new ArrayList<>();
        fillListOfSymbols(nums, '0', '9');
        map.put("N", nums);
        List<String> sign = new ArrayList<>();
        sign.add("+");
        sign.add("-");
        map.put("S", sign);
    }

    @Override
    public List<String> getTranslateElements(String character) {
        return map.get(character);
    }
}
