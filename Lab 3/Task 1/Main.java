//.................................... Калечиц Александра, 5 группа
/*4.	Словом в строке считается последовательность букв латинского алфавита,
остальные символы являются разделителями между словами.
За один просмотр символов строки найти все слова с максимальной долей
гласных букв (т.е. прописных и строчных символов ’a’, ’e’, ’i’, ’o’, ’u’, ’y’)
и занести их в новую строку. Слова в исходной строке разделяются некоторым
множеством разделителей. Слова в новой строке должны разделяться ровно одним пробелом.*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку: ");
        String str = scanner.nextLine();
        String result = WordsMaxVowel(str);
        System.out.println("Слова с максимальной долей гласных: " + result);
    }

    private static boolean isVowel(char c) {
        return "aeiouyAEIOUY".indexOf(c) != -1; // Проверка на гласную
    }

    private static String WordsMaxVowel(String str) {
        StringBuilder result = new StringBuilder();
        int maxCount = 0;
        int count = 0;
        int start = -1;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isAlphabetic(c)) {
                if (start == -1) {
                    start = i;
                }
                count += isVowel(c) ? 1 : 0; // увеличиваем счетчик гласных
            } else {
                if (start != -1) {
                    int end = i - 1;
                    if (count > maxCount) {
                        maxCount = count;
                        result.setLength(0); // очищаем результат
                        result.append(str, start, end + 1); // добавляем новое слово(с бОльшим количеством гл)
                    } else if (count == maxCount) {
                        result.append(" ").append(str, start, end + 1); // обавляем слово с с таким же кол-вом гл
                    }
                    start = -1;
                    count = 0;
                }
            }
        }

        // Проверяем последнее слово, если оно существует
        if (start != -1) {
            int end = str.length() - 1;
            if (count > maxCount) {
                maxCount = count;
                result.setLength(0);
                result.append(str, start, end + 1);
            } else if (count == maxCount) {
                result.append(" ").append(str, start, end + 1);
            }
        }

        return result.toString().trim(); // Возвращаем результат(trim, чтобы не было лишних пробелов)
    }
}