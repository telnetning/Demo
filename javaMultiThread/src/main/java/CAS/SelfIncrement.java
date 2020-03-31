import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SelfIncrement
 * 用来证明自增操作并不是原子的
 *
 * @since 2020-03-31
 */
public class SelfIncrement implements Runnable
{
    private static int value = 0;

    /**
     *   javap -c SelfIncrement.class 结果
     *
     *   public void run();
     *     Code:
     *        0: getstatic     #2                  // Field value:I
     *        3: iconst_1
     *        4: iadd
     *        5: putstatic     #2                  // Field value:I
     *        8: return
     *
     *   先获取值，再加1，回填值，没有原子性
     */
    @Override public void run()
    {
        value++;
    }

    public static void main(String[] args) throws InterruptedException
    {
        ExecutorService es = Executors.newCachedThreadPool();

        for (int i = 0; i < 10000; i++)
        {
            es.submit(new SelfIncrement());
        }

        System.out.println(value);
        es.shutdown();
    }
}
