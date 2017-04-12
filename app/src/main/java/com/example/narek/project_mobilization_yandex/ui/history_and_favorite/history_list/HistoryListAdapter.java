package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.history_list;


import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.ui.history_and_favorite.HistoryAndFavoriteAdapter;

import java.util.List;

public class HistoryListAdapter extends HistoryAndFavoriteAdapter {


    public HistoryListAdapter(List<TranslationDTO> translationDTOList, OnItemClickListener onItemClickListener) {
        super(translationDTOList, onItemClickListener);
    }

    @Override
    protected void notifyClickedItem(int position) {
        notifyItemChanged(position);
    }
}

