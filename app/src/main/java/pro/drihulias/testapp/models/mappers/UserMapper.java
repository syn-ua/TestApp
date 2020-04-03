package pro.drihulias.testapp.models.mappers;

import pro.drihulias.testapp.models.UserBLModel;
import pro.drihulias.testapp.modules.retrofit.api.models.user.UserModel;

public class UserMapper {
    public static UserBLModel mapFromNetModelToBlModel(UserModel userModel) {
        return new UserBLModel(userModel.image,userModel.name,userModel.items);
    }
}
