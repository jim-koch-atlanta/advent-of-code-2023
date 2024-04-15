import java.util.concurrent.Callable;

public class FactorialCallable implements Callable<Long> {
    protected long lower, upper, result;
    public FactorialCallable(int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public long getResult() {
        return this.result;
    }

    @Override
    public Long call() {
        System.out.println("this.result = " + this.result);
        this.result = this.lower;
        System.out.println("this.result = " + this.result);
        for (long i = this.lower + 1; i <= this.upper; i++) {
            System.out.println("this.result = " + this.result);
            this.result *= i;
        }

        return this.result;
    }
}