package com.example.jobscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Random;
import java.util.concurrent.Executors;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
  Button trampoline,newThread,io,computation,from;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trampoline = (Button) findViewById(R.id.trampoline);
        newThread = (Button) findViewById(R.id.newThread);
        computation = (Button) findViewById(R.id.computation);
        io = (Button) findViewById(R.id.io);
        from = (Button) findViewById(R.id.from);



        trampoline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gettrampoline();
            }


        });
        newThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getnewThread();
            }
        });
        computation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getcomputation();
            }
        });
        io.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getio();
            }
        });
        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             getfrom();
            }

        });
    }
    private void getio() {
        Observable.just("A", "AB", "ABC")
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
    }
    private void gettrampoline() {
        Observable.just("A", "AB", "ABC")
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
    }
    private void getnewThread() {
        Observable.just("A", "AB", "ABC")
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
    }
    private void getcomputation() {
        Observable.just("A", "AB", "ABC")
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
    }

    private void getfrom() {
        Observable.just("A", "AB", "ABC")
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
    }
    protected static Observable<Integer> getLengthWithDelay(String v) {
        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(3) * 1000);
            return Observable.just(v.length());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}