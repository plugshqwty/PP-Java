//...........................Калечиц Александра, 5 группа (ArrayList)
/*
 4.	Среди строк заданной матрицы, содержащих только нечетные элементы,
 найти строку с максимальной суммой модулей элементов*/

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<ArrayList<Integer>> matrix;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите размерность матрицы: ");
        int n = in.nextInt();
        int m = in.nextInt();

        matrix = new ArrayList<>();

        System.out.println("Введите элементы матрицы: ");
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                row.add(in.nextInt());
            }
            matrix.add(row); // добавляем строку в матрицу
        }

        System.out.println("Вывод элементов матрицы: ");
        for (ArrayList<Integer> row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }

        int maxSum = 0;
        int index = -1;

        for (int i = 0; i < n; i++) {
            ArrayList<Integer> row = matrix.get(i);
            int sum = 0;
            boolean allOdd = true;

            for (int j = 0; j < m; j++) {
                int element = row.get(j);
                if (element % 2 == 0) {
                    allOdd = false; // Если есть четный элемент, строка не подходит
                    break;
                }
                sum += Math.abs(element); // Сумма модулей
            }
            if (allOdd && sum > maxSum) {
                maxSum = sum;
                index = i + 1;
            }
        }


        if (index != -1) {
            System.out.println("Максимальная сумма модулей строки, состоящей только из нечетных элементов: " + maxSum);
            System.out.println("Номер этой строки: " + index);
        } else {
            System.out.println("Нет строки только с нечетными элементами");
        }

        in.close();
    }
}