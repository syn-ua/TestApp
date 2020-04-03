package pro.drihulias.testapp.ui.home.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import pro.drihulias.testapp.dependency.ServiceLocator;
import pro.drihulias.testapp.models.UserBLModel;
import pro.drihulias.testapp.services.interfaces.UserService;
import pro.drihulias.testapp.ui.base.BaseViewModelImpl;
import pro.drihulias.testapp.ui.home.interfaces.HomeViewModel;

public class HomeViewModelImpl extends BaseViewModelImpl implements HomeViewModel {
    private UserService userService;
    private int currentOffset = 0;
    private MutableLiveData<List<UserBLModel>> refreshUsers = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<List<UserBLModel>> addedUsers = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<Boolean> hasMore = new MutableLiveData<>(true);
    private MutableLiveData<String> errorsMessage = new MutableLiveData<>(null);

    private boolean isLoaging = false;


    public HomeViewModelImpl() {
        userService = ServiceLocator.getInstance().getServiceManager().getUserService();
        refreshUsers();
    }

    private void refreshUsers() {
        isLoaging = true;
        userService.getFirstPageUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userLoadingDTO -> {
                    refreshUsers.setValue(userLoadingDTO.users);
                    currentOffset = userLoadingDTO.users.size();
                    hasMore.setValue(userLoadingDTO.hasMore == null ? false : userLoadingDTO.hasMore);
                    isLoaging = false;
                }, this::showError);
    }

    private void showError(Throwable throwable) {
        isLoaging = false;
        errorsMessage.setValue(throwable != null ? throwable.getMessage() : "Error is null");

    }

    @Override
    public void onFullRefreshClick() {
        refreshUsers();
    }

    @Override
    public void onNextPageRequest() {
        if (hasMore.getValue() && !isLoaging) {
            isLoaging = true;
            userService.getPageWithOffSet(currentOffset)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(userLoadingDTO -> {
                        currentOffset += userLoadingDTO.users.size();
                        addedUsers.setValue(userLoadingDTO.users);
                        hasMore.setValue(userLoadingDTO.hasMore);
                        isLoaging = false;
                    }, this::showError);
        }
    }

    @Override
    public LiveData<List<UserBLModel>> getRefreshUsersLiveData() {
        return refreshUsers;
    }

    @Override
    public LiveData<List<UserBLModel>> getAddedUsersLiveData() {
        return addedUsers;
    }

    @Override
    public LiveData<Boolean> getHasMoreLiveData() {
        return hasMore;
    }

    @Override
    public LiveData<String> getErrorsMessageLiveData() {
        return errorsMessage;
    }
}
