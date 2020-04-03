package pro.drihulias.testapp.services.user.models;

import java.util.List;

import pro.drihulias.testapp.models.UserBLModel;

public class UserLoadingDTO {
    public List<UserBLModel> users;
    public Boolean hasMore;

    public UserLoadingDTO(List<UserBLModel> users, Boolean hasMore) {

        this.users = users;
        this.hasMore = hasMore;
    }
}
