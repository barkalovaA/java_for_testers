package Common;

import java.util.Random;

public class CommonFunctions {
    public static String randomString(int n) {
        var rnd = new Random();
        var result = "";
        for (int i = 0; i < n; i++) {
            result = result + (char)('a' + rnd.nextInt(26));
        }
        //Генерация строк с апострофом
        //if (n < 20) {
        //    result = result + '\'';
        //}
        return result;
    }
}
