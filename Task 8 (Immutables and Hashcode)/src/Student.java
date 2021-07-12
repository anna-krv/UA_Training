public class Student {
    // for generating hashCode
    private static int PRIME = 41;
    private String name;
    private int age;
    private String group;

    public Student(String name, int age, String group) {
        this.name = name;
        this.age = age;
        this.group = group;
    }

    public int hashCode() {
        return (age * PRIME + name.hashCode()) * PRIME + group.hashCode();
    }

    public boolean equals(Object o) {
        if (!(o instanceof Student)) {
            return false;
        }
        if (o.hashCode() != hashCode()) {
            return false;
        }
        Student other = (Student) o;
        return other.age == age && other.name.equals(name) && other.group.equals(group);
    }
}
