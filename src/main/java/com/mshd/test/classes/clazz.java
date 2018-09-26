package com.mshd.test.classes;

/**
 * 学习内部类和匿名内部类
 *
 * 1.成员内部类
 * 2.方法内部类
 * Created by Pangaofeng on 2018/9/25
 */
public class clazz {

    private String member="这是外部类变量";

    class C{
        public void out(){
            //内部类访问外部类变量
            System.out.println("这是内部类B的go方法"+clazz.this.member);
        }
    }


    //成员内部类
    //不对外开放，高内聚
    class B{
        public B() {
            //当内部类的构造器为Protected、private修饰时外部类外不可以访问
        }
        public void go(){
            //内部类访问外部类变量
            System.out.println("这是内部类B的go方法"+clazz.this.member);
        }
    }

    //可供成员的外部类中其他方法调用
    public  B show(){
        return this.new B();//外部类调用
    }

    //　二、方法内部类（局部内部类）
    class Duanzanshichangzheng {
         public  void noProblem() {
               System.out.println("患有急性短暂失常症,开车撞死人没事");
         }
    }

    private String member1 ="全局变量";
    final int n=4;
    public void driver(){
        final String member2 ="局部变量";//方法内的变量只有final变量才能被方法内部类访问
        System.out.println("我正在开车"+member1);

        //每个内部类都能独立地继承自一个（接口的）实现，所以无论外围类是否已经继承了某个（接口的）实现，
        //对于内部类都没有影响
        //内部类使得多重继承的解决方案变得完整。接口解决了部分问题，而内部类有效地实现了“多重继承”
        //短暂，不对外，防止变成全局
        class B extends Duanzanshichangzheng {
            public void show(){
                  System.out.println(member2+4);
            }
        }
        new B().noProblem();//方法内部类里的方法只能在方法里调用
        new B().show();
            System.out.println("一切恢复正常");
        }

    public static void main(String[] args) {

        B b = new clazz().show();
        System.out.println("//以上是成员内部类..................");

        new clazz().driver();
        System.out.println("//以上是方法内部类..................");

        //匿名内部类
        Doctor1 d1 = new Doctor1();
        d1.workInDay();
        d1.workInNight();

        Doctor2 d2 = new Doctor2();
        d2.workInDay();
        d2.workInNight();

        //语法格式
        //1、new 抽象类或者接口
        //2、后加大括号
        //3、实现未实现方法
        Doctor d3 = new Doctor(){

            //匿名内部类
            public void workInNight() {
                   System.out.println("睡觉");
            }

        };

        d3.workInDay();
        d3.workInNight();

    }
}
