// Калечиц Александра, 5 группа
/**
 *Написать программу для консольного процесса, который состоит из двух потоков: Main и Sort.
 *
 * Поток Main должен выполнить следующие действия:
 * •	создать массив целых чисел, размерность вводится пользователем с клавиатуры, элементы которого задаются программно числами от 1 до размерности;
 * •	вывести размерность и элементы исходного массива на консоль;
 * •	запросить у пользователя порядок сортировки массива (возрастание или убывание);
 * •	запустить поток Sort;
 * •	вывести на консоль элементы отсортированного массива после завершения работы потока Sort.
 *
 * Поток Sort должен выполнить следующие действия:
 * •	отсортировать элементы введенного массива в соответствии с заданным порядком (использовать Comparator).
 *
 */

import java.util.*;
import java.util.concurrent.Exchanger;

// Класс потока для сортировки массива
class SortThread implements Runnable {
    private final Exchanger<List<Integer>> arrayExchanger; // Обменник для массива
    private final Exchanger<Integer> orderExchanger; // Обменник для порядка сортировки

    SortThread(Exchanger<List<Integer>> arrayExchanger, Exchanger<Integer> orderExchanger) {
        this.arrayExchanger = arrayExchanger;
        this.orderExchanger = orderExchanger;
    }

    // Метод, выполняемый в потоке сортировки
    public void run() {
        System.out.println("Начало сортировки");
        try {
            // Получаем массив от главного потока и порядок сортировки
            List<Integer> array = arrayExchanger.exchange(new ArrayList<>());
            Integer order = orderExchanger.exchange(0);

            // Сортируем массив в зависимости от порядка
            if (order == 1) {
                Collections.sort(array); // По возрастанию
            } else {
                Collections.sort(array, Collections.reverseOrder()); // По убыванию
            }

            // Отправляем отсортированный массив обратно
            arrayExchanger.exchange(array);
        } catch (InterruptedException ex) {
            System.out.println("Поток сортировки прерван: " + ex.getMessage());
        }
        System.out.println("Сортировка завершена");
    }
}

// Класс потока для главного процесса
class MainThread implements Runnable {
    private final Exchanger<List<Integer>> arrayExchanger; // Обменник для массива
    private final Exchanger<Integer> orderExchanger; // Обменник для порядка сортировки

    MainThread(Exchanger<List<Integer>> arrayExchanger, Exchanger<Integer> orderExchanger) {
        this.arrayExchanger = arrayExchanger;
        this.orderExchanger = orderExchanger;
    }

    // Метод для получения размера массива от пользователя
    public static int getArraySize() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите размер массива: ");
        return scanner.nextInt();
    }

    // Метод для создания массива с перемешанными числами от 1 до size
    public static List<Integer> createArray(int size) {
        List<Integer> array = new ArrayList<>(size);
        for (int i = 1; i <= size; i++) {
            array.add(i);  // Заполняем числами от 1 до size
        }
        Collections.shuffle(array); // Перемешиваем массив
        return array;
    }

    // Метод для вывода информации о массиве
    public static void printArrayInfo(List<Integer> array) {
        System.out.println("Размер: " + array.size() + "; Массив: " + array);
    }

    // Метод для запроса порядка сортировки у пользователя
    public static int getSortOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите порядок сортировки.");
        System.out.println("1 - По возрастанию");
        System.out.println("0 - По убыванию");
        return scanner.nextInt();
    }

    // Метод, выполняемый в главном потоке
    public void run() {
        System.out.println("Главный поток запущен");
        int size = getArraySize(); // Получаем размер массива
        List<Integer> array = createArray(size); // Создаем массив
        printArrayInfo(array); // Выводим информацию о массиве

        try {
            // Обмениваемся массивом и порядком сортировки с потоком сортировки
            arrayExchanger.exchange(array);
            orderExchanger.exchange(getSortOrder());
            array = arrayExchanger.exchange(new ArrayList<>()); // Получаем отсортированный массив
        } catch (InterruptedException ex) {
            System.out.println("Главный поток прерван: " + ex.getMessage());
        }

        System.out.println("Отсортированный массив: " + array); // Выводим отсортированный массив
        System.out.println("Главный поток завершен");
    }
}

// Главный класс для запуска программы
public class Main {
    public static void main(String[] args) {
        Exchanger<List<Integer>> arrayExchanger = new Exchanger<>(); // Создаем обменник для массива
        Exchanger<Integer> orderExchanger = new Exchanger<>(); // Создаем обменник для порядка сортировки

        // Создаем и запускаем потоки
        Thread mainThread = new Thread(new MainThread(arrayExchanger, orderExchanger));
        Thread sortThread = new Thread(new SortThread(arrayExchanger, orderExchanger));

        mainThread.start(); // Запускаем главный поток
        sortThread.start(); // Запускаем поток сортировки
    }
}