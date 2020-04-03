package pro.drihulias.testapp.modules.interfaces;

import pro.drihulias.testapp.modules.retrofit.api.UserApi;
import retrofit2.Retrofit;

public interface RetrofitModule {
    Retrofit getRetrofit(String mainUrl);

    UserApi getUserApi(Retrofit retrofit);
}
