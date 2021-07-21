import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Counter {
    public static Map<Integer, Integer> count(List<Integer> nums) {
        Map<Integer, Integer> numsCounted = new HashMap<Integer, Integer>();

        for (Integer n : nums) {
            numsCounted.putIfAbsent(n, 0);
            numsCounted.replace(n, numsCounted.get(n) + 1);
        }
        return numsCounted;
    }
}
