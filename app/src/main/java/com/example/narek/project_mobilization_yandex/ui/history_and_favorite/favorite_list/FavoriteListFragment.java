package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.favorite_list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.ui.base_repository.BaseRepositoryFragment;
import com.example.narek.project_mobilization_yandex.ui.widget.LoadingView;
import com.example.narek.project_mobilization_yandex.ui.widget.SearchView;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;
import net.yslibrary.android.keyboardvisibilityevent.Unregistrar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnTextChanged;

public class FavoriteListFragment extends BaseRepositoryFragment<FavoriteListContract.IView, FavoriteListContract.IPresenter>
        implements FavoriteListContract.IView, FavoriteListAdapter.OnItemClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.loading_view)
    LoadingView mLoadingView;

    @BindView(R.id.search_bar)
    SearchView mSearchView;

    private FavoriteListAdapter mAdapter;
    private Unregistrar mUnregistrar;

    public static FavoriteListFragment newInstance() {
        FavoriteListFragment fragment = new FavoriteListFragment();
        return fragment;
    }

    @NonNull
    @Override
    public FavoriteListContract.IPresenter createPresenter() {
        return new FavoriteListPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.init();
        registerKeyboardVisibilityEvent();
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
    protected boolean whitButterKnife() {
        return true;
    }

    @Override
    public void onItemClickListener(TranslationDTO translationDTO) {
        presenter.handleFavoriteStatusChanged(translationDTO);
    }

    @Override
    public void showFavoriteList(List<TranslationDTO> data) {
        setupAdapter(data);
    }

    @Override
    public void addFavoriteItem(TranslationDTO translationDTO) {
        if (mAdapter == null) {
            List<TranslationDTO> list = new ArrayList<>();
            list.add(translationDTO);
            setupAdapter(list);
        } else {
            mAdapter.addFirst(translationDTO);
        }
    }

    @Override
    public void removeFavoriteItem(TranslationDTO translationDTO) {
        mAdapter.removeItem(translationDTO);
    }

    @OnTextChanged(R.id.search_view)
    protected void onTextChanged(CharSequence text) {
        mAdapter.getFilter().filter(text);
    }


    private void setupAdapter(List<TranslationDTO> translationDTOList) {
        mRecyclerView.setVisibility(View.VISIBLE);
        mAdapter = new FavoriteListAdapter(translationDTOList, this);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
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
