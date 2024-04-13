public class JimMath {
    public static int divide(int numerator, int denominator) {
        int result = 0;
        try {
            result = Math.divideExact(numerator, denominator)            ;
        } catch (ArithmeticException ex) {
            System.out.println("Exception: " + ex.getLocalizedMessage());
            return 0;
        }
        return result;
    }
}
