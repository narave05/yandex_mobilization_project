package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.base_history_favorite;

import android.widget.Filter;

import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;

import java.util.ArrayList;
import java.util.List;

public class SearchFilter extends Filter {
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
            mAdapter.setFilteredStatus(true);
        } else {
            results.count = originalList.size();
            results.values = originalList;
            mAdapter.setFilteredStatus(false);
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        mAdapter.replaceList((List<TranslationDTO>) results.values);
    }

    private boolean containsIgnoreCase(TranslationDTO translationDTO, String queryText) {
        return translationDTO.getOriginalText().toLowerCase().contains(queryText.toLowerCase()) ||
                translationDTO.getTranslatedTextList().get(0).toLowerCase().contains(queryText.toLowerCase());
    }
}
