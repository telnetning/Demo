import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * SelfIncrement
 * 用来证明自增操作并不是原子的
 *
 * @since 2020-03-31
 */
public class SelfIncrementWithAtomic implements Runnable
{
    // 需要使用 volatile，否则还是无法保证
    // volatile 保证修改对其它线程可见
    private static volatile AtomicInteger value = new AtomicInteger(0);

    @Override public void run()
    {
        value.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException
    {
        ExecutorService es = Executors.newCachedThreadPool();

        for (int i = 0; i < 10000; i++)
        {
            es.submit(new SelfIncrementWithAtomic());
        }

        System.out.println(value);
        es.shutdown();
    }
}
