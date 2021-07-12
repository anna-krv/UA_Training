import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        testStudents();
        testImmutable();
    }

    public static void testStudents(){
        Student s1 = new Student("oleg", 20, "1");
        Student s2 = new Student("oleg", 20, "1");
        Student s3 = new Student("oleg", 20, "2");
        Student s4 = new Student("daryna", 20, "1");

        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);

        for (Student student: students){
            System.out.printf("Hashcode: %s. Equals: ", student.hashCode());
            for (Student other: students){
                System.out.print(student.equals(other) +" ");
            }
            System.out.println();
        }
    }

    public static void testImmutable(){
        Strongbox box;
        List<Double> prices = new ArrayList<>();

        prices.add(120.3);
        prices.add(842.4);
        box = new Strongbox("Angelina", prices);
        prices.add(999.99);

        for (Double price: box.getJewelryPrices()){
            System.out.println(price);
        }
    }
}
