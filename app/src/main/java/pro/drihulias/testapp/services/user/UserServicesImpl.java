package pro.drihulias.testapp.services.user;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import pro.drihulias.testapp.models.mappers.UserMapper;
import pro.drihulias.testapp.modules.retrofit.api.UserApi;
import pro.drihulias.testapp.services.interfaces.UserService;
import pro.drihulias.testapp.services.user.models.UserLoadingDTO;

public class UserServicesImpl implements UserService {
    private static final int PAGE_SIZE = 10;
    private UserApi userApi;

    public UserServicesImpl(UserApi userApi) {
        this.userApi = userApi;
    }

    @Override
    public Single<UserLoadingDTO> getUsers(int offset, int limit) {
        return userApi.getUsers(offset, limit)
                .subscribeOn(Schedulers.io())
                .flatMap(getUsersResponceModel -> {
                    if (!getUsersResponceModel.status) {
                        return Single.error(new Throwable("Response not good"));
                    }
                    return Flowable.fromIterable(getUsersResponceModel.data.users)
                            .map(UserMapper::mapFromNetModelToBlModel)
                            .toList()
                            .map(userBLModels -> new UserLoadingDTO(userBLModels, getUsersResponceModel.data.hasMore));
                });
    }

    @Override
    public Single<UserLoadingDTO> getFirstPageUsers() {
        return getUsers(0, PAGE_SIZE);
    }

    @Override
    public Single<UserLoadingDTO> getPageWithOffSet(int offset) {
        return getUsers(offset, PAGE_SIZE);
    }
}
