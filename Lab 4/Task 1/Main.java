//..............................................  Калечиц Александра, 5 группа
// 4.2

/*
*Входные данные:
Входной файл input1.html содержит текст, написанный на языке HTML.
В тесте находятся теги. В одной строке может быть несколько тегов. Теги находятся в угловых скобках,
* каждому открывающему тегу ставится в соответствие закрывающий тег. Например, пара тегов<b></b>.
Между тегами находится текст, причем теги не разрывают текст. Например, при поиске слова hello
*  комбинация h<b><i>el</i>l</b>o должна быть найдена.
Гарантируется,что страница HTML является корректной, т.е. все символы "<" и ">" используются только
*  в тегах, все теги записаны корректно.
Входной файл input2.in содержит список фрагментов текста, которые нужно найти в первом файле,
*  записанных через разделители (точка с запятой). Может быть несколько строк.

Примечание: Ваша программа должна игнорировать различие между строчными и прописными буквами и для тегов и для искомого контекста.

Выходные данные:
1. В выходной файл output1.out вывести список всех тегов в порядке возрастания количества символов тега.
2. В выходной файл output2.out вывести номера строк (нумерация с 0) первого файла,
  в которых был найден искомый контекст в первый раз или -1 , если не был найден.
3. В выходной файл output3.out - список фрагментов второго файла, которые НЕ были найдены в первом файле.

* */
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        try {
            String htmlContent = readFile("input1.html");
            List<String> fragments = readFragments("input2.in");

            // Извлечение тегов
            Set<String> tags = extractTags(htmlContent);
            List<String> sortedTags = new ArrayList<>(tags);
            Collections.sort(sortedTags, Comparator.comparingInt(String::length));

            // Поиск фрагментов
            List<Integer> foundLines = new ArrayList<>();
            for (String fragment : fragments) {
                int lineNumber = findFragmentInHtml(htmlContent, fragment);
                foundLines.add(lineNumber);
            }

            // Запись в выходные файлы
            writeFile("output1.out", sortedTags);
            writeFile("output2.out", foundLines);
            writeFile("output3.out", getNotFoundFragments(fragments, foundLines));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFile(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    private static List<String> readFragments(String fileName) throws IOException {
        List<String> fragments = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                fragments.addAll(Arrays.asList(line.split(";")));
            }
        }
        return fragments;
    }

    private static Set<String> extractTags(String htmlContent) {
        Set<String> tags = new HashSet<>();
        Pattern pattern = Pattern.compile("<(\\w+)[^>]*>");
        Matcher matcher = pattern.matcher(htmlContent);
        while (matcher.find()) {
            tags.add(matcher.group(0).toLowerCase());
        }
        return tags;
    }

    private static int findFragmentInHtml(String htmlContent, String fragment) {
        String normalizedFragment = fragment.toLowerCase();
        String textWithoutTags = htmlContent.replaceAll("<[^>]+>", "").toLowerCase();
        String[] lines = textWithoutTags.split("\n");
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains(normalizedFragment)) {
                return i;
            }
        }
        return -1;
    }

    private static void writeFile(String fileName, List<?> content) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Object line : content) {
                bw.write(line.toString());
                bw.write("\n");
            }
        }
    }

    private static List<String> getNotFoundFragments(List<String> fragments, List<Integer> foundLines) {
        List<String> notFound = new ArrayList<>();
        for (int i = 0; i < fragments.size(); i++) {
            if (foundLines.get(i) == -1) {
                notFound.add(fragments.get(i));
            }
        }
        return notFound;
    }
}