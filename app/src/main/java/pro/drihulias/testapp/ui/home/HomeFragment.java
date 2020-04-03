package pro.drihulias.testapp.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import pro.drihulias.testapp.R;
import pro.drihulias.testapp.databinding.FragmentHomeBinding;
import pro.drihulias.testapp.ui.base.BaseFragment;
import pro.drihulias.testapp.ui.home.adapter.UsersAdapter;
import pro.drihulias.testapp.ui.home.interfaces.HomeViewModel;
import pro.drihulias.testapp.ui.home.models.HomeViewModelImpl;

public class HomeFragment extends BaseFragment {
    private HomeViewModel viewModel;
    private FragmentHomeBinding views;
    private UsersAdapter adapter;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        viewModel = viewModels(HomeViewModelImpl.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        views = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return views.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycleView();

    }

    private void initRecycleView() {
        adapter = new UsersAdapter();
        views.recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        views.recycler.setAdapter(adapter);
        adapter.setNextPageListener(viewModel::onNextPageRequest);
        viewModel.getRefreshUsersLiveData().observe(getViewLifecycleOwner(), userBLModels -> adapter.initData(userBLModels));
        viewModel.getAddedUsersLiveData().observe(getViewLifecycleOwner(), userBLModels -> adapter.addUsers(userBLModels));
        viewModel.getErrorsMessageLiveData().observe(getViewLifecycleOwner(), this::showToast);
    }
}
