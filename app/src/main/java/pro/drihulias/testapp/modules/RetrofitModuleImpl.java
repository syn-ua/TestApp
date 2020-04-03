package pro.drihulias.testapp.modules;

import com.google.gson.Gson;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import pro.drihulias.testapp.modules.interfaces.RetrofitModule;
import pro.drihulias.testapp.modules.retrofit.api.UserApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitModuleImpl implements RetrofitModule {
    private Gson gson;
    private OkHttpClient client;

    public RetrofitModuleImpl(Gson gson, OkHttpClient client) {
        this.gson = gson;
        this.client = client;
    }

    @Override
    public Retrofit getRetrofit(String mainUrl) {
        return new Retrofit.Builder()
                .baseUrl(mainUrl)
                .client(client)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Override
    public UserApi getUserApi(Retrofit retrofit) {
        return retrofit.create(UserApi.class);
    }
}
