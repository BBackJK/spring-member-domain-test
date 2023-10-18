package bback.test.proviider.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Strings {


    public boolean anyMatch(char ch, char... chars) {
        for (char c : chars) {
            if ( c == ch ) {
                return true;
            }
        }
        return false;
    }
}
