
package bookstore.java.cmp;
import bookstore.java.cmp.Access;

public class User {

    private String name;
    private String birthday;
    private String phoneNo;
    private String email;
    private String password;
    private int salary;
    private Access accessLevel;
    private String status;

    // ************ Constructor/Initializer ************ //

    public User(String[] data) {

        this.name = data[0];
        this.birthday = data[1];
        this.phoneNo = data[2];
        this.email = data[3];
        this.password = data[4];
        this.salary = Integer.parseInt(data[5]);

        switch (data[6]) {
            case "admin":
                accessLevel = Access.ADMINISTRATOR;
                break;
            case "manager":
                accessLevel = Access.MANAGER;
                break;
            case "librarian":
                accessLevel = Access.LIBRARIAN;
                break;
            default:
                accessLevel = null;
                break;
        }

        this.status = data[7];
    }

    // *********************** END *********************** //

    @Override
    public String toString() {
        return "Name: " + name + ", Salary: " + salary + ", Access Level: " + accessLevel;
    }

    /**
     * Returns all data for saving it in the CSV file
     *
     * @return data
     */
    public String getAllData() {
        String data = name + "," + birthday + "," + phoneNo + "," + email + "," + password + ",";
        data += salary + "," + accessLevel.toString() + "," + status;
        return data;
    }

    // ****************** Getter/Setter ****************** //

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Access getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(Access accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // *********************** END *********************** //

}
