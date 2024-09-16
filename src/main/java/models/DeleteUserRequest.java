package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DeleteUserRequest {

    @JsonProperty("ids")
    private List<Integer> ids;

    public DeleteUserRequest() {}

    public DeleteUserRequest(List<Integer> ids) {
        this.ids = ids;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
