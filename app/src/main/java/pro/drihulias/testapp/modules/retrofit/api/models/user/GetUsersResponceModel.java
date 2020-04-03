package pro.drihulias.testapp.modules.retrofit.api.models.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetUsersResponceModel {
    @Expose
    @SerializedName("data")
    public DataModel data;

    @Expose
    @SerializedName("message")
    public String message;

    @Expose
    @SerializedName("status")
    public Boolean status;
}
