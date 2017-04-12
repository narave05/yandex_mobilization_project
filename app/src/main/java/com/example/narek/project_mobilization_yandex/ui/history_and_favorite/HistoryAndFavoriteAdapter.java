package com.example.narek.project_mobilization_yandex.ui.history_and_favorite;


import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class HistoryAndFavoriteAdapter extends RecyclerView.Adapter<HistoryAndFavoriteAdapter.HistoryViewHolder> {

    private List<TranslationDTO> mTranslationDTOList;
    private OnItemClickListener mOnItemClickListener;

    public HistoryAndFavoriteAdapter(List<TranslationDTO> translationDTOList, OnItemClickListener onItemClickListener) {
        mTranslationDTOList = translationDTOList;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
                    mOnItemClickListener.onItemClickListener(translationDTO.getPrimaryKey(), !translationDTO.isFavorite());
                    notifyClickedItem(historyViewHolder.getAdapterPosition());
                }
        }
    }

    protected abstract void notifyClickedItem(int position);


    @Override
    public void onBindViewHolder(final HistoryViewHolder holder, int position) {
        final TranslationDTO translationDTO = mTranslationDTOList.get(position);
        holder.bind(translationDTO);
    }

    @Override
    public int getItemCount() {
        return mTranslationDTOList.size();
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

            // TODO: 12.04.2017 move to color recurse
            if (translationDTO.isFavorite()) {
                mFavImage.setColorFilter(Color.parseColor("#ffcc00"), PorterDuff.Mode.SRC_IN);
            } else {
                mFavImage.setColorFilter(Color.parseColor("#858e98"), PorterDuff.Mode.SRC_IN);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClickListener(String primaryKey, boolean isChecked);
    }
}

