package calculator;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        context.getBean(Calculator.class).addition();
        context.getBean(Calculator.class).subtraction();
        context.getBean(Calculator.class).multiplication();
    }
}
