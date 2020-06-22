public class F3 extends AbstractFunctionOO {

    public F3(double x0, int max_iter, int eps, String fun_name) {
        super(x0, max_iter, eps, fun_name);
    }

    @Override
    public double evaluate(double x) {
        return x * x + 2 * x + 1;
    }
}
