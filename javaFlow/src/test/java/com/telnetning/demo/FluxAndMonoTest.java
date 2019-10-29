package com.telnetning.demo;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class FluxAndMonoTest
{
    @Test
    public void createFlux() {
        Flux.just("Hello", "World").subscribe(System.out::println);

        // generate 同步
        Flux.generate(sink -> {
            sink.next("Hello");
            sink.complete();
        }).subscribe(System.out::println);

        // create 支持同步和异步的消息产生
        Flux.create(sink -> {
            for (int i = 0; i < 10; i++)
            {
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out::println);
    }

    @Test
    public void transformFluxAndMono() {
        Flux.range(1,10).buffer(5, 2).subscribe(System.out::println);
        Flux.range(-10, 10).bufferTimeout(5, Duration.ofSeconds(10))
            .subscribe(System.out::println);

        Flux.range(1, 10).map(x -> x * x).buffer(5).subscribe(System.out::println);
        Flux.range(1,10).flatMap(x -> Mono.just(x * x)).buffer(10).subscribe(System.out::println);
        Flux.range(10,20).flatMap(x -> Mono.delay(Duration.ofSeconds(1000))).subscribe(System.out::println);

        // Flux 转成序列迭代，以及，window 使用
        for (Flux<Integer> integerFlux : Flux.range(1, 10).window(4).toIterable())
        {
            integerFlux.subscribe(System.out::println);
            System.out.println("--------");
        }
    }

    @Test
    public void filterFluxAndMono() {
        Flux.range(1, 100).take(10).subscribe(System.out::println);
    }

    @Test
    public void combineFluxAndMono() {
        Flux.just("a", "b").zipWith(Flux.just("A", "B"),  (s1, s2) ->
            String.format("%s:%s", s1, s2)
        ).subscribe(System.out::println);
    }

    @Test
    public void logAndDebugFluxAndMono() {
        Flux.just(1, 2, 42).log().subscribe(System.out::println);
        /*
          [ INFO] (main) | onSubscribe([Synchronous Fuseable] FluxArray.ArraySubscription)
          [ INFO] (main) | request(unbounded)
          [ INFO] (main) | onNext(1)
          1
          [ INFO] (main) | onNext(2)
          2
          [ INFO] (main) | onNext(42)
          42
          [ INFO] (main) | onComplete()
         */
    }
}
