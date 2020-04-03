package pro.drihulias.testapp.ui.base;

import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import pro.drihulias.testapp.ui.base.interfaces.BaseViewModel;

public abstract class BaseViewModelImpl extends ViewModel implements BaseViewModel {
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
}
