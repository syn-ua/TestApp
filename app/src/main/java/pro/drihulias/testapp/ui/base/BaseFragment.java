package pro.drihulias.testapp.ui.base;

import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import pro.drihulias.testapp.ui.base.interfaces.BaseView;

import static android.widget.Toast.LENGTH_LONG;

public class BaseFragment extends Fragment implements BaseView {


    protected <Model extends ViewModel> Model viewModels(Class<Model> clazz) {
        return new ViewModelProvider(this).get(clazz);
    }

    public void showToast(String message) {
        Toast.makeText(getContext(), message, LENGTH_LONG).show();
    }
}
