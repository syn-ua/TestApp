package pro.drihulias.testapp.modules.retrofit.api;

import io.reactivex.rxjava3.core.Single;
import pro.drihulias.testapp.modules.retrofit.api.models.user.GetUsersResponceModel;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserApi {
    @GET("/api/users")
    Single<GetUsersResponceModel> getUsers(@Query("offset") int offset, @Query("limit") int limit);
}
