package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.favorite_list;


import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.ui.history_and_favorite.HistoryAndFavoriteBaseAdapter;

import java.util.List;

public class FavoriteListAdapter extends HistoryAndFavoriteBaseAdapter {

    public FavoriteListAdapter(List<TranslationDTO> translationDTOList, OnItemClickListener onItemClickListener) {
        super(translationDTOList, onItemClickListener);
    }
}

