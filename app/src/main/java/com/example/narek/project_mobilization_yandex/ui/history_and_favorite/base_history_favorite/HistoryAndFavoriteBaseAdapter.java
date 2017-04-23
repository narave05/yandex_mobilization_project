package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.base_history_favorite;


import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.ui.history_and_favorite.ListStateListener;

import java.util.ArrayList;
import java.util.List;

public class HistoryAndFavoriteBaseAdapter
        extends RecyclerView.Adapter<HistoryViewHolder>
        implements Filterable {

    private boolean isFiltered;
    private List<TranslationDTO> mTranslationDTOList;
    private List<TranslationDTO> mOriginalTranslationDOTList;
    private SearchFilter mSearchFilter;
    private OnItemClickListener mOnItemClickListener;
    private ListStateListener mListStateListener;
    private RecyclerView mRecyclerView;

    public HistoryAndFavoriteBaseAdapter(List<TranslationDTO> translationDTOList, Fragment fragment) {
        mTranslationDTOList = translationDTOList;
        mOriginalTranslationDOTList = translationDTOList;
        mOnItemClickListener = (OnItemClickListener) fragment;
        mListStateListener = (ListStateListener) fragment;

        if (mListStateListener != null) {
            mListStateListener.onListStateChange(mTranslationDTOList.isEmpty(), false);
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
            mOriginalTranslationDOTList.remove(translationDTO);
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
        if (isFiltered) {
            addFirstFromSelectedList(mOriginalTranslationDOTList, translationDTO);
        } else {
            addFirstFromSelectedList(mTranslationDTOList, translationDTO);
            notifyItemInserted(0);
            if (mRecyclerView != null) {
                mRecyclerView.smoothScrollToPosition(0);
            }
        }
    }

    private void addFirstFromSelectedList(List<TranslationDTO> list, TranslationDTO translationDTO) {
        if (list.size() == 0) {
            list.add(translationDTO);
        } else {
            list.add(0, translationDTO);
        }
        if (mListStateListener != null) {
            mListStateListener.onListStateChange(mTranslationDTOList.isEmpty(), isFiltered);
        }
    }

    public void replaceList(List<TranslationDTO> newList) {
        mTranslationDTOList = newList;
        if (mListStateListener != null) {
            mListStateListener.onListStateChange(mTranslationDTOList.isEmpty(), isFiltered);
        }
        notifyDataSetChanged();
    }

    public void removeItem(TranslationDTO translationDTO) {
        int index = mTranslationDTOList.indexOf(translationDTO);
        if (index > -1) {
            mTranslationDTOList.remove(index);
            notifyItemRemoved(index);
        }
        mOriginalTranslationDOTList.remove(translationDTO);
        if (mListStateListener != null) {
            mListStateListener.onListStateChange(mTranslationDTOList.isEmpty(), isFiltered);
        }
    }

    public void deleteAllList() {
        isFiltered = false;
        ArrayList<TranslationDTO> emptyList = new ArrayList<>();
        mTranslationDTOList = emptyList;
        mOriginalTranslationDOTList = emptyList;
        notifyDataSetChanged();
        if (mListStateListener != null) {
            mListStateListener.onListStateChange(true, false);
        }
    }

    public List<TranslationDTO> getOriginalTranslationDOTList() {
        return mOriginalTranslationDOTList;
    }

    public void setFilteredStatus(boolean filtered) {
        isFiltered = filtered;
    }

    public interface OnItemClickListener {
        void onItemClickListener(TranslationDTO translationDTO);
    }

}

