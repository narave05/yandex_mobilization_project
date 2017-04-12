package com.example.narek.project_mobilization_yandex.ui.language_list;


import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.data.model.clean.Language;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LanguageListAdapter extends RecyclerView.Adapter<LanguageListAdapter.LanguageViewHolder> {

    private int mSelectedItem = -1;
    private boolean isClicked;
    private List<Language> mLanguageList;
    private OnItemClickListener mOnItemClickListener;

    public LanguageListAdapter(List<Language> languageList, int selectedItemPosition, OnItemClickListener onItemClickListener) {
        mLanguageList = languageList;
        mSelectedItem = selectedItemPosition;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public LanguageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.language_item, parent, false);
        return new LanguageViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(final LanguageViewHolder holder, int position) {

        final Language language = mLanguageList.get(holder.getAdapterPosition());
        final boolean isChecked = mSelectedItem == holder.getAdapterPosition();
        holder.bind(language, isChecked);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isClicked) {
                    notifyItemChanged(mSelectedItem);
                    mSelectedItem = holder.getAdapterPosition();
                    notifyItemChanged(mSelectedItem);
                    if (mOnItemClickListener != null) {
                        isClicked = true;
                        mOnItemClickListener.onItemClickListener(language);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLanguageList.size();
    }


    public static class LanguageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.language_text)
        TextView mLanguageText;

        @BindView(R.id.check_icon)
        ImageView checkImage;

        Context mContext;

        public LanguageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bind(Language language, boolean isChecked) {
            mLanguageText.setText(language.getFullName());
            int backgroundColor;
            if (isChecked) {
                checkImage.setVisibility(View.VISIBLE);
                backgroundColor = ContextCompat.getColor(mContext, R.color.language_list_item_color);
            } else {
                checkImage.setVisibility(View.GONE);
                backgroundColor = Color.WHITE;
            }
            itemView.setBackgroundColor(backgroundColor);
        }
    }

    public interface OnItemClickListener {
        void onItemClickListener(Language language);
    }
}

