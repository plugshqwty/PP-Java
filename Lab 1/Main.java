//.......................................Калечиц Александра, 5 группа

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.print("Input a number K (stepen dlya e): ");
    int k = in.nextInt();
    double e=10;
    if(k==-1){
        e=1/e;
    }
    if(k==0){
        e=1;
    }
    if(k>0){
        System.out.print("NOT VAL \n");
    }
    if(k<=-2){
        k=k*(-1);
        for (int i = k; i>1; i--){
            e=e*10;
        }
        e=1/e;
    }
    System.out.println("E = " + e + "\n" );

    System.out.print("Input a number X (-1;1): ");
    while (!in.hasNextDouble()) {
        System.out.println("Это не дробное число! Попробуйте снова:");
        in.next();
    }
    double x = in.nextFloat();
  //  System.out.println("X = " + x);
    double koren=1;
    int n=1;
    int copyn;
    int f=1,df=1;
    int dn=1;
    int ed=-1;
    int dwa=2;
    double modul;
    double copyx=x;
    double slog;
    while(1>0){
        x=copyx;
        copyn=n;
        f=1;
        df=1;
        dwa=2;
        slog=0;
        dn=2*n;
        while(copyn>0){
            f=f*copyn;
            copyn=copyn-1;
        }
        copyn=dn;
        while(copyn>0){
            df=df*copyn;
            copyn=copyn-1;
        }
        copyn=dn;
        while(copyn>1){
            dwa=dwa*2;
            copyn=copyn-1;
        }
        copyn =n;
        while(copyn>1){
            x=x*copyx;
            copyn=copyn-1;
        }
        ed=ed*(-1);
        slog=(ed* df);
        slog=slog/(dwa* (dn-1)* f*f)*x;
        //System.out.println("slog "+ slog);
        if (slog<0){
            modul=slog*(-1);
        }
        else {
            modul=slog;
        }
        if(modul>=e){
            koren=koren+slog;
            n++;
        }
        else{
           // System.out.println("last slog (menshe e)"+slog+ " индекс = "+ n);
            break;
        }
    }
    System.out.println("koren (ряд Тейлора) = "+koren);
/////////////////////////////////////////////////////////////////////
    double tenPowerK = Math.pow(10, -k);
   // System.out.println("10^K = " + tenPowerK);
    double koren1 = 1.0;
    int n1 = 1;
    double term;
    double modul1;
    double stepen;
    x=copyx;

        while (true) {
           // System.out.println("x = "+x);
            stepen=Math.pow(x,n1);
            //System.out.println("stepen "+ stepen);
            term =stepen * coefficient(n1);
           // System.out.println("TERM "+ term);
            //System.out.println("slog "+ term);
            if (term<0){
                modul1=term*(-1);
            }
            else {
                modul1=term;
            }

            if (modul1>=e){
                koren1=koren1+term;
                n1++;
            }
            else {
                // System.out.println("last slog (menshe e) " + term + " индекс = " + n);
                break;
            }
        }
    System.out.println("koren(Встроенные функции) = " + koren1);

    in.close();    }
    private static double coefficient(int n1) {
        if (n1 == 0) return 1;
        double doubleFactorial = 1;
         for (int i = 1; i < n1; i++) {
        doubleFactorial *= (2 * i - 1);        }
         double coeff=(Math.pow(-1, n1 - 1) * doubleFactorial) / (Math.pow(2, n1) * factorial(n1));
       //  System.out.println("coefff "+ coeff);
        return coeff;
    }
private static double factorial(int n1) {
        double result = 1;
    for (int i = 2; i <= n1; i++) {
        result *= i;
    }
    return result;
}}