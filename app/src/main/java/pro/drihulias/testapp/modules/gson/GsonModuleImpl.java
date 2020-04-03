package pro.drihulias.testapp.modules.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pro.drihulias.testapp.modules.interfaces.GsonModule;

public class GsonModuleImpl implements GsonModule {
    @Override
    public Gson getGson() {
        return new GsonBuilder().create();
    }
}
