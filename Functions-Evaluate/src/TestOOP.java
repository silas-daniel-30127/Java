public class TestOOP {
    public static void main(String[] args) {
        OOPFunction F1 = new OOPFunction(1., 3, 100);
        OOPFunction F2 = new OOPFunction(-2., 0, 0);
        System.out.println(F1);
        System.out.println(F2);

        F1 fa = new F1(12, 2, 110, "x + 4 * ln(x) - 5");
        F2 fb = new F2(5, 4, 12, "x^2 - 2^x");

        AbstractFunctionOO[] array = new AbstractFunctionOO[10];
        array[0] = fa;
        array[1] = fb;
        array[2] = new F3(1, 10, 1, "x * x + 2 * x + 1");

        for (AbstractFunctionOO abstractFunctionOO : array) {
            if (abstractFunctionOO != null) {
                System.out.println(abstractFunctionOO);
            }
        }
    }
}
