//................................Калечиц Александра, 5 группа(Random)

/*32.	Даны две действительные квадратные матрицы порядка n.
 Получить новую матрицу путем прибавления к элементам каждого столбца
 первой матрицы произведения элементов соответствующих строк второй матрицы.
*/

import java.util.Random;
import java.util.Scanner;
public class Main {
    public static int digit(int c, int n,int [][]matrix) {
        int proizv=1;
        int x;
        for(int i=0;i<n;i++) {
           x=matrix[c][i];
            proizv=proizv*matrix[c][i];
        }

        return proizv;
    }

    public static void main(String[] args) {
     Scanner in = new Scanner(System.in);
     Random random = new Random(); // Создаем экземпляр Random
     System.out.println("Введите размерность матриц: ");
     int n=in.nextInt();
     int [][]matrix1=new int[n][n];
     int [][]matrix2=new int[n][n];
     //System.out.println("Введите элементы3 матриц: ");

     for(int i=0;i<n;i++) {
         for(int j=0;j<n;j++) {
             matrix1[i][j]=random.nextInt(10);
         }
     }
        System.out.println("Элементы первой матрицы (использован класс Random): ");
     for(int i=0;i<n;i++) {
         for(int j=0;j<n;j++) {
             System.out.print(matrix1[i][j]+" ");
       }
         System.out.println();
     }
        //System.out.println("Введите элементы матриц: ");
     for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                matrix2[i][j]=random.nextInt(10);
            }
     }
     System.out.println("Элементы второй матрицы (использован класс Random): ");
     for(int i=0;i<n;i++) {
         for(int j=0;j<n;j++) {
                System.out.print(matrix2[i][j]+" ");
         }
            System.out.println();
     }

     int [][]matrix3=new int[n][n];
     int g=0;
     int c=0;
     int k=0;
     int t=0;
     int h=0;
      for(int i=0;i<n;i++){
          h=digit(g,n,matrix2);
          for(int j=0;j<n;j++){
                  c=matrix1[k][t];
                  k++;
              matrix3[j][i]=c+h;
          }
          g++;
          t++;
          k=0;
      }
        System.out.println("Полученая матрица: ");
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                System.out.print(matrix3[i][j]+ " ");
            }
            System.out.println();
        }

    }
}