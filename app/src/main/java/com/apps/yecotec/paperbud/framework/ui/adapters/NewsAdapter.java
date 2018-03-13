package com.apps.yecotec.paperbud.framework.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.yecotec.paperbud.R;
import com.apps.yecotec.paperbud.domain.models.Article;
import com.apps.yecotec.paperbud.domain.utils.TimeUtil;
import com.apps.yecotec.paperbud.framework.models.SimpleNews;
import com.apps.yecotec.paperbud.framework.presentation.presenters.MainPresenter;
import com.apps.yecotec.paperbud.framework.ui.listeners.AdapterListener;
import com.squareup.picasso.Picasso;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kenruizinoue on 12/1/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AdapterListener{

    String TAG = this.getClass().getSimpleName();

    private enum ViewType {
        CONTRACTED_CARD, EXPANDED_CARD
    }

    private List<SimpleNews> mNewsList;
    private Context mContext;
    private Set<Integer> mSelectedItems;
    private MainPresenter.View mView;

    public NewsAdapter(Context context, MainPresenter.View view) {
        mContext = context;
        mSelectedItems = new HashSet<>();
        mView = view;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // check if this should be an expanded card
        if (viewType == ViewType.EXPANDED_CARD.ordinal()) {
            View view = inflater.inflate(R.layout.item_news_expanded, parent, false);
            return new ExpandedNewsAdapterViewHolder(view, this);
        }

        View view = inflater.inflate(R.layout.item_news_default, parent, false);

        return new NewsAdapterViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        //call bind method of corresponding class
        if (viewHolder instanceof NewsAdapterViewHolder) {
            ((NewsAdapterViewHolder) viewHolder).bind(position);
        } else if(viewHolder instanceof ExpandedNewsAdapterViewHolder) {
            ((ExpandedNewsAdapterViewHolder) viewHolder).bind(position);
        }
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        // check to see if a view at this position should be expanded or default/contracted
        if (mSelectedItems.contains(position))
            return ViewType.EXPANDED_CARD.ordinal();

        return ViewType.CONTRACTED_CARD.ordinal();
    }

    @Override
    public void onClickShowMore(int position) {
        mSelectedItems.add(position);
        notifyItemChanged(position);
    }

    @Override
    public void onClickShowLess(int position) {
        mSelectedItems.remove(position);
        notifyItemChanged(position);
    }

    public void refershItems(@NonNull List<SimpleNews> newsList) {
        // clean up old data
        if (mNewsList != null) {
            mNewsList.clear();
        }
        mNewsList = newsList;

        mSelectedItems = new HashSet<>();
        notifyDataSetChanged();
    }

    public class NewsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.tv_news_title)
        TextView newsTitle;
        @BindView(R.id.iv_news)
        ImageView newsImage;
        @BindView(R.id.tv_news_date)
        TextView newsDate;

        private AdapterListener mListener;

        public NewsAdapterViewHolder(View itemView, final AdapterListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mListener = listener;
            itemView.setOnClickListener(this);
        }

        //inflate widget from article object
        public void bind(int position) {
            final SimpleNews simpleNews = mNewsList.get(position);

            newsTitle.setText(simpleNews.getTitle());

            if(simpleNews.getImgUrl() != null) {
                Picasso.with(mContext)
                        .load(simpleNews.getImgUrl())
                        .error(R.drawable.ic_broken_image)
                        .resize(1000, 1000)
                        .centerCrop()
                        .into(newsImage);
            } else {
                newsImage.setBackground(mContext.getResources().getDrawable(R.drawable.ic_image));
            }

            newsDate.setText(TimeUtil.getOnlyDate(simpleNews.getPublishedDate()));
        }

        @OnClick(R.id.iv_more)
        public void showMoreButtonClicked() {
            mListener.onClickShowMore(getAdapterPosition());
        }

        @Override
        public void onClick(View view) {
            mView.itemSelected(mNewsList.get(getAdapterPosition()));
        }
    }

    public class ExpandedNewsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_news_title)
        TextView newsTitle;
        @BindView(R.id.iv_news)
        ImageView newsImage;
        @BindView(R.id.tv_news_date)
        TextView newsDate;
        @BindView(R.id.tv_news_description)
        TextView newsDescription;

        private AdapterListener mListener;

        public ExpandedNewsAdapterViewHolder(View itemView, final AdapterListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mListener = listener;
            itemView.setOnClickListener(this);
        }

        //inflate widget from article object
        public void bind(int position) {
            SimpleNews simpleNews = mNewsList.get(position);

            newsTitle.setText(simpleNews.getTitle());

            if(simpleNews.getImgUrl() != null) {
                Picasso.with(mContext)
                        .load(simpleNews.getImgUrl())
                        .error(R.drawable.ic_broken_image)
                        .resize(1000, 1000)
                        .centerCrop()
                        .into(newsImage);
            } else {
                newsImage.setBackground(mContext.getResources().getDrawable(R.drawable.ic_image));
            }

            newsDate.setText(TimeUtil.getOnlyDate(simpleNews.getPublishedDate()));
            newsDescription.setText(simpleNews.getDescription());
        }

        @OnClick(R.id.iv_less)
        public void showLessButtonClicked() {
            mListener.onClickShowLess(getAdapterPosition());
        }

        @Override
        public void onClick(View view) {
            mView.itemSelected(mNewsList.get(getAdapterPosition()));
        }
    }
}
