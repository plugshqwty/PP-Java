/**
 * ....................................Калечиц Александра, 5 группа.................................................
 * .............................................5 Лаба..............................................................
 * Постановка задачи
 * Необходимо прочитать данные, обработать их и записать выходные данные в заданном формате.
 *
 * Входные данные
 * Входной файл input содержит данные в формате CSV.
 * Каждая запись в файле расположена на новой строке.
 * Разделителем между полями одной записи является символ точка с запятой (';').
 * Если значения какого-то поля записи нет, то разделить все равно присутствует.
 * Обязательными для заполнения являются только те поля, по которым строятся запросы для выборки данных.
 *
 * Формат входных данных
 * Имеется список компаний.
 * Каждый элемент списка содержит следующие поля:
 *    Наименование (name)
 *     Краткое наименование (shortTitle)
 *     Дата актуализации (dateUpdate)
 *     Адрес (address)
 *     Дата основания (dateFoundation)
 *     Численность сотрудников (countEmployees)
 *     Аудитор (auditor)
 *     Телефон (phone)
 *     Email (email)
 *     Отрасль (branch)
 *     Вид деятельности (activity)
 *     Адрес страницы в Интернет (internetAddress/link)
 *
 * Выходные данные
 * 1. Прочитать данные из входного файла.
 * 2. Выбрать данные по запросу.
 * 3. Записать полученные данные в два файла (в XML-формате и JSON).
 *
 * Запросы
 * 1. Найти компанию по краткому наименованию.
 * 2. Выбрать компании по отрасли.
 * 3. Выбрать компании по виду деятельности.
 * 4. Выбрать компании по дате основания в определенном промежутке (с и по).
 * 5. Выбрать компании по численности сотрудников в определенном промежутке (с и по).
 *
 * Примечания
 * 1. Ваша программа должна игнорировать различие между строчными и прописными буквами.
 * 2. Необходимо вести статистику работы приложения в файле logfile.txt. Данные должны накапливаться.
 * Формат данных: дата и время запуска приложения; текст запроса.
 *
 */


import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.*;

class Company {
    String name;
    String shortTitle;
    String dateUpdate;
    String address;
    String dateFoundation; // Дата в формате "YYYY-MM-DD"
    int countEmployees;
    String auditor;
    String phone;
    String email;
    String branch;
    String activity;
    String internetAddress;

    public Company(String[] data) {
        this.name = data[0];
        this.shortTitle = data[1];
        this.dateUpdate = data[2];
        this.address = data[3];
        this.dateFoundation = data[4];
        this.countEmployees = data[5].isEmpty() ? 0 : Integer.parseInt(data[5]);
        this.auditor = data[6];
        this.phone = data[7];
        this.email = data[8];
        this.branch = data[9];
        this.activity = data[10];
        this.internetAddress = data[11];
    }

    public String toJSON() {
        return String.format(
                "{\n\"name\":\"%s\",\n\"shortTitle\":\"%s\",\n\"dateUpdate\":\"%s\",\n\"address\":\"%s\",\n\"dateFoundation\":\"%s\",\n"
                        + "\"countEmployees\":%d,\n\"auditor\":\"%s\",\n\"phone\":\"%s\",\n\"email\":\"%s\",\n\"branch\":\"%s\",\n"
                        + "\"activity\":\"%s\",\n\"internetAddress\":\"%s\"}",
                name, shortTitle, dateUpdate, address, dateFoundation, countEmployees, auditor, phone, email, branch, activity, internetAddress
        );
    }
}

class CompanyService {
    List<Company> companies;

    public CompanyService(List<Company> companies) {
        this.companies = companies;
    }

    public List<Company> findByShortTitle(String shortTitle) {
        return companies.stream()
                .filter(c -> c.shortTitle.equalsIgnoreCase(shortTitle))
                .collect(Collectors.toList());
    }

    public List<Company> findByBranch(String branch) {
        return companies.stream()
                .filter(c -> c.branch.equalsIgnoreCase(branch))
                .collect(Collectors.toList());
    }

    public List<Company> findByActivity(String activity) {
        return companies.stream()
                .filter(c -> c.activity.equalsIgnoreCase(activity))
                .collect(Collectors.toList());
    }

    public List<Company> findByFoundationDate(String fromDate, String toDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate from = LocalDate.parse(fromDate, formatter);
        LocalDate to = LocalDate.parse(toDate, formatter);

        return companies.stream()
                .filter(c -> {
                    try {
                        LocalDate companyDate = LocalDate.parse(c.dateFoundation, formatter);
                        return !companyDate.isBefore(from) && !companyDate.isAfter(to);
                    } catch (DateTimeParseException e) {
                        return false; // Пропускаем компании с некорректной датой
                    }
                })
                .collect(Collectors.toList());
    }

    public List<Company> findByEmployeeCount(int min, int max) {
        return companies.stream()
                .filter(c -> c.countEmployees >= min && c.countEmployees <= max)
                .collect(Collectors.toList());
    }

