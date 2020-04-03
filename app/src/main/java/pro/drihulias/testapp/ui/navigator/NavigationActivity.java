package pro.drihulias.testapp.ui.navigator;

import android.os.Bundle;

import androidx.annotation.Nullable;

import pro.drihulias.testapp.R;
import pro.drihulias.testapp.ui.base.BaseActivity;

public class NavigationActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
    }
}
