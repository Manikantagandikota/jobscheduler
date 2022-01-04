# jobscheduler
Scheduler
Schedulers are used in multi-threading environment to work with Observable operators.
As per the reactive, Scheduler are used to schedule how chain of operators will apply to different threads.
	Schedulers.Computation()
            Creates and returns a Scheduler intended for computational work. Count of threads             to be scheduled depends upon the CPUs present in the system. One thread is allowed per CPU. Best for event-loops or callback operations

1.	Observable.just("A", "AB", "ABC")
        .flatMap(v -> getLengthWithDelay(v)
                .doOnNext(s -> System.out.println("Processing Thread "
                        + Thread.currentThread().getName()))
                .subscribeOn(Schedulers.computation()))
        .subscribe(length -> System.out.println("Receiver Thread "
                + Thread.currentThread().getName()
                + ", Item length " + length));

try {
    Thread.sleep(10000);
} catch (InterruptedException e) {
    e.printStackTrace();
}

	Schedulers.IO()
Creates and returns a Scheduler intended for IO-bound work. Thread pool may extend as needed.

2.	Observable.just("A", "AB", "ABC")
        .flatMap(v -> getLengthWithDelay(v)
                .doOnNext(s -> System.out.println("Processing Thread "
                        + Thread.currentThread().getName()))
                .subscribeOn(Schedulers.io()))
        .subscribe(length -> System.out.println("Receiver Thread "
                + Thread.currentThread().getName()
                + ", Item length " + length));

try {
    Thread.sleep(10000);
} catch (InterruptedException e) {
    e.printStackTrace();
}
	Schedulers.newThread()

                        Creates and returns a Scheduler that creates a new Thread for each unit of                                                       work.

3.	Observable.just("A", "AB", "ABC")
        .flatMap(v -> getLengthWithDelay(v)
                .doOnNext(s -> System.out.println("Processing Thread "
                        + Thread.currentThread().getName()))
                .subscribeOn(Schedulers.newThread()))
        .subscribe(length -> System.out.println("Receiver Thread "
                + Thread.currentThread().getName()
                + ", Item length " + length));

try {
    Thread.sleep(10000);
} catch (InterruptedException e) {
    e.printStackTrace();
}

	Schedulers.trampoline ()

Creates and returns a Scheduler that queues work on the current thread to be executed after the current work completes


4.	Observable.just("A", "AB", "ABC")
        .flatMap(v -> getLengthWithDelay(v)
                .doOnNext(s -> System.out.println("Processing Thread "
                        + Thread.currentThread().getName()))
                .subscribeOn(Schedulers.trampoline()))
        .subscribe(length -> System.out.println("Receiver Thread "
                + Thread.currentThread().getName()
                + ", Item length " + length));

try {
    Thread.sleep(10000);
} catch (InterruptedException e) {
    e.printStackTrace();
}







	Schedulers.From ()
                   Converts an Executor into a new Scheduler instance.
5.	Observable.just("A", "AB", "ABC")
        .flatMap(v -> getLengthWithDelay(v)
                .doOnNext(s -> System.out.println("Processing Thread "
                        + Thread.currentThread().getName()))
                .subscribeOn(Schedulers.from(Executors.newFixedThreadPool(3))))
        .subscribe(length -> System.out.println("Receiver Thread "
                + Thread.currentThread().getName()
                + ", Item length " + length));

try {
    Thread.sleep(10000);
} catch (InterruptedException e) {
    e.printStackTrace();
}
















