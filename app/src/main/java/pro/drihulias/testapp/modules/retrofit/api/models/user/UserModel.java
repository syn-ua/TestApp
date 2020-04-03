package pro.drihulias.testapp.modules.retrofit.api.models.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserModel {
    @Expose
    @SerializedName("name")
    public String name;

    @Expose
    @SerializedName("image")
    public String image;

    @Expose
    @SerializedName("items")
    public List<String> items;
}
