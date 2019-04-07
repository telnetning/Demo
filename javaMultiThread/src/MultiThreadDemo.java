/**
 * Use Runnable is better than Thread
 * 1. you can extends only one parent class(Thread), but implements many
 *  interfaces is better
 * 2. In certain circumstances, you can just use Runnable.run() without Thread
 * 3. Seperated the job from the runner
 *
 * Refenence:
 * 1. https://stackoverflow.com/questions/541487/implements-runnable-vs-extends-thread-in-java
 * 2. http://tutorials.jenkov.com/java-concurrency/creating-and-starting-threads.html#java-threads-video-tutorial
 */

// First: Extends Thread
class MultiThreadingExtendsThread extends Thread {
    @Override public void run()
    {
        System.out.println("Hello, MultiThreadingExtendsThread!");
    }
}

// Second: Implements Runnable with a class
class MultiThreadingExtendsRunnable implements Runnable {
    @Override public void run()
    {
        System.out.println("Hello, MultiThreadingExtendsRunnable!");
    }
}

public class MultiThreadDemo
{
    public static void main(String[] args) {

        // Third: A Anonymous class implements Runnable
        Runnable myRunner = new Runnable()
        {
            @Override public void run()
            {
                System.out.println("Anonymous implments of Runnable");
            }
        };

        // Fouth: Lambda implements Runnable
        Runnable lambdaRunner = () -> {
            System.out.println("Lambda implements Runnable");
        };

        for(int i = 0; i < 10; i++) {
            MultiThreadingExtendsThread mt = new MultiThreadingExtendsThread();
            mt.start();

            Thread mter = new Thread(new MultiThreadingExtendsRunnable());
            mter.start();

            Thread anonymt = new Thread(myRunner);
            anonymt.start();

            Thread lamdbamt = new Thread(lambdaRunner);
            lamdbamt.start();
        }
    }
}
