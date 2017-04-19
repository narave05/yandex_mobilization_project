package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.history_list;


import android.widget.Filter;

import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.ui.history_and_favorite.HistoryAndFavoriteBaseAdapter;

import java.util.List;

public class HistoryListAdapter extends HistoryAndFavoriteBaseAdapter {


    public HistoryListAdapter(List<TranslationDTO> translationDTOList, OnItemClickListener onItemClickListener) {
        super(translationDTOList, onItemClickListener);
    }
}

