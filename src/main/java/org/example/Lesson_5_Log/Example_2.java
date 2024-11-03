package org.example.Lesson_5_Log;

public class Example_2 {
    public static void main(String[] args) {
        final MyLogger myLogger = new MyLogger(LogLevel.DEBUG);

        myLogger.debug("My message - debug");
        myLogger.error("My message - error");
    }
}

class MyLogger{
    private final LogLevel logLevel;

    MyLogger(LogLevel logLevel){
        this.logLevel = logLevel;
    }

    public void debug (String message){
        System.out.println("[DEBUG]" + message);
    }
    public void error(String message){
        if ((LogLevel.DEBUG == logLevel)){
            System.out.println("[ERROR]" + message);
        }
    }
}
enum LogLevel{
    DEBUG,
    ERROR
}