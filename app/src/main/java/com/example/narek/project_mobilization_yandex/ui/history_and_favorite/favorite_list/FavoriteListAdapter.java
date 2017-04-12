package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.favorite_list;


import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.ui.history_and_favorite.HistoryAndFavoriteAdapter;

import java.util.List;

public class FavoriteListAdapter extends HistoryAndFavoriteAdapter {

    public FavoriteListAdapter(List<TranslationDTO> translationDTOList, OnItemClickListener onItemClickListener) {
        super(translationDTOList, onItemClickListener);
    }

    @Override
    protected void notifyClickedItem(int position) {
            notifyItemRemoved(position);
    }
}

