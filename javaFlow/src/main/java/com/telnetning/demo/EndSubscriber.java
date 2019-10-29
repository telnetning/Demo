package com.telnetning.demo;

/*
 * demo 来源于 https://github.com/eugenp/tutorials/tree/master/core-java-modules
 * /core-java-9/src/main/java/com/baeldung/java9/streams.reactive
 */

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.atomic.AtomicInteger;

public class EndSubscriber<T> implements Flow.Subscriber<T>
{

    private AtomicInteger howMuchMessagesConsume;
    private Flow.Subscription subscription;
    public List<T> consumedElements = new LinkedList<>();

    public EndSubscriber(Integer howMuchMessagesConsume) {
        this.howMuchMessagesConsume
            = new AtomicInteger(howMuchMessagesConsume);
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    /*
     * Subscriber 自己决定请求的数据量大小
     */
    @Override
    public void onNext(T item) {
        howMuchMessagesConsume.decrementAndGet();
        System.out.println("Got : " + item);
        consumedElements.add(item);
        if (howMuchMessagesConsume.get() > 0) {
            subscription.request(1);
        }
    }

    @Override public void onError(Throwable throwable)
    {
        throwable.printStackTrace();
    }

    @Override public void onComplete()
    {
        System.out.println("Done");
    }
}