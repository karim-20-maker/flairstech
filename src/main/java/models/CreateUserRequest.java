package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateUserRequest {

    private String username;
    private String password;
    private boolean status;

    @JsonProperty("userRoleId")
    private int userRoleId;

    @JsonProperty("empNumber")
    private int empNumber;

    public CreateUserRequest(String username, String password, boolean status, int userRoleId, int empNumber) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.userRoleId = userRoleId;
        this.empNumber = empNumber;
    }

    // Getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public int getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(int empNumber) {
        this.empNumber = empNumber;
    }
}
