package pro.drihulias.testapp.ui.home.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

import pro.drihulias.testapp.R;
import pro.drihulias.testapp.databinding.CellUserBinding;
import pro.drihulias.testapp.models.UserBLModel;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.Holder> {
    private static final int PREPEIR_USERS = 5;
    private static final String TAG = UsersAdapter.class.getSimpleName();
    private ArrayList<UserBLModel> data = new ArrayList<>();
    private LayoutInflater inflater;
    private RequestManager glide;
    private Runnable onNextPageRequest;


    @UiThread
    public void initData(List<UserBLModel> items) {
        data.clear();
        if (items != null) {
            data.addAll(items);
            Log.d(TAG, "initData: " + items.size());
        }
        notifyDataSetChanged();
    }

    @UiThread
    public void addUsers(List<UserBLModel> items) {
        if (items != null) {
            int itemSize = data.size();
            data.addAll(items);
            notifyItemRangeInserted(itemSize, items.size());
        }
    }


    @NonNull
    @Override
    public UsersAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
            glide = Glide.with(parent.getContext());
        }
        return new Holder(DataBindingUtil.inflate(inflater, R.layout.cell_user, parent, false), glide);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.Holder holder, int position) {
        holder.bind(getItem(position));
        if (position + PREPEIR_USERS > data.size() && onNextPageRequest != null) {
            onNextPageRequest.run();
        }
    }

    private UserBLModel getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setNextPageListener(Runnable onNextPageRequest) {
        this.onNextPageRequest = onNextPageRequest;
    }


    public static class Holder extends RecyclerView.ViewHolder {
        private final GridLayoutManager gridLayoutManager;
        private CellUserBinding views;
        private ImagesAdapter adapter;
        private RequestManager glide;

        public Holder(CellUserBinding inflate, RequestManager glide) {
            super(inflate.getRoot());
            this.views = inflate;
            adapter = new ImagesAdapter(glide);
            this.glide = glide;
            this.views.imagesRecycle.setAdapter(adapter);
            gridLayoutManager = new GridLayoutManager(this.views.getRoot().getContext(), 2);

            this.views.imagesRecycle.setLayoutManager(gridLayoutManager);
//            this.views.imagesRecycle.addItemDecoration(new GridSpacingItemDecoration(2, spacingInPixels, true, 0));

        }

        void bind(UserBLModel item) {
            views.userName.setText(item.name);
            glide.load(item.avatarImage).into(views.avatarImage);
            adapter.updateItems(item.items);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (item.items.size() % 2 == 1 && position == 0) ? 2 : 1;
                }
            });
        }
    }
}
