package com.webber.rxjavademos;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;

import static rx.Observable.from;

public class MainActivity extends AppCompatActivity {

    private TextView mDemoTv;
    private ImageView mDemoIv;
    private EditText mDemoEt;
    private String mDemoStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDemoTv = (TextView) findViewById(R.id.demo_tv);
        mDemoIv = (ImageView) findViewById(R.id.demo_iv);
        mDemoEt = (EditText) findViewById(R.id.demo_et);
        View.OnClickListener observer = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("webber", "点击了！");
            }
        };
        mDemoEt.setOnClickListener(observer);

        //demo1();
        //demo2();
        //demo3();
        //demo4();
        //demo5();
        //demo6();
        //demo7();
        //demo8();
        //demo9();
        //demo10();
        //学习了扔物线 的RxJava 大致来总结一下
        //RxJava 是一种支持异步操作的库，并且支持链式调用，内部的实现是基于观察者模式
        //RxJava 最大的亮点在于他能使程序逻辑更明了，并且提高了扩展性
        //简单使用
        // Observable 与 Observer 通过subscribe 联系起来 Observable.subscribe(Observer)
        // Observable.create()\Observable.from()\Observable.just()用于创建一个Observable 对象
        // 通常在使用的时候有库中提供的Fun&Action 来做具体操作
        // 对于线程的处理，subscribeOn() 指定事件产生的线程，例如做一些耗时操作，多次设置时已第一次设置的线程为准;
        // observableOn() 指定事件消费的线程，例如要在UI线程显示，多次设置时已最后一次的线程为准
        // 通常使用的线程有：io()指 I\O线程，用于一些读写操作，例如查找数据库，网络请求,内部是无上限的线程池；
        // computation() 计算线程，用于处理一些CPU密集计算，内部是核数的线程池；
        // newThread() 新起一个线程；immediate() 当前线程，也是默认值；AndroidSchedulers.mainThread()，Android主线程即UI线程；
        // 变换 （重难点）**
        // 简单理解就是传入对象A输出对象B
        // map() 一对一的变换，输入一个对象A，输出一个对象B
        // flatMap() 一对多的变换，输入一个对象A，输出多个对象B
        //原理分析
        // 变换原理 （没搞明白，个人理解就是在Observable操作时生成一个新的Observable,并传递给subscribe）
        //练手示例 利用RxJava 实现三级联动

        //进阶示例 -操作符
        demo11();
    }

    //操作符示例
    private void demo11() {
        // 创建操作符
        //demo11_1();
        // 变换操作符
        demo11_2();
        // 过滤操作符
        //demo11_3();
        // 组合操作符
        //demo11_4();

        // 错误处理
        // 辅助操作
        // 条件和布尔操作
        // 算数\聚合操作
        // 异步操作
        // 连接操作
    }

    private void demo11_4() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        List<String> list2 = new ArrayList<>();
        list2.add("a");
        list2.add("b");
        list2.add("c");
        list2.add("d");

        //一秒执行一次切三秒获取一次发送的事件  防抖动
        Observable.interval(1, TimeUnit.SECONDS)
                .throttleFirst(3, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.d("demo11_4", getCurrentThreadName() + " " + aLong);
                    }
                });

        /*// 需求 计时 无法递减，无法在链中控制停止
        Observable.interval(1000, 1000, TimeUnit.MILLISECONDS)
                .delay(10, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.d("demo11_4", getCurrentThreadName() + " " + aLong);
                    }
                }).unsubscribe();*/

       /* //Timer 发生在computation 线程中U操作需要切换到主线程
        Observable.timer(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.d("demo11_4", getCurrentThreadName());
                        mDemoIv.setBackgroundResource(R.mipmap.ic_launcher);
                    }
                });
*/

     /*   Observable.from(list).delay(3, TimeUnit.SECONDS)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d("demo11_4", s);
                    }
                });*/


        /*Observable.zip(Observable.from(list), Observable.from(list2), new Func2<String, String, String>() {
            @Override
            public String call(String s, String s2) {
                return s + s2;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("demo11_4", s);
            }
        });*/

      /*  Observable.merge(Observable.from(list2), Observable.from(list))
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d("demo11_4", s);
                    }
                });*/

    }

    //组合连接符
    private void demo11_3() {
        //demo11_3_1();
        //demo11_3_2();
        demo11_3_3();
    }

    // 一段时间没有变化则发送事件
    // TODO 例如百度搜索 一段时间没有输入采取查找关键字
    private void demo11_3_3() {
        RxTextView.textChanges(mDemoEt)
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence charSequence) {
                        return charSequence.toString().trim().length() > 0;
                    }
                }).subscribe(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                Log.d("demo11_3", charSequence.toString());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });

    }

    //过滤重复数据
    private void demo11_3_2() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("3");
        list.add("1");
        list.add("4");
        list.add("1");

        Observable.from(list)
                .distinct()
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d("demo11_4", s);
                    }
                });
    }

    long startTime;
    long time;

    // GroupBy 根据条件分组
    // 需求：有一些学生，按照他们的班级分类
    private void demo11_3_1() {
        List<StudentBean> students = getStudent();
        formatUnity(students);
        Log.d("demo11_3", "-------------格式化前分割线--------------");
        //demo11_3_1_1(students);
        demo11_3_1_2(students);
    }

    //是出学生和班级
    private void formatUnity(List<StudentBean> students) {
        from(students)
                .subscribe(new Action1<StudentBean>() {
                    @Override
                    public void call(StudentBean studentBean) {
                        Log.d("demo11_3", String.format("姓名：%s->>班级:%s", studentBean.getName(), studentBean.getUnity()));
                    }
                });
    }

    //利用RxJava中的GroupBy操作符
    private void demo11_3_1_2(final List<StudentBean> students) {
        Observable<GroupedObservable<String, StudentBean>> groupedObservableObservable = Observable.from(students)
                .groupBy(new Func1<StudentBean, String>() {
                    @Override
                    public String call(StudentBean studentBean) {
                        return studentBean.getUnity();
                    }
                });

        Observable.concat(groupedObservableObservable).subscribe(new Action1<StudentBean>() {
            @Override
            public void call(StudentBean studentBean) {
                Log.d("demo11_3", formatStudent(studentBean));
            }
        });
    }

    private String formatStudent(StudentBean studentBean) {
        return String.format("姓名：%s->>班级：%s", studentBean.getName(), studentBean.getUnity());
    }

    // 有一些学生，按照他们班级分组
    // 常规思路 利用map()遍历一遍学生 班级相同的放在一个map中，一个Map即一个班级
    private void demo11_3_1_1(List<StudentBean> students) {
        startTime = System.currentTimeMillis();
        Map<String, List<StudentBean>> studentMap = new HashMap<>();
        for (StudentBean bean : students) {
            if (studentMap.containsKey(bean.getUnity())) {
                studentMap.get(bean.getUnity()).add(bean);
            } else {
                List<StudentBean> unitys = new ArrayList<>();
                unitys.add(bean);
                studentMap.put(bean.getUnity(), unitys);
            }
        }
        studentMap = sortMap(studentMap);
        for (Map.Entry<String, List<StudentBean>> entry : studentMap.entrySet()) {
            Log.d("demo11_3", "key= " + entry.getKey());
            formatUnity(entry.getValue());
        }
        startTime = System.currentTimeMillis();
    }

    private Map<String, List<StudentBean>> sortMap(Map<String, List<StudentBean>> studentMap) {
        Map<String, List<StudentBean>> sortMap = new TreeMap<String, List<StudentBean>>(new StudentComparator());
        sortMap.putAll(studentMap);
        return sortMap;
    }

    //过滤连接符
    private void demo11_2() {
        //demo11_2_1();
        //FlatMap
        //demo11_2_2();
        //map
        demo11_2_3();
    }

    //打印每一名学生任意一门课程成绩
    private void demo11_2_3() {
        Observable.from(getStudent())
                .map(new Func1<StudentBean, Cause>() {
                    @Override
                    public Cause call(StudentBean studentBean) {
                        return studentBean.getCauseList().get(generateRandom(0, studentBean.getCauseList().size()));
                    }
                }).subscribe(new Action1<Cause>() {
            @Override
            public void call(Cause cause) {
                print("demo11_2_3", formatCause(cause));
            }
        });
    }

    //生成随机数
    private int generateRandom(int min, int max) {
        int rand = (int) (min + Math.random() * max);
        print("generateRandom", rand + "");
        return rand;
    }

    //打印每一名学生的每一门课程成绩
    private void demo11_2_2() {
        Observable.from(getStudent())
                .flatMap(new Func1<StudentBean, Observable<Cause>>() {
                    @Override
                    public Observable<Cause> call(StudentBean studentBean) {
                        print("demo11_2_2", studentBean.getName());
                        return Observable.from(studentBean.getCauseList());
                    }
                }).subscribe(new Action1<Cause>() {
            @Override
            public void call(Cause cause) {
                print("demo11_2_2", formatCause(cause));
            }
        });
    }

    private void demo11_2_1() {
        //TODO flatMap 无序输出 concatMap 有序输出
        //转换连接符 map flatMap concatMap
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.newThread())
                .flatMap(new Func1<Integer, Observable<String>>() {
                    @Override
                    public Observable<String> call(Integer integer) {
                        return Observable.just(integer + "");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d("demo11_1", s);
                    }
                });
    }

    //TimeUnit
    //MICROSECONDS 微秒
    //MILLISECONDS 毫秒
    //NANOSECONDS 纳秒
    private void demo11_1_1_4() {
        Observable.timer(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        print("demo11_1_1_4", aLong);
                    }
                });
    }

    /**
     * 创建操作符demo
     */
    private void demo11_1() {
        //Create
        //demo11_1_1();
        //From
        //demo11_1_2();
        //Just
        //demo11_1_3();
        //Timer
        //demo11_1_1_4();
        //Interval
        demo11_1_1_5();

    }

    private void demo11_1_1_5() {
        Observable.interval(1, TimeUnit.SECONDS).
                filter(new Func1<Long, Boolean>() {
                    @Override
                    public Boolean call(Long aLong) {
                        return aLong < 10;
                    }
                })
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        print("demo11_1_1_5", aLong);
                    }
                });
    }


    private void demo11_1_3() {
        Observable.just(2, 4, 6, 23, 234, 1)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        print("demo11_1_3", integer);
                    }
                });
    }

    private void print(String tag, Object obj) {
        Log.d(tag, obj + "");
    }

    private void demo11_1_2() {
        Integer[] ints = {5, 4, 3, 8, 1};
        Observable.from(ints)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d("demo11_1_2", integer + "");
                    }
                });
    }

    private void demo11_1_1() {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                subscriber.onNext("消息");
            }
        }).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                Log.d("demo11_1_1", (String) o);
            }
        });
    }

    //执行前的线程操作 doOnScheduler
    //例如用于网络请求开始前 显示进度条
    private void demo10() {
        Observable.just(1, 0, 2, 4)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        Log.d("demo10", "doOnSub:" + getCurrentThreadName());
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.computation())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d("demo10", "sub" + getCurrentThreadName());
                    }
                });
    }

    private String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }

    //变换中的多次线程切换 以map flatMap为例
    //需求：输出学生的多门成绩
    private void demo9() {
        final List<StudentBean> students = getStudent();
        from(students)
                .subscribeOn(Schedulers.immediate())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<StudentBean, Observable<Cause>>() {
                    @Override
                    public Observable<Cause> call(StudentBean studentBean) {
                        Log.d("demo9", "姓名:" + studentBean.getName() + "Thread:" + Thread.currentThread().getName());
                        return from(studentBean.getCauseList());
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Cause>() {
                    @Override
                    public void call(Cause cause) {
                        Log.d("demo9", formatCause(cause) + "Thread:" + Thread.currentThread().getName());
                    }
                });
    }

    //变换原理 lift
    //需求：将Integer 转为String
    private void demo8() {
        Observable observable1 = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

            }
        });
        observable1.lift(new Observable.Operator<String, Integer>() {
            @Override
            public Subscriber<? super Integer> call(Subscriber<? super String> subscriber) {
                return null;
            }
        });
    }

    //变换队列 flatMap
    //有这样一个需求：有若干学生，现在需要输出这些学生所有的成绩
    private void demo7() {
        List<StudentBean> students = getStudent();
        demo7_1(students);
        //demo7_2(students);
        //demo7_3(students);
        //demo7_4(students);
        demo7_5(students);
    }

    private void demo7_5(List<StudentBean> students) {
        from(students)//遍历学生数组
                .subscribeOn(Schedulers.io())//在IO线程中运算
                .observeOn(AndroidSchedulers.mainThread())//在主线程中回调
                .filter(new Func1<StudentBean, Boolean>() {
                    @Override
                    public Boolean call(StudentBean studentBean) {
                        return studentBean.isMale();//筛选出性别为男的数据
                    }
                })
                .map(new Func1<StudentBean, List<Cause>>() {
                    @Override
                    public List<Cause> call(StudentBean studentBean) {
                        Log.d("demo7_5", "姓名：" + studentBean.getName() + "性别：" + (studentBean.isMale() ? "男" : "女"));
                        return studentBean.getCauseList();
                    }
                })
                .subscribe(new Action1<List<Cause>>() {
                    @Override
                    public void call(List<Cause> causes) {
                        for (Cause cause : causes) {
                            Log.d("demo7_5", formatCause(cause));
                        }
                    }
                });
    }

    //写到这里感觉到了 from 和for循环一样，把list里面的对象挨个输出
    //这个例子传入的是Students 输出的是每一个课程信息
    private void demo7_4(List<StudentBean> students) {
        from(students).flatMap(new Func1<StudentBean, Observable<Cause>>() {
            @Override
            public Observable<Cause> call(StudentBean studentBean) {
                Log.d("demo7_4", "姓名：" + studentBean.getName());
                return from(studentBean.getCauseList());
            }
        }).subscribe(new Action1<Cause>() {
            @Override
            public void call(Cause cause) {
                Log.d("demo7_4", formatCause(cause));
            }
        });
    }

    private void demo7_3(List<StudentBean> students) {
        from(students).map(new Func1<StudentBean, List<Cause>>() {
            @Override
            public List<Cause> call(StudentBean studentBean) {
                Log.d("demo7_3", "姓名：" + studentBean.getName());
                return studentBean.getCauseList();
            }
        }).subscribe(new Action1<List<Cause>>() {
            @Override
            public void call(List<Cause> causes) {
                for (Cause cause : causes) {
                    Log.d("demo7_3", formatCause(cause));
                }
            }
        });
    }

    //RxJava
    private void demo7_2(List<StudentBean> students) {
        from(students).subscribe(new Action1<StudentBean>() {
            @Override
            public void call(StudentBean studentBean) {
                Log.d("demo7_2", "姓名：" + studentBean.getName());
                for (Cause cause : studentBean.getCauseList()) {
                    Log.d("demo7_2", formatCause(cause));
                }
            }
        });
    }

    //for 循环
    private void demo7_1(List<StudentBean> students) {
        for (StudentBean studentBean : students) {
            Log.d("demo7_1", "姓名：" + studentBean.getName());
            for (Cause cause : studentBean.getCauseList()) {
                Log.d("demo7_1", formatCause(cause));
            }
        }
    }

    private String formatCause(Cause cause) {
        return String.format("caseName:%s->>causeGrade:%s", cause.getName(), cause.getGrade());
    }

    //float与double 的区别 单精度4字节，双精度8字节
    private List<StudentBean> getStudent() {
        List<StudentBean> students = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            StudentBean student = new StudentBean();
            student.setName("学生" + i);
            student.setUnity(random.nextInt(5) + 1 + "班");
            List<Cause> causes = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Cause cause = new Cause(getCauseName(j), (float) (Math.random() * 100));
                causes.add(cause);
            }
            student.setCauseList(causes);
            students.add(student);
        }
        return students;
    }

    private String getCauseName(int j) {
        switch (j) {
            case 0:
                return "语文";
            case 1:
                return "数学";
            case 2:
                return "外语";
            default:
                return "Android开发入门";
        }
    }

    //变换例子 map
    //传入的对象转换为另一个对象 其实就是在map 的Fun 方法中做了操作
    //这只是简单的对象变换，RxJava还支持整个事件队列的变换
    private void demo6() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("file:///android_asset/01.jpg");
            }
        }).map(new Func1<String, Bitmap>() {
            @Override
            public Bitmap call(String s) {
                Log.d("demo5", getAssets().getLocales()[0]);
                //TODO 打开filePath无法获得Bitmap的问题
                //return BitmapFactory.decodeFile(s);
                try {
                    return BitmapFactory.decodeStream(getAssets().open(s));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }).subscribe(new Action1<Bitmap>() {
            @Override
            public void call(Bitmap bitmap) {
                mDemoIv.setImageBitmap(bitmap);
            }
        });

    }

    //线程卡顿例子
    private void demo5() {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                try {
                    Log.d("demo5", Thread.currentThread().getName());
                    Thread.sleep(6000);
                    subscriber.onNext("我运行结束了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        mDemoTv.setText((String) o);
                        Log.d("demo5", (String) o);
                    }
                });
    }

    //线程切换例子
    private void demo4() {
        //just 会将传入的参数依次发送
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                Log.d("demo4", Thread.currentThread().getId() + "");
                subscriber.onNext(1);
                subscriber.onNext(2);
                subscriber.onCompleted();
            }
        })
                //事件产生的线程
                //Schedulers.io() IO线程 数据库读写，文件读写，网络请求，内部是一个无上限的线程池
                //Schedulers.immediate() 当前线程，默认的线程
                //Schedulers.computation() 用于CPU密集计算的线程 图形的计算 内部是一个CPU核数的线程池
                //Schedulers.newThread() 新开一个线程
                .subscribeOn(Schedulers.io())
                //事件消费的线程
                //AndroidSchedulers.mainThread() Android主线程 用于UI更新
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d("demo4", Thread.currentThread().getName());
                        Log.d("demo4", integer + "");
                    }
                });
    }

    //例子
    private void demo3() {
        final int imgRes = R.mipmap.ic_launcher;
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                subscriber.onNext(getTheme().getDrawable(imgRes));
                Log.d("demo3", "Observable:" + Thread.currentThread().getName());
            }
        }).subscribe(new Action1<Drawable>() {
            @Override
            public void call(Drawable drawable) {
                mDemoIv.setBackground(drawable);
                Log.d("demo3", "Subscribe:" + Thread.currentThread().getName());
            }
        });
    }

    //例子
    private void demo2() {
        String[] names = {"name1", "name2", "name3"};
        //from 表示将传入的集合 拆分成对象后依次输出
        from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("webber", "demo2:" + s);
            }
        });
    }

    //简单的创建
    private void demo1() {
        //创建观察者
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onCompleted() {
                Log.d("webber", "onCompleted:");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("webber", "onError:" + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.d("webber", "onNext:" + s);
            }
        };


        Subscriber<String> stringSubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d("network", "onCompleted" + Thread.currentThread().getName());

            }

            @Override
            public void onError(Throwable e) {
                Log.d("network", "onError" + Thread.currentThread().getName());

            }

            @Override
            public void onNext(String s) {
                Log.d("network", "onNext" + Thread.currentThread().getName());

            }

            @Override
            public void onStart() {
                super.onStart();
                Log.d("network", "onStart" + Thread.currentThread().getName());
            }
        };
        //创建被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //subscriber.onStart();
                Log.d("network", "call" + Thread.currentThread().getName());
                subscriber.onNext("item1");
                subscriber.onNext("item2");
                subscriber.onNext("item3");
                subscriber.onCompleted();
                //subscriber.onError(new Throwable("我出错了！！！"));
            }
        });//.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        //订阅二者关系
        observable.subscribe(stringSubscriber);
    }
}
