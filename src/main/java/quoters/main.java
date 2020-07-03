package main.java.quoters;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("main/resources/context.xml");
        context.getBean(Quoter.class).sayQuote();
    }
}
