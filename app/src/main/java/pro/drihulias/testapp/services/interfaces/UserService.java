package pro.drihulias.testapp.services.interfaces;

import io.reactivex.rxjava3.core.Single;
import pro.drihulias.testapp.services.user.models.UserLoadingDTO;

public interface UserService {
    Single<UserLoadingDTO> getUsers(int offset, int limit);

    Single<UserLoadingDTO> getFirstPageUsers();

    Single<UserLoadingDTO> getPageWithOffSet(int offset);
}
