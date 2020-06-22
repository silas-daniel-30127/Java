public class F1 extends AbstractFunctionOO {

    public F1(double x0, int max_iter, int eps, String fun_name) {
        super(x0, max_iter, eps, fun_name);
    }

    @Override
    public double evaluate(double x) {
        return x + 4 * Math.log(x) - 5;
    }
}
