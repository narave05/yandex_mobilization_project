package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.base_history_favorite;

import android.widget.Filter;

import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDto;

import java.util.ArrayList;
import java.util.List;

 class SearchFilter extends Filter {
    private HistoryAndFavoriteBaseAdapter mAdapter;

    SearchFilter(HistoryAndFavoriteBaseAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        List<TranslationDto> originalList = mAdapter.getOriginalTranslationDOTList();
        if (constraint != null && constraint.length() > 0) {
            List<TranslationDto> filteredList = new ArrayList<>();
            for (TranslationDto translationDto : originalList) {
                if (containsIgnoreCase(translationDto, constraint.toString())) {
                    filteredList.add(translationDto);
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
        mAdapter.replaceList((List<TranslationDto>) results.values);
    }

    private boolean containsIgnoreCase(TranslationDto translationDto, String queryText) {
        return translationDto.getOriginalText().toLowerCase().contains(queryText.toLowerCase()) ||
                translationDto.getTranslatedTextList().get(0).toLowerCase().contains(queryText.toLowerCase());
    }
}
