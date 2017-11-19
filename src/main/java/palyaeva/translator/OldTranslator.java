package palyaeva.translator;

public abstract class OldTranslator {

    public static String translate(String word) {
        String translated = "";
        for (char c : word.toCharArray()) {
            translated += translateChar(c);
        }
        return translated;
    }

    public static String translateChar(char symbol) {
        if ((symbol >= '0') && (symbol <= '9')) {
            return "N";
        }
        if (symbol == '.') {
            return "D";
        }
        if (symbol == '+' || symbol == '-') {
            return "S";
        }
        if (symbol == 'e' || symbol == 'E') {
            return "E";
        }
        return String.valueOf(symbol);
    }

  /*  public static String translateInt(char symbol) {
        if ((symbol >= '0') && (symbol <= '9')) {
            return "N";
        }
        if (symbol == '+' || symbol == '-') {
            return "S";
        }
        return String.valueOf(symbol);
    }

    public static String translateSpace(char symbol) {
        if (symbol == '\n' || symbol == '\t' || symbol == '\r' || symbol == ' ')
            return "WS";
        return String.valueOf(symbol);
    }

    public static String translateIdentify(char symbol){
        if (Character.isDigit(symbol) || Character.isLetter(symbol) || symbol == '+' ||
                symbol == '-' || symbol == '*' || symbol == '/' || symbol == '%' ||
                symbol == '!' || symbol == '_'){
            return "S";
        }
        return String.valueOf(symbol);
    }*/
}
