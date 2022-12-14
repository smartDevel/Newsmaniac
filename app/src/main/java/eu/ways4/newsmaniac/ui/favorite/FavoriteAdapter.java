package eu.ways4.newsmaniac.ui.favorite;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import eu.ways4.newsmaniac.ui.NewsDetailActivity;
import eu.ways4.newsmaniac.utils.AppUtils;
import eu.ways4.newsmaniac.R;
import eu.ways4.newsmaniac.databinding.HeadlineListItemBinding;
import eu.ways4.newsmaniac.local.FavoriteEntity;
import com.squareup.picasso.Picasso;

public class FavoriteAdapter extends ListAdapter<FavoriteEntity, FavoriteAdapter.FavoriteViewHolder> {

    HeadlineListItemBinding binding;
    Activity activity;
    ItemCLickedListener clickedListener;

    public FavoriteAdapter(Activity activity) {
        super(diffUtilCallback);
        this.activity = activity;
    }

    public void setClickedListener(ItemCLickedListener clickedListener) {
        this.clickedListener = clickedListener;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = HeadlineListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FavoriteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        FavoriteEntity article = getItem(position);
        holder.bind(article);
        holder.itemView.setOnClickListener(v -> {
            NewsDetailActivity.start(holder.itemView.getContext(), article.getUrl());
        });

    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTv;
        private TextView descriptionTv;
        private ImageView headlineImage, share, like;

        public FavoriteViewHolder(HeadlineListItemBinding binding) {
            super(binding.getRoot());
            titleTv = binding.title;
            descriptionTv = binding.description;
            headlineImage = binding.articleImage;
            like = binding.like;
            share = binding.share;
        }

        private void bind(FavoriteEntity article) {
            titleTv.setText(article.getTitle());
            descriptionTv.setText(article.getDescription());
            String path = article.getUrlToImage();
            String titlePath = article.getTitle();
            String titleUrl = article.getUrl();
            if (titleUrl != ""){
                //titlePath = "<a href='" + path + "'>" + article.getTitle() + "</a>";
                titlePath = titleUrl + " " + article.getTitle();
            }
            Picasso.get().load(path).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(headlineImage);
            String finalTitlePath = titlePath;
            share.setOnClickListener(v -> {
                //AppUtils.shareNewsTitle(v.getContext(), activity, path + " " + article.getTitle() );
                AppUtils.shareNewsTitle(v.getContext(), activity, finalTitlePath);
            });
            like.setOnClickListener(v -> clickedListener.itemClicked(article,getAdapterPosition()));
        }
    }

    private static DiffUtil.ItemCallback<FavoriteEntity> diffUtilCallback = new DiffUtil.ItemCallback<FavoriteEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull FavoriteEntity oldItem, @NonNull FavoriteEntity newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull FavoriteEntity oldItem, @NonNull FavoriteEntity newItem) {
            return oldItem.equals(newItem);
        }
    };

    public interface ItemCLickedListener {
        void itemClicked(FavoriteEntity article, int position);
    }
}
