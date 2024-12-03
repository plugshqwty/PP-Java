/*
*Модифицировать условие задачи учитывая свои пожелания
Возможно  авторское условие задачи
Использовать контейнеры:
 Vector, ArrayList, LinkedList, HashSet, TreeSet, HashMap, TreeMap.)

1) Задача "контакты"
а) разработать класс Контакт, определяющий запись в электронной книге мобильного
телефона и содержащий по меньшей мере следующие поля:
- *Наименование (имя человека или организации)
- *Номер телефона мобильного
- Номер телефона рабочего
- Номер телефона (домашнего)
- Адрес электронной почты
- Адрес веб-страницы
- Адрес

Обязательными является поля помеченные *, остальные поля могут быть пустыми

б) класс Контакт должен реализовать:
-интерфейс Comparable и Comparator с возможностью выбора одного из полей для сравнения
-интерфейс Iterator - индексатор по всем полям объекта Контакт
-метод для сохранения значений всех полей в строке текста (переопределить toString())
-конструктор или метод для инициализации объекта из строки текста

в) Для тестирования класса Контакт, создать консольное приложение позволяющее
создать небольшой массив контактов и напечатать отсортированными по
выбранному полю.

* */

//................................... Калечиц Александра, 5 группа .............................................

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Contact> contacts = new ArrayList<>();

        // Чтение данных из файла
        try (BufferedReader br = new BufferedReader(new FileReader("contacts.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                contacts.add(Contact.fromString(line));
            }
            System.out.println("Contacts read: " + contacts.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Опция выбора поля для сортировки
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите поле для сортировки:");
        System.out.println("1. Name");
        System.out.println("2. Mobile Number");
        System.out.println("3. Work Number");
        System.out.println("4. Home Number");
        System.out.println("5. Email");
        System.out.println("6. Webpage");
        System.out.println("7. Address");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                contacts.sort(Comparator.comparing(Contact::getName));
                break;
            case 2:
                contacts.sort(Comparator.comparing(Contact::getMobileNumber));
                break;
            case 3:
                contacts.sort(Comparator.comparing(Contact::getWorkNumber));
                break;
            case 4:
                contacts.sort(Comparator.comparing(Contact::getHomeNumber));
                break;
            case 5:
                contacts.sort(Comparator.comparing(Contact::getEmail));
                break;
            case 6:
                contacts.sort(Comparator.comparing(Contact::getWebpage));
                break;
            case 7:
                contacts.sort(Comparator.comparing(Contact::getAddress));
                break;
            default:
                System.out.println("Неверный выбор. Сортировка по имени по умолчанию.");
                contacts.sort(Comparator.comparing(Contact::getName));
                break;
        }
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
        // Запись результата в файл
        try (PrintWriter pw = new PrintWriter(new FileWriter("output.txt"))) {
            for (Contact contact : contacts) {
                pw.println(contact);
            }
            System.out.println("Contacts written to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
