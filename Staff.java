
public class Staff extends User {
    // private String ID;
    private String name;
    private String role;
    private String gender;
    private int age;

    public Staff(String name, String role, String gender, int age, String UserId, String password, UserType userType) {
        super(UserId, password, userType);
        this.name = name;
        this.role = role;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Staff ID: " + super.getUserId() + ", Name: "
                + name + ", Gender: " + gender
                + ", Role: "
                + role + ", Age: " + age;
    }

}
