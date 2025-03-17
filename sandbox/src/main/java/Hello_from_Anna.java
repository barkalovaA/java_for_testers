import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.util.Scanner;

public class Hello_from_Anna {
    public static void main(String[] args) {
/*        System.out.println("Hello,world!");

        var configFile = new File("java_for_testers/sandbox/build.gradle");
        System.out.println(configFile.getAbsolutePath());
        System.out.println(configFile.exists());

        System.out.println(new File("").getAbsolutePath());*/

        int sum = 0;
        System.out.println("Введите число и нажмите <Enter>:");

        while (true) {
            int value = new Scanner(System.in).nextInt();
            sum = sum + value;
            System.out.println("" + sum);
            if (value == 0) {
                sum = 0;
                System.out.println("Сумма сброшена. Введите новое число:");
            }
        }
    }
}
