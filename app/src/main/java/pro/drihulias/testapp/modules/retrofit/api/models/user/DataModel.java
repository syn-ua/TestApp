package pro.drihulias.testapp.modules.retrofit.api.models.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataModel {

    @Expose
    @SerializedName("users")
    public List<UserModel> users;

    @Expose
    @SerializedName("has_more")
    public Boolean hasMore;
}
