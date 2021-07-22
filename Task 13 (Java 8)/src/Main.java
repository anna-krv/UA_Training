import javafx.util.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        int[] nums = new int[]{100, 65, 93, 78, 98, 74, 87};

        average(nums);
        minimum(nums);
        countEqual(nums, 0);
        countGreater(nums, 0);
        multiply(nums);
    }

    private static void average(int[] nums) {
        Arrays.stream(nums).average().ifPresent(avg -> System.out.println("Average:\t" + avg));
    }

    private static void minimum(int[] nums) {
        IntStream
                .range(0, nums.length)
                .mapToObj(i -> new Pair<>(nums[i], i))
                .min(Comparator.comparingInt(Pair::getKey))
                .ifPresent(pair -> System.out.println("Min value:\t" + pair.getKey() + "\tat index:\t" + pair.getValue()));
    }

    private static void countEqual(int[] nums, int elem) {
        long count = Arrays
                .stream(nums)
                .filter(n -> n == 0)
                .count();
        System.out.printf("# of elements equal to %d:\t%d\n", elem, count);
    }

    private static void countGreater(int[] nums, int elem) {
        long countGreater = Arrays
                .stream(nums)
                .filter(n -> n > 0)
                .count();
        System.out.printf("# of elements greater than %d:\t%d\n", elem, countGreater);
    }

    private static void multiply(int[] nums) {
        int multiplier = 2;
        Arrays
                .stream(nums)
                .map(n -> n * multiplier)
                .forEach(n -> System.out.print(n + " "));
    }

}
