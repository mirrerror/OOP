import java.util.UUID;

public class Person {

    private UUID uuid;
    private int age;
    private String fullName;

    public Person(int age, String fullName) {
        this.uuid = UUID.randomUUID();
        this.age = age;
        this.fullName = fullName;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
