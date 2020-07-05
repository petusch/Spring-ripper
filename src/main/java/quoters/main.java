package main.java.quoters;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class main {
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("main/resources/context.xml");

        while (true) {
            Thread.sleep(200);
            context.getBean(Quoter.class).sayQuote();
        }
    }
}
