//.......................................Калечиц Александра, 5 группа (VECTOR)
//Подсчитать количество строк заданной матрицы, являющихся перестановкой чисел 1, 2, ..., 20.
/*
matrix
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
2 2 2 2 2 2 4 5 7 4 8 0 8 6 5 5 3 7 8 9
20 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1
20 1 19 2 18 3 17 4 16 5 15 6 14 7 13 8 12 9 11 10
5 6 7 8 9 0 3 5 6 7 8 9 4 3 2 1 5 6 7 3
10 9 8 7 6 5 4 3 2 1 20 19 18 17 16 15 14 13 12 11
21 44 66 77 19 35 11 18 5 6 1 1 2 4 5 6 7 8 9 8
1 20 2 19 3 18 4 17 5 16 6 15 7 14 8 13 9 12 10 11

8 строк всего
5 являются перестановкой
*/

import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static int Perestanovka(int n, Vector<Vector<Integer>> matrix) {
        Vector<Integer> a = new Vector<>();
        Vector<Integer> b = new Vector<>();


        for (int i = 1; i <= 20; i++) {
            a.add(i);//заполнение двух массивов add()~=  (первоначальное заполнение)
            b.add(0);
        }

        int count = 0;
        int t=0;


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 20; j++) {
                for (int k = 0; k < 20; k++) {
                    if (matrix.get(i).get(j).equals(a.get(k))) {   //equals()~==
                        b.set(k, b.get(k) + 1); // get~[]
                    }
                }
            }
            for (int s = 0; s < 20; s++) {
                if (b.get(s) == 1) {
                    t++;
                }
            }
            if (t == 20) {
                count++;
            }
            t = 0;
            for (int s = 0; s < 20; s++) {
                b.set(s, 0); // set~=  (присваивание)
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Введите количество строк матрицы:");
        int n = in.nextInt();

        Vector<Vector<Integer>> matrix = new Vector<>();  // матрица вектор

        System.out.println("Введите элементы матрицы:");
        for (int i = 0; i < n; i++) {
            Vector<Integer> row = new Vector<>();
            for (int j = 0; j < 20; j++) {       //заполняем вектор строку
                row.add(in.nextInt());
            }
            matrix.add(row); // и добавляем строку в матрицу, т.е. вектрок векторов
        }

        int count = Perestanovka(n, matrix);
        System.out.println("Количество строк, являющихся перестановкой чисел от 1 до 20: " + count);
    }
}