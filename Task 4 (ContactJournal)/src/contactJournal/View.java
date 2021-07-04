package contactJournal;

public class View {
    private final String ASK_FOR_INPUT="Please, input contact's ";
    private final String FORMAT_ERROR="Unfortunately, wrong format for: ";
    public void reportErrorIn(String whatWasAsked){
        print(FORMAT_ERROR+whatWasAsked);

    }
    public void askFor(String whatToGet){
        print(ASK_FOR_INPUT+whatToGet);
    }
    public void print(String whatToPrint){
        System.out.println(whatToPrint);
    }
}