    public void writeToXML(String filename, List<Company> companies) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("<companies>\n");
            for (Company company : companies) {
                writer.write("  <company>\n");
                writer.write(String.format("    <name>%s</name>\n", company.name));
                writer.write(String.format("    <shortTitle>%s</shortTitle>\n", company.shortTitle));
                writer.write(String.format("    <dateUpdate>%s</dateUpdate>\n", company.dateUpdate));
                writer.write(String.format("    <address>%s</address>\n", company.address));
                writer.write(String.format("    <dateFoundation>%s</dateFoundation>\n", company.dateFoundation));
                writer.write(String.format("    <countEmployees>%d</countEmployees>\n", company.countEmployees));
                writer.write(String.format("    <auditor>%s</auditor>\n", company.auditor));
                writer.write(String.format("    <phone>%s</phone>\n", company.phone));
                writer.write(String.format("    <email>%s</email>\n", company.email));
                writer.write(String.format("    <branch>%s</branch>\n", company.branch));
                writer.write(String.format("    <activity>%s</activity>\n", company.activity));
                writer.write(String.format("    <internetAddress>%s</internetAddress>\n", company.internetAddress));
                writer.write("  </company>\n");
            }
            writer.write("</companies>\n");
        }
    }

    public void writeToJSON(String filename, List<Company> companies) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("[\n");
            for (int i = 0; i < companies.size(); i++) {
                writer.write(companies.get(i).toJSON());
                if (i < companies.size() - 1) {
                    writer.write(",\n");
                }
            }
            writer.write("\n]");
        }
    }
}

class DataParser {
    public List<Company> parseCSV(String filename) throws IOException {
        List<Company> companies = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";", -1);
                // Проверяем и преобразуем дату, если необходимо
                if (data.length > 4 && data[4].contains(".")) { // Если дата в формате DD.MM.YYYY
                    data[4] = convertDateFormat(data[4]);
                }
                companies.add(new Company(data));
            }
        }
        return companies;
    }

    private String convertDateFormat(String date) {
        try {
            DateTimeFormatter sourceFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(date, sourceFormat);
            return targetFormat.format(localDate);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return date; // Возвращаем исходную дату в случае ошибки
        }
    }
}

class Logger {
    public static void log(String message) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("logfile.txt", true))) {
            bw.write(new Date() + " - " + message);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            DataParser parser = new DataParser();
            List<Company> companies = parser.parseCSV("input.csv");

            CompanyService service = new CompanyService(companies);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Выберите запрос для поиска компаний:");
            System.out.println("1. Найти компанию по краткому наименованию");
            System.out.println("2. Найти компании по отрасли");
            System.out.println("3. Найти компании по виду деятельности");
            System.out.println("4. Найти компании по диапазону даты основания");
            System.out.println("5. Найти компании по диапазону численности сотрудников");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline character

            List<Company> foundCompanies = null;

            switch (choice) {
                case 1:
                    System.out.print("Введите краткое наименование: ");
                    String shortTitle = scanner.nextLine();
                    foundCompanies = service.findByShortTitle(shortTitle);
                    Logger.log("Пользователь искал компанию по краткому наименованию: " + shortTitle);
                    break;
                case 2:
                    System.out.print("Введите отрасль: ");
                    String branch = scanner.nextLine();
                    foundCompanies = service.findByBranch(branch);
                    Logger.log("Пользователь искал компании по отрасли: " + branch);
                    break;
                case 3:
                    System.out.print("Введите вид деятельности: ");
                    String activity = scanner.nextLine();
                    foundCompanies = service.findByActivity(activity);
                    Logger.log("Пользователь искал компании по виду деятельности: " + activity);
                    break;
                case 4:
                    System.out.print("Введите дату основания с (YYYY-MM-DD): ");
                    String fromDate = scanner.nextLine();
                    System.out.print("Введите дату основания по (YYYY-MM-DD): ");
                    String toDate = scanner.nextLine();
                    foundCompanies = service.findByFoundationDate(fromDate, toDate);
                    Logger.log("Пользователь искал компании по диапазону даты основания: " + fromDate + " по " + toDate);
                    break;
                case 5:
                    System.out.print("Введите минимальное количество сотрудников: ");
                    int minEmployees = scanner.nextInt();
                    System.out.print("Введите максимальное количество сотрудников: ");
                    int maxEmployees = scanner.nextInt();
                    foundCompanies = service.findByEmployeeCount(minEmployees, maxEmployees);
                    Logger.log("Пользователь искал компании по диапазону численности сотрудников: " + minEmployees + " - " + maxEmployees);
                    break;
                default:
                    System.out.println("Неверный выбор.");
                    return;
            }

            // Проверяем, есть ли найденные компании
            if (foundCompanies != null && !foundCompanies.isEmpty()) {
                service.writeToJSON("output.json", foundCompanies);
                service.writeToXML("output.xml", foundCompanies);
                System.out.println("Результаты записаны в выходные файлы.");
            } else {
                System.out.println("Компании, соответствующие вашим критериям, не найдены.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}