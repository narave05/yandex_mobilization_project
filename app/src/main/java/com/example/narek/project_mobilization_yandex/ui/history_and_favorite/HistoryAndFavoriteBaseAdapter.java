package com.example.narek.project_mobilization_yandex.ui.history_and_favorite;


import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryAndFavoriteBaseAdapter
        extends RecyclerView.Adapter<HistoryAndFavoriteBaseAdapter.HistoryViewHolder>
        implements Filterable {

    protected List<TranslationDTO> mTranslationDTOList;
    protected List<TranslationDTO> mOriginalTranslationDOTList;
    private SearchFilter mSearchFilter;
    private OnItemClickListener mOnItemClickListener;
    private RecyclerView mRecyclerView;

    public HistoryAndFavoriteBaseAdapter(List<TranslationDTO> translationDTOList, OnItemClickListener onItemClickListener) {
        mTranslationDTOList = translationDTOList;
        mOriginalTranslationDOTList = translationDTOList;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mRecyclerView = ((RecyclerView) parent);
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.history_and_favorite_item, parent, false);
        final HistoryViewHolder historyViewHolder = new HistoryViewHolder(inflatedView);
        historyViewHolder.mFavImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFavoriteIconClick(historyViewHolder);
            }
        });

        return historyViewHolder;
    }

    private void onFavoriteIconClick(HistoryViewHolder historyViewHolder) {
        if (historyViewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
            final TranslationDTO translationDTO = mTranslationDTOList.get(historyViewHolder.getAdapterPosition());
            if (mOnItemClickListener != null) {
                translationDTO.setFavorite(!translationDTO.isFavorite());
                mOnItemClickListener.onItemClickListener(translationDTO);
            }
        }
    }

    public void insertedOrUpdatePositionItem(TranslationDTO translationDTO) {
        int index = mTranslationDTOList.indexOf(translationDTO);
        if (index > -1) {
            mTranslationDTOList.remove(index);
            notifyItemRemoved(index);
        }
        addFirst(translationDTO);
    }

    public void updateItem(TranslationDTO translationDTO) {
        int index = mTranslationDTOList.indexOf(translationDTO);
        if (index > -1) {
            mTranslationDTOList.set(index, translationDTO);
            notifyItemChanged(index);
        }
    }

    public void addFirst(TranslationDTO translationDTO) {
        mTranslationDTOList.add(0, translationDTO);
        notifyItemInserted(0);
        mRecyclerView.smoothScrollToPosition(0);
    }

    public void addNewList(List<TranslationDTO> newList) {
        mTranslationDTOList = newList;
        notifyDataSetChanged();
    }

    public void removeItem(TranslationDTO translationDTO) {
        int index = mTranslationDTOList.indexOf(translationDTO);
        if (index > -1) {
            mTranslationDTOList.remove(index);
            mOriginalTranslationDOTList.remove(translationDTO);
            notifyItemRemoved(index);
        }
    }

    @Override
    public void onBindViewHolder(final HistoryViewHolder holder, int position) {
        final TranslationDTO translationDTO = mTranslationDTOList.get(position);
        holder.bind(translationDTO);
    }

    @Override
    public int getItemCount() {
        return mTranslationDTOList.size();
    }

    @Override
    public Filter getFilter() {
        if (mSearchFilter == null) {
            mSearchFilter = new SearchFilter(this);
        }
        return mSearchFilter;
    }

    public List<TranslationDTO> getOriginalTranslationDOTList() {
        return mOriginalTranslationDOTList;
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.original_text)
        TextView mOriginalText;

        @BindView(R.id.translated_text)
        TextView mTranslatedText;

        @BindView(R.id.language_pair_cod_text)
        TextView mLanguagePairCodText;

        @BindView(R.id.fav_icon)
        AppCompatImageView mFavImage;

        Context mContext;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bind(TranslationDTO translationDTO) {

            mOriginalText.setText(translationDTO.getOriginalText());
            mTranslatedText.setText(translationDTO.getTranslatedTextList().get(0));
            mLanguagePairCodText.setText(translationDTO.getLanguagePairCodText().toUpperCase());
            int color;
            if (translationDTO.isFavorite()) {
                color = ContextCompat.getColor(mContext, R.color.fav_active_icon_color);
            } else {
                color = ContextCompat.getColor(mContext, R.color.fav_inactive_icon_color);
            }
            mFavImage.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        }
    }


    private static class SearchFilter extends Filter {
        HistoryAndFavoriteBaseAdapter mAdapter;

        public SearchFilter(HistoryAndFavoriteBaseAdapter adapter) {
            mAdapter = adapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<TranslationDTO> originalList = mAdapter.getOriginalTranslationDOTList();
            if (constraint != null && constraint.length() > 0) {
                List<TranslationDTO> filteredList = new ArrayList<>();
                for (TranslationDTO translationDTO : originalList) {
                    if (containsIgnoreCase(translationDTO, constraint.toString())) {
                        filteredList.add(translationDTO);
                    }
                }
                results.count = filteredList.size();
                results.values = filteredList;
            } else {
                results.count = originalList.size();
                results.values = originalList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mAdapter.addNewList((List<TranslationDTO>) results.values);
        }

        private boolean containsIgnoreCase(TranslationDTO translationDTO, String queryText) {
            return translationDTO.getOriginalText().toLowerCase().contains(queryText.toLowerCase()) ||
                    translationDTO.getTranslatedTextList().get(0).toLowerCase().contains(queryText.toLowerCase());
        }
    }

    public interface OnItemClickListener {
        void onItemClickListener(TranslationDTO translationDTO);
    }

}

