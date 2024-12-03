//..............................  Калечиц Александра, 5 группа
/*
4.	Задан текстовый файл input.txt.
Требуется определить строки этого файла,
содержащие максимальную по длине строго возрастающую подстроку.
 Если таких строк несколько, найти первые 10.
 Результат вывести на консоль в форме, удобной для чтения. */

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        String filename = "input.txt";
        String[] longestLines = new String[10];
        int maxLength = 0;
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                int length = findLongest(line);
                if (length > maxLength) {
                    maxLength = length;
                    count = 0; // сброс счет, если макс длина стала больше
                    longestLines[count++] = line;
                } else if (length == maxLength && length > 0 && count < 10) {
                    longestLines[count++] = line; // добавляем строку, если меньше 10
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

        System.out.println("Строки с максимальной по длине строго возрастающей подстрокой (до 10):");
        for (int i = 0; i < count; i++) {
            System.out.println((i+1)+")"+longestLines[i]);
        }
    }

    private static int findLongest(String s) {
        if (s.isEmpty()) return 0;

        int maxLength = 1;
        int currentLength = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) > s.charAt(i - 1)) {
                currentLength++;
            } else {
                maxLength = Math.max(maxLength, currentLength);
                currentLength = 1;
            }
        }

        maxLength = Math.max(maxLength, currentLength);
        return maxLength;
    }
}