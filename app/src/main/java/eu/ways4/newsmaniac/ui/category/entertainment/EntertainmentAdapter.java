package eu.ways4.newsmaniac.ui.category.entertainment;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
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
import eu.ways4.newsmaniac.remote.model.Article;
import com.squareup.picasso.Picasso;

public class EntertainmentAdapter extends ListAdapter<Article, EntertainmentAdapter.EntertainmentViewHolder> {

    HeadlineListItemBinding binding;
    Activity activity;

    public EntertainmentAdapter(Activity activity) {
        super(diffUtilCallback);
        this.activity = activity;
    }


    @NonNull
    @Override
    public EntertainmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = HeadlineListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new EntertainmentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EntertainmentViewHolder holder, int position) {
        Article article = getItem(position);
        holder.bind(article);
        holder.itemView.setOnClickListener(v -> {
            NewsDetailActivity.start(holder.itemView.getContext(), article.getUrl());
        });
    }

    class EntertainmentViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTv;
        private TextView descriptionTv;
        private ImageView headlineImage, share, like;

        public EntertainmentViewHolder(HeadlineListItemBinding binding) {
            super(binding.getRoot());
            titleTv = binding.title;
            descriptionTv = binding.description;
            headlineImage = binding.articleImage;
            like = binding.like;
            like.setVisibility(View.INVISIBLE);
            share = binding.share;
        }

        private void bind(Article article) {
            titleTv.setText(article.getTitle());
            descriptionTv.setText(article.getDescription());
            String path = article.getUrlToImage();
            if (!(path == null)) {
                if (!path.isEmpty()) {
                    Picasso.get().load(path).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(headlineImage);
                    share.setOnClickListener(v -> {
                        AppUtils.shareNewsTitle(v.getContext(), activity, article.getTitle() + "\n" + article.getUrl());
                    });
                }

            }
//            Picasso.get().load(path).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(headlineImage);
//            share.setOnClickListener(v -> {
//                AppUtils.shareNewsTitle(v.getContext(), activity, article.getTitle() + "\n" + article.getUrl());
//            });
        }
    }

    private static DiffUtil.ItemCallback<Article> diffUtilCallback = new DiffUtil.ItemCallback<Article>() {
        @Override
        public boolean areItemsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
            return oldItem.equals(newItem);
        }
    };

    public interface ItemCLickedListener {
        void itemClicked(Article article);
    }
}
