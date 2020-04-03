package pro.drihulias.testapp.dependency;

import pro.drihulias.testapp.dependency.interfaces.ModulesManager;
import pro.drihulias.testapp.modules.RetrofitModuleImpl;
import pro.drihulias.testapp.modules.gson.GsonModuleImpl;
import pro.drihulias.testapp.modules.interfaces.GsonModule;
import pro.drihulias.testapp.modules.interfaces.OkHttpModule;
import pro.drihulias.testapp.modules.interfaces.RetrofitModule;
import pro.drihulias.testapp.modules.okhttp.OkHttpModuleImpl;

public class ModulesManagerImpl implements ModulesManager {
    private GsonModule gsonModule;
    private OkHttpModule okHttpModule;
    private RetrofitModule retrofitModule;

    public void init() {
        gsonModule = new GsonModuleImpl();
        okHttpModule = new OkHttpModuleImpl();
        retrofitModule = new RetrofitModuleImpl(gsonModule.getGson(), okHttpModule.getOkHttpClient());
    }

    @Override
    public GsonModule getGsonModules() {
        return gsonModule;
    }

    @Override
    public OkHttpModule getOkHttpModule() {
        return okHttpModule;
    }

    @Override
    public RetrofitModule getRetrofitModule() {
        return retrofitModule;
    }
}
