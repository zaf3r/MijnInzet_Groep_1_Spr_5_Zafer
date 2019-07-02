package makeitwork.mijninzet.model.CourseSchedule;

public class Temp {
    //    class with the only purpose of easily translating a JSON-file into attributes of User and Role.
    private String userName;
    private int roleId;
    private String email;

    public Temp() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = Integer.parseInt(roleId);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Temp{" +
                "userName='" + userName + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}
