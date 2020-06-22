import java.util.Scanner;

public class StructFunction {
    public static void main(String[] args) {
        double x0, x, eps;
        int max_iter, n;
        Scanner in = new Scanner(System.in);

        System.out.print("x0=");
        x0 = in.nextDouble();

        System.out.print("max_iter=");
        max_iter = in.nextInt();

        System.out.print("eps=");
        eps = in.nextInt();

        n = 0;
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
            System.out.printf("Aprox. root is: %.4f", x);
        }
    }
}
