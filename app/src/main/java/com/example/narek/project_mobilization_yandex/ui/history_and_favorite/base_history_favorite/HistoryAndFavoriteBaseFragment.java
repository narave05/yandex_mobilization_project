package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.base_history_favorite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDto;
import com.example.narek.project_mobilization_yandex.ui.base_repository.BaseRepositoryFragment;
import com.example.narek.project_mobilization_yandex.ui.history_and_favorite.DeleteClickCallback;
import com.example.narek.project_mobilization_yandex.ui.history_and_favorite.ListStateListener;
import com.example.narek.project_mobilization_yandex.ui.widget.LoadingView;
import com.example.narek.project_mobilization_yandex.ui.widget.SearchView;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;
import net.yslibrary.android.keyboardvisibilityevent.Unregistrar;

import java.util.List;

import butterknife.BindView;

public abstract class HistoryAndFavoriteBaseFragment
        <V extends HistoryAndFavoriteBaseContract.IView,
                P extends HistoryAndFavoriteBaseContract.IPresenter<V>>
        extends BaseRepositoryFragment<V, P>
        implements HistoryAndFavoriteBaseContract.IView,
        HistoryAndFavoriteBaseAdapter.OnItemClickListener,
        DeleteClickCallback, ListStateListener, SearchView.OnQueryTextListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.loading_view)
    LoadingView mLoadingView;

    @BindView(R.id.hint_layout_id)
    LinearLayout mHintLayout;

    @BindView(R.id.search_bar)
    SearchView mSearchView;

    protected HistoryAndFavoriteBaseAdapter mAdapter;

    private Unregistrar mUnregistrar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSearchView.setOnQueryChangeListener(this);
        presenter.init();
        registerKeyboardVisibilityEvent();

    }

    @Override
    protected boolean whitButterKnife() {
        return true;
    }

    @Override
    public void showProgress() {
        mLoadingView.show();
    }

    @Override
    public void hideProgress() {
        mLoadingView.hide();
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onItemClickListener(TranslationDto translationDto) {
        presenter.handleFavoriteStatusChanged(translationDto);
    }

    @Override
    public void deleteHistoryAndFavoriteList() {
        mAdapter.deleteAllList();
    }

    @Override
    public void filterAdapterList(String text) {
        if (mAdapter != null) {
            mAdapter.getFilter().filter(text);
        }
    }

    @Override
    public void showHintLayout() {
        mHintLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSearchView() {
        mSearchView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideHintLayout() {
        mHintLayout.setVisibility(View.GONE);
    }

    @Override
    public void hideSearchView() {
        mSearchView.resetInputText();
        mSearchView.setVisibility(View.GONE);
    }

    @Override
    public void onDeleteClick() {
        presenter.handleDeleteClick();
    }

    @Override
    public void onListStateChange(boolean isEmpty, boolean isSearchResult) {
        presenter.handleListStateChange(isEmpty,isSearchResult);
    }



    @Override
    public void onQueryTextChange(String newText) {
        Log.e("onQueryTextChange: ", "_" + newText);
        presenter.handleSearchText(newText);
    }

    protected void setupAdapter(List<TranslationDto> translationDtoList) {
        if (mAdapter == null) {
            mRecyclerView.setVisibility(View.VISIBLE);
            mAdapter = new HistoryAndFavoriteBaseAdapter(translationDtoList, this);
            mRecyclerView.setAdapter(mAdapter);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(layoutManager);
        } else {
            mAdapter.replaceList(translationDtoList);
        }
    }

    private void registerKeyboardVisibilityEvent() {
        mUnregistrar = KeyboardVisibilityEvent.registerEventListener(getActivity(), new KeyboardVisibilityEventListener() {
            @Override
            public void onVisibilityChanged(boolean isOpen) {
                if (!isOpen) {
                    mSearchView.cancelViewFocus();
                }
            }
        });
    }
}
