import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        FactorialCallable r01to10 = new FactorialCallable(1, 10);
        FactorialCallable r11to20 = new FactorialCallable(11, 20);

        Future<Long> f1 = JimConcurrentTaskScheduler.RunTask(r01to10);
        Future<Long> f2 = JimConcurrentTaskScheduler.RunTask(r11to20);
        while (!(f1.isDone() && f2.isDone())) {
            System.out.println("Not done! Waiting...");
            try {
                Thread.sleep(10);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        System.out.printf("20! = %d", r01to10.getResult() * r11to20.getResult());
    }
}