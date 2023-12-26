package lessons;

import java.util.stream.LongStream;

public class Main {

    public static long factorial(int number) {
        return LongStream.rangeClosed(1, number)
                .reduce(1, (long x, long y) -> x * y);
    }

    public static void main(String[] args) {
        System.out.println(factorial(5));
    }

}
