import java.util.*;

public class Main {

    public static void main(String[] args) {
        demoCounter();
        demoList();
    }

    private static void demoCounter() {
        List<Integer> nums = Arrays.asList(2, 4, 5, 4, 4, 2, 3, 5);
        Map<Integer, Integer> numsCounted = Counter.count(nums);

        System.out.println("Count occurrence of numbers in a list.");
        for (Map.Entry<Integer, Integer> entry : numsCounted.entrySet()) {
            System.out.printf("number: %d\t--\t %d times\n", entry.getKey(), entry.getValue());
        }
        System.out.println("********************");
    }

    private static void demoList() {
        AdvancedList<Integer> nums = new AdvancedList<>();
        Random rand = new Random();
        int n = 40;

        System.out.println("Collection implementation.");
        for (int i = 0; i < n; i++) {
            nums.add(rand.nextInt(100));
        }
        System.out.println("Size:\t" + nums.size());
        for (int i = 0; i < nums.size(); i++) {
            System.out.print(nums.get(i) + " ");
        }
        try {
            nums.remove(5);
        } catch (UnsupportedOperationException ex) {
            System.out.println("\nThis collection doesn't support remove operation.");
        }
        try {
            nums.clear();
        } catch (UnsupportedOperationException ex) {
            System.out.println("\nThis collection doesn't support clear operation.");
        }
        System.out.println("********************");
    }
}
