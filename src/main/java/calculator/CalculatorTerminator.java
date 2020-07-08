package calculator;



public class CalculatorTerminator implements Calculator {

    private int firstNumber;
    private int secondNumber;


    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }

    @Override
    @Profiling
    public int addition() {
        int result = firstNumber+secondNumber;
        System.out.println("addition result = " + result);
        return result;
    }

    @Override
    public int subtraction() {
        int result;
        if(secondNumber>firstNumber) {
            result = secondNumber - firstNumber;
        }
        else {
            result = firstNumber - secondNumber;
        }
        System.out.println("subtraction result = " + result);
        return result;
    }

    @Override
    @Profiling
    public int multiplication() {
        int result = firstNumber*secondNumber;
        System.out.println("multiplication result = " + result);
        return result;
    }
}
