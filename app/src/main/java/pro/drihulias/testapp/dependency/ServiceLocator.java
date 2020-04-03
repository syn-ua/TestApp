package pro.drihulias.testapp.dependency;

import pro.drihulias.testapp.dependency.interfaces.ModulesManager;
import pro.drihulias.testapp.dependency.interfaces.ServiceManager;

public class ServiceLocator {
    private static ServiceLocator serviceLocator;
    private ModulesManager modulesManager;
    private ServiceManager serviceManager;


    public static ServiceLocator getInstance() {
        if (serviceLocator == null) {
            synchronized (ServiceLocator.class) {
                if (serviceLocator == null) {
                    serviceLocator = new ServiceLocator();
                    serviceLocator.init();
                }
            }
        }
        return serviceLocator;
    }

    private void init() {
        ModulesManagerImpl modulesManager = new ModulesManagerImpl();
        modulesManager.init();
        this.modulesManager = modulesManager;

        ServicesManagerImpl serviceManager = new ServicesManagerImpl();
        serviceManager.init(modulesManager);
        this.serviceManager = serviceManager;
    }


    public ServiceManager getServiceManager() {
        return serviceManager;
    }

    public ModulesManager getModulesManager() {
        return modulesManager;
    }
}
