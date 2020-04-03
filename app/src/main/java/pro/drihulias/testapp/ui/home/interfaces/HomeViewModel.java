package pro.drihulias.testapp.ui.home.interfaces;

import androidx.lifecycle.LiveData;

import java.util.List;

import pro.drihulias.testapp.models.UserBLModel;
import pro.drihulias.testapp.ui.base.interfaces.BaseViewModel;

public interface HomeViewModel extends BaseViewModel {
    void onFullRefreshClick();

    void onNextPageRequest();

    LiveData<List<UserBLModel>> getRefreshUsersLiveData();

    LiveData<List<UserBLModel>> getAddedUsersLiveData();

    LiveData<Boolean> getHasMoreLiveData();

    LiveData<String> getErrorsMessageLiveData();
}
