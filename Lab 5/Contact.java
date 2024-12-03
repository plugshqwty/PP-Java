import java.util.*;
import java.util.stream.Collectors;

public class Contact implements Comparable<Contact>, Comparator<Contact>, Iterable<String> {
    // Поля
    private String name;
    private String mobileNumber;
    private String workNumber;
    private String homeNumber;
    private String email;
    private String webpage;
    private String address;

    // Конструктор
    public Contact(String name, String mobileNumber, String workNumber, String homeNumber, String email, String webpage, String address) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.workNumber = workNumber;
        this.homeNumber = homeNumber;
        this.email = email;
        this.webpage = webpage;
        this.address = address;
    }

    // Переопределяем метод toString()
    @Override
    public String toString() {
        return "Name: " + name + ", Mobile: " + mobileNumber + ", Work: " + workNumber + ", Home: " + homeNumber +
                ", Email: " + email + ", Webpage: " + webpage + ", Address: " + address;
    }

    // Реализация интерфейса Comparable
    @Override
    public int compareTo(Contact other) {
        return this.name.compareTo(other.name);
    }

    // Реализация интерфейса Comparator
    @Override
    public int compare(Contact c1, Contact c2) {
        return c1.name.compareTo(c2.name);
    }

    // Реализация интерфейса Iterable
    @Override
    public Iterator<String> iterator() {
        return Arrays.asList(name, mobileNumber, workNumber, homeNumber, email, webpage, address).iterator();
    }

    // Метод для инициализации объекта из строки текста
    public static Contact fromString(String contactStr) {
        String[] parts = contactStr.split(",");
        return new Contact(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
    }

    // Геттеры для полей, чтобы Comparator мог их использовать
    public String getName() {
        return name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getWebpage() {
        return webpage;
    }

    public String getAddress() {
        return address;
    }
}
