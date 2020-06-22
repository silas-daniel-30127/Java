public abstract class AbstractFunctionOO extends AbstractFunction {
    double x0, F, FPrime;
    int max_iter, eps;
    String display;
    String fun_name;

    public AbstractFunctionOO(double x0, int max_iter, int eps, String fun_name) {
        this.x0 = x0;
        this.max_iter = max_iter;
        this.eps = eps;
        display = "";
        this.fun_name = fun_name;
        compute();
    }

    private double f(double x) {
        return x + 4 * Math.log(x) - 5;
    }

    private void compute() {
        double x;
        int n = 0;
        x = x0;
        double aux;
        do {
            double h = 0.00001;
            F = evaluate(x);
            FPrime = (evaluate(x + h) - F) / h;
            aux = F / FPrime;
            x = x - aux;
            n = n + 1;
        } while (Math.abs(aux) < eps && (n < max_iter));

        if (Math.abs(aux) >= eps) {
            display += "Accuracy not reached in" + n + " iterations";
        } else {
            display += String.format("Aprox. root is: %.5f", x);
        }
    }

    @Override
    public String toString() {
        return fun_name + ':' + '\n' + display;
    }
}
