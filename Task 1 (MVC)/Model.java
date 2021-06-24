public class Model {
    private String[] inputArr;
    private final int N = 2;
    private int size;

    public Model() {
        inputArr = new String[N];
        size = 0;
    }

    /**
     * Add String to array with strings inputed by user.
     *
     * @param input
     */
    public void addInput(String input) {
        if (size < inputArr.length) {
            inputArr[size++] = input;
        }
    }

    /**
     * Get output as combination of input.
     *
     * @return input strings joined with whitespace.
     */
    public String getOutput() {
        return String.join(" ", inputArr);
    }
}
