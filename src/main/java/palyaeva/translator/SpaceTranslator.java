package palyaeva.translator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpaceTranslator implements Translator {

        Map<String, List<String>> map = new HashMap<>();

        public SpaceTranslator() {
            List<String> spaces = new ArrayList<>();
            spaces.add("\n");
            spaces.add("\r");
            spaces.add("\t");
            spaces.add(" ");
            map.put("WS", spaces);
        }

        @Override
        public List<String> getTranslateElements(String character) {
            return map.get(character);
        }
    }
