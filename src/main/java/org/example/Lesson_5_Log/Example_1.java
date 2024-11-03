package org.example.Lesson_5_Log;

public class Example_1 {
    public static void main(String[] args) {
        execute();
        skipDebug();
        debug();

    }

    private static void execute(){
        // Do something
        int sum = 0;
        for (int i = 0; i < 10; i ++){
            sum += i;
         //   System.out.println(i);
        }

        System.out.println("Do something");
    }

    private static void skipDebug(){
        for (int i = 0; i < 5; i++) {
          doSomething();
        }

    }
    private static void debug(){
        for (int i = 0; i < 5; i++) {
            doSomething();
        }
    }
    private static void doSomething(){
        System.out.println("Do Something");
    }
}

