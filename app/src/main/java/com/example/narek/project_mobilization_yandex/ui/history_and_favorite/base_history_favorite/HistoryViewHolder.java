package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.base_history_favorite;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;

import butterknife.BindView;
import butterknife.ButterKnife;

public  class HistoryViewHolder extends RecyclerView.ViewHolder {

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