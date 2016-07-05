package JoiningAndSynchronizeThreads_3;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Worker
{

    private int count = 0;

    public static void main(String[] args)
    {
        Worker worker = new Worker();
        worker.doWork();
    }

    public synchronized void increment(String threadName) throws InterruptedException
    {
        count++;
        System.out.println("Thread in Progress: " + threadName + " and count is: " + count);
    }

    public void doWork()
    {
        Thread thread1 = new Thread(new Runnable()
        {
            public void run()
            {
                for (int i = 0; i < 10; i++)
                {
                    try
                    {
                        increment(Thread.currentThread().getName());
                    }
                    catch (InterruptedException ex)
                    {
                        Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        thread1.start();
        Thread thread2 = new Thread(new Runnable()
        {
            public void run()
            {
                for (int i = 0; i < 10; i++)
                {
                    try
                    {
                        increment(Thread.currentThread().getName());
                    }
                    catch (InterruptedException ex)
                    {
                        Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        thread2.start();

        try
        {
            thread1.join();
            thread2.join();
        }
        catch (InterruptedException ignored)
        {
        }
        System.out.println("Count is: " + count);
    }
}
