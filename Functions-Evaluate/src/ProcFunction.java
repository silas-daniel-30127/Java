import java.util.Scanner;

public class ProcFunction {

    public static double readDoubleValues(String text) {
        Scanner in = new Scanner(System.in);
        System.out.print(text);
        return in.nextDouble();
    }

    public static int readIntValues(String text) {
        Scanner in = new Scanner(System.in);
        System.out.print(text);
        return in.nextInt();
    }

    public static void compute(double x0, int max_iter, int eps) {
        double x;
        int n = 0;
        x = x0;
        double aux;
        do {
            double f = x + 4 * Math.log(x) - 5;
            double f1 = 1 + 4 / x;
            aux = f / f1;
            x = x - aux;
            n = n + 1;
        } while (Math.abs(aux) > eps && (n < max_iter));

        if (Math.abs(aux) >= eps) {
            System.out.print("Accuracy not reached in" + n + " iterations");
        } else {
            System.out.printf("Aprox. root is: %.5f", x);
        }
    }

    public static void main(String[] args) {
        compute(readDoubleValues("x0 = "), readIntValues("max_iter = "), readIntValues("eps = "));
    }
}
