//...................................  Калечиц Александра,5 группа
// 4.3

/*Для выполнения заданий использовать регулярные выражения.
Каждое задание реализовать в отдельном методе.
Строку получать из текстового файла input.txt.
Результат работы методов записывать в выходной текстовый файл output.txt.

    1. Из заданной строки исключить символы, расположенные внутри круглых скобок.
    Сами скобки тоже должны быть исключены.

    2. Из заданной строки удалить из каждой группы идущих подряд цифр,
    в которой более двух цифр, все цифры, начиная с третьей.

    3. Из заданной строки удалить из каждой группы идущих подряд цифр все незначащие нули.

* */

import java.io.*;
import java.nio.file.*;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        String inputFileName = "input.txt";
        String outputFileName = "output.txt";

        try {
            String content = new String(Files.readAllBytes(Paths.get(inputFileName)));

            String result1 = removeParentheses(content);
            String result2 = removeExcessDigits(content);
            String result3 = removeSignificantZeros(content);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
                writer.write("1. " + result1 + "\n");
                writer.write("2. " + result2 + "\n");
                writer.write("3. " + result3 + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Задача 1: Удалить символы внутри круглых скобок и сами скобки
    public static String removeParentheses(String input) {
        return input.replaceAll("\\([^()]*\\)", "").replaceAll("\\s+", " ").trim();
    }

    // Задача 2: Удалить из каждой группы цифр, где более двух, все цифры, начиная с третьей
    public static String removeExcessDigits(String input) {
        return input.replaceAll("(\\d{2})(\\d{1,})", "$1"); // Оставляем только первые две цифры
    }

    // Задача 3: Удалить незначащие нули из каждой группы цифр
    public static String removeSignificantZeros(String input) {
        return input.replaceAll("\\b0+(\\d)", "$1").replaceAll("\\b0+", "");
    }
}