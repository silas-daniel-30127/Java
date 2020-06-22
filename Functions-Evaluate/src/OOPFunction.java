public class OOPFunction {
    private double x0;
    private int max_iter;
    private int eps;
    private String display;

    public OOPFunction(double x0, int max_iter, int eps) {
        this.x0 = x0;
        this.max_iter = max_iter;
        this.eps = eps;
        display = "";
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
            aux = f(x) / ((f(x + h) - f(x)) / h);
            x = x - aux;
            n = n + 1;
        } while (Math.abs(aux) < eps && (n < max_iter));

        if (Math.abs(aux) >= eps) {
            display += "Accuracy not reached in" + n + " iterations";
        } else {
            display += String.format("Aprox. root is: %.5f", x);
            display += "\n iteratii = " + n;
        }
    }

    @Override
    public String toString() {
        return display;
    }
}
