package models;


import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateUserResponse {

    @JsonProperty("data")
    private UserData data;

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    public static class UserData {
        @JsonProperty("id")
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}

