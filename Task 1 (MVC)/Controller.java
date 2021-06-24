import java.util.Scanner;

public class Controller {
    private Model model;
    private View view;
    private Scanner sc;
    private final String IGNORED_INPUT = "The input will be ignored.";
    private final String INPUT_MESSAGE = "Your input = ";
    private final String[] EXPECTED_INPUT = new String[]{"Hello", "world!"};

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        sc = new Scanner(System.in);
    }

    /**
     * Read one string from scanner.
     *
     * @return String
     */
    public String getInputString() {
        String s = null;
        view.print(INPUT_MESSAGE);
        if (sc.hasNext()) {
            s = sc.next();
        }
        return s;
    }

    /**
     * Check input String.
     *
     * @param input string to check
     * @param i     number of previous successful inputs
     * @return true if input is as expected
     */
    public boolean check(String input, int i) {
        return input.equals(EXPECTED_INPUT[i]);
    }

    /**
     * Read input and add it to model. If input is not valid print a message.
     */
    public void getInput() {
        for (int i = 0; i < EXPECTED_INPUT.length; i++) {
            String input = getInputString();
            while (!check(input, i)) {
                view.print(IGNORED_INPUT);
                input = getInputString();
            }
            model.addInput(input);
        }
    }

    /**
     * Get input, store it in model. Then get output from model and print it.
     */
    public void processUser() {
        getInput();
        String out = model.getOutput();
        view.print(out);
    }
}
