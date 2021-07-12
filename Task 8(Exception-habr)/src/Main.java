import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String... args) {
        main3();
    }

    // EOFException перехватили catch-ом, им не пугаем
    public static void main1() throws FileNotFoundException {
        try {
            if (System.currentTimeMillis() % 2 == 0) {
                throw new EOFException();
            } else {
                throw new FileNotFoundException();
            }
        } catch (EOFException e) {
            // ...
        }
    }

    public static void main2() throws Throwable {
        try {
            Throwable t = new Exception(); // а лететь будет Exception
            throw t;
        } catch (Exception e) { // и мы перехватим Exception
            System.out.println("Перехвачено!");
        }
    }

    public static void main3() {
        try {
            throw new Exception();
        } catch (Throwable e) {
            // ...
        }
    }

    public static void main4() {
        try {
            throw new Exception();
        } catch (Exception e) {
            // ...
        }
    }

    public static void main5() throws IOException, InterruptedException {
        f0();
        f1();
        f2();
    }

    public static void f0() throws EOFException {
    }

    public static void f1() throws FileNotFoundException {
    }

    public static void f2() throws InterruptedException {
    }

}

class Parent {
    // предок пугает IOException и InterruptedException
    public void f() throws IOException, InterruptedException {
    }
}

class Child extends Parent {
    // а потомок пугает только потомком IOException
    @Override
    public void f() throws FileNotFoundException {
    }
}


