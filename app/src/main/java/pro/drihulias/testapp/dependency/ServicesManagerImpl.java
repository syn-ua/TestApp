package pro.drihulias.testapp.dependency;

import pro.drihulias.testapp.BuildConfig;
import pro.drihulias.testapp.dependency.interfaces.ModulesManager;
import pro.drihulias.testapp.dependency.interfaces.ServiceManager;
import pro.drihulias.testapp.modules.interfaces.RetrofitModule;
import pro.drihulias.testapp.services.interfaces.UserService;
import pro.drihulias.testapp.services.user.UserServicesImpl;
import retrofit2.Retrofit;

public class ServicesManagerImpl implements ServiceManager {
    private UserService userService;

    public void init(ModulesManager modulesManager) {
        RetrofitModule retrofitModule = modulesManager.getRetrofitModule();
        Retrofit retrofit = retrofitModule.getRetrofit(BuildConfig.BASE_URL);
        userService = new UserServicesImpl(retrofitModule.getUserApi(retrofit));
    }

    @Override
    public UserService getUserService() {
        return userService;
    }
}
