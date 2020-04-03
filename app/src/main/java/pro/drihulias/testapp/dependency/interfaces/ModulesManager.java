package pro.drihulias.testapp.dependency.interfaces;

import pro.drihulias.testapp.modules.interfaces.GsonModule;
import pro.drihulias.testapp.modules.interfaces.OkHttpModule;
import pro.drihulias.testapp.modules.interfaces.RetrofitModule;

public interface ModulesManager {
    GsonModule getGsonModules();

    OkHttpModule getOkHttpModule();

    RetrofitModule getRetrofitModule();
}
