package FxHomeWork2.Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Validate {
    private static boolean hasLetters(String str) {
        str = str.toLowerCase(); // превращает все символы в нижний регистр
        Pattern pattern = Pattern.compile("[a-z]");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
    static boolean isValid(String str){
        return !(str.isEmpty() || hasLetters(str));
    }
    static boolean isMoreZero(String str){
        return Double.parseDouble(str) > 0;
    }
    static boolean islessZero(String str){
        return Double.parseDouble(str) < 0;
    }
}
