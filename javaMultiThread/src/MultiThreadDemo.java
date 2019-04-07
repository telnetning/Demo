
class MultiThreading extends Thread {
    @Override public void run()
    {
        System.out.println("Hello, MultiThreading!");
    }
}

public class MultiThreadDemo
{
    public static void main(String[] args) {
        for(int i = 0; i < 10; i++) {
            MultiThreading mt = new MultiThreading();
            mt.run();
        }
    }
}
