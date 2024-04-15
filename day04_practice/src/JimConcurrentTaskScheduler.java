import java.util.concurrent.*;

public class JimConcurrentTaskScheduler {
    protected static ExecutorService executorService = null;
    protected static Object executorServiceMutex;

    public static synchronized <T> Future<T> RunTask(Callable<T> r) {
        if (executorService == null) {
            executorService = Executors.newCachedThreadPool();
        }

        return executorService.submit(r);
    }

    public static synchronized boolean isShutdown() {
        if (executorService == null) {
            executorService = Executors.newCachedThreadPool();
        }

        return executorService.isShutdown();
    }

    public static synchronized boolean isTerminated() {
        if (executorService == null) {
            executorService = Executors.newCachedThreadPool();
        }

        return executorService.isTerminated();
    }
}
