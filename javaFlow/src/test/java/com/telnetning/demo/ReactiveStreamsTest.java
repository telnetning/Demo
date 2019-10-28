package com.telnetning.demo;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class ReactiveStreamsTest
{
    @Test public void givenPublisher_whenRequestForOnlyOneElement_thenShouldConsumeOnlyThatOne()
        throws InterruptedException
    {
        // given
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        EndSubscriber<String> subscriber = new EndSubscriber<>(1);
        publisher.subscribe(subscriber);
        List<String> items = List.of("1", "x", "2", "x", "3", "x");
        List<String> expected = List.of("1");

        // when
        assertThat(publisher.getNumberOfSubscribers()).isEqualTo(1);
        items.forEach(publisher::submit); // invoke every subscribe's onNext()
        publisher.close();

        // then
        // For Awaitility 3.X, it’s untilAsserted() method, not until().
        await().atMost(1000, TimeUnit.MILLISECONDS)
            .untilAsserted(() -> assertThat(subscriber.consumedElements).containsExactlyElementsOf(expected));
    }

    @Test
    public void givenPublisher_whenSubscribeToIt_thenShouldConsumeAllElements() throws InterruptedException {
        //given
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        EndSubscriber<String> subscriber = new EndSubscriber<>(6);
        publisher.subscribe(subscriber);
        List<String> items = List.of("1", "x", "2", "x", "3", "x");

        //when
        assertThat(publisher.getNumberOfSubscribers()).isEqualTo(1);
        items.forEach(publisher::submit);
        publisher.close();

        //then

        await().atMost(1000, TimeUnit.MILLISECONDS).untilAsserted(
            () -> assertThat(subscriber.consumedElements).containsExactlyElementsOf(items)
        );
    }

    /*
     * 使用 Processor，Processor 是 Publisher 的消费者，是 Subscriber 的生产者，负责数据转换
     */
    @Test
    public void givenPublisher_whenSubscribeAndTransformElements_thenShouldConsumeAllElements() throws InterruptedException {
        //given
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        TransformProcessor<String, Integer> transformProcessor = new TransformProcessor<>(Integer::parseInt);
        EndSubscriber<Integer> subscriber = new EndSubscriber<>(3);
        List<String> items = List.of("1", "2", "3");
        List<Integer> expectedResult = List.of(1, 2, 3);

        //when
        publisher.subscribe(transformProcessor);
        transformProcessor.subscribe(subscriber);
        items.forEach(publisher::submit);
        publisher.close();

        //then
        await().atMost(1000, TimeUnit.MILLISECONDS).untilAsserted(
            () -> assertThat(subscriber.consumedElements).containsExactlyElementsOf(expectedResult)
        );
    }
}
