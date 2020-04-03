package pro.drihulias.testapp.ui.home.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

import pro.drihulias.testapp.R;
import pro.drihulias.testapp.databinding.CellImageBinding;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.Holder> {
    private ArrayList<String> images = new ArrayList<>();
    private RequestManager glide;
    private LayoutInflater inflater;

    public ImagesAdapter(RequestManager glide) {
        this.glide = glide;
    }

    @UiThread
    public void updateItems(List<String> items) {
        this.images.clear();
        if (items != null) {
            images.addAll(items);
        }
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        return new Holder(DataBindingUtil.inflate(inflater, R.layout.cell_image, parent, false), glide);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bind(getItem(position));
    }

    private String getItem(int position) {
        return images.get(position);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }


    public static class Holder extends RecyclerView.ViewHolder {
        private CellImageBinding views;
        private RequestManager glide;

        public Holder(CellImageBinding inflate, RequestManager glide) {
            super(inflate.getRoot());
            this.views = inflate;
            this.glide = glide;
        }

        void bind(String imageUrl) {
            glide.load(imageUrl).into(views.image);
        }
    }
}
