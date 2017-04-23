package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.base_history_favorite;


import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDto;
import com.example.narek.project_mobilization_yandex.ui.history_and_favorite.ListStateListener;

import java.util.ArrayList;
import java.util.List;

public class HistoryAndFavoriteBaseAdapter
        extends RecyclerView.Adapter<HistoryViewHolder>
        implements Filterable {

    private boolean isFiltered;
    private List<TranslationDto> mTranslationDtoList;
    private List<TranslationDto> mOriginalTranslationDOTList;
    private SearchFilter mSearchFilter;
    private OnItemClickListener mOnItemClickListener;
    private ListStateListener mListStateListener;
    private RecyclerView mRecyclerView;

    HistoryAndFavoriteBaseAdapter(List<TranslationDto> translationDtoList, Fragment fragment) {
        mTranslationDtoList = translationDtoList;
        mOriginalTranslationDOTList = translationDtoList;
        mOnItemClickListener = (OnItemClickListener) fragment;
        mListStateListener = (ListStateListener) fragment;

        if (mListStateListener != null) {
            mListStateListener.onListStateChange(mTranslationDtoList.isEmpty(), false);
        }
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

    @Override
    public void onBindViewHolder(final HistoryViewHolder holder, int position) {
        final TranslationDto translationDto = mTranslationDtoList.get(position);
        holder.bind(translationDto);
    }

    @Override
    public int getItemCount() {
        return mTranslationDtoList.size();
    }

    @Override
    public Filter getFilter() {
        if (mSearchFilter == null) {
            mSearchFilter = new SearchFilter(this);
        }
        return mSearchFilter;
    }

    private void onFavoriteIconClick(HistoryViewHolder historyViewHolder) {
        if (historyViewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
            final TranslationDto translationDto = mTranslationDtoList.get(historyViewHolder.getAdapterPosition());
            if (mOnItemClickListener != null) {
                translationDto.setFavorite(!translationDto.isFavorite());
                mOnItemClickListener.onItemClickListener(translationDto);
            }
        }
    }

    public void insertedOrUpdatePositionItem(TranslationDto translationDto) {
        int index = mTranslationDtoList.indexOf(translationDto);
        if (index > -1) {
            mTranslationDtoList.remove(index);
            mOriginalTranslationDOTList.remove(translationDto);
            notifyItemRemoved(index);
        }
        addFirst(translationDto);
    }

    public void updateItem(TranslationDto translationDto) {

        int index = mTranslationDtoList.indexOf(translationDto);
        if (index > -1) {
            mTranslationDtoList.set(index, translationDto);
            notifyItemChanged(index);
        }
    }

    public void addFirst(TranslationDto translationDto) {
        if (isFiltered) {
            addFirstFromSelectedList(mOriginalTranslationDOTList, translationDto);
        } else {
            addFirstFromSelectedList(mTranslationDtoList, translationDto);
            notifyItemInserted(0);
            if (mRecyclerView != null) {
                mRecyclerView.smoothScrollToPosition(0);
            }
        }
    }

    private void addFirstFromSelectedList(List<TranslationDto> list, TranslationDto translationDto) {
        if (list.size() == 0) {
            list.add(translationDto);
        } else {
            list.add(0, translationDto);
        }
        if (mListStateListener != null) {
            mListStateListener.onListStateChange(mTranslationDtoList.isEmpty(), isFiltered);
        }
    }

    void replaceList(List<TranslationDto> newList) {
        mTranslationDtoList = newList;
        if (mListStateListener != null) {
            mListStateListener.onListStateChange(mTranslationDtoList.isEmpty(), isFiltered);
        }
        notifyDataSetChanged();
    }

    public void removeItem(TranslationDto translationDto) {
        int index = mTranslationDtoList.indexOf(translationDto);
        if (index > -1) {
            mTranslationDtoList.remove(index);
            notifyItemRemoved(index);
        }
        mOriginalTranslationDOTList.remove(translationDto);
        if (mListStateListener != null) {
            mListStateListener.onListStateChange(mTranslationDtoList.isEmpty(), isFiltered);
        }
    }

    void deleteAllList() {
        isFiltered = false;
        ArrayList<TranslationDto> emptyList = new ArrayList<>();
        mTranslationDtoList = emptyList;
        mOriginalTranslationDOTList = emptyList;
        notifyDataSetChanged();
        if (mListStateListener != null) {
            mListStateListener.onListStateChange(true, false);
        }
    }

    List<TranslationDto> getOriginalTranslationDOTList() {
        return mOriginalTranslationDOTList;
    }

    void setFilteredStatus(boolean filtered) {
        isFiltered = filtered;
    }

    interface OnItemClickListener {
        void onItemClickListener(TranslationDto translationDto);
    }

}

