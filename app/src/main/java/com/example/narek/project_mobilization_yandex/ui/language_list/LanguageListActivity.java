package com.example.narek.project_mobilization_yandex.ui.language_list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.data.model.clean.Language;
import com.example.narek.project_mobilization_yandex.ui.base_repository.BaseRepositoryActivity;
import com.example.narek.project_mobilization_yandex.ui.widget.ErrorLayout;
import com.example.narek.project_mobilization_yandex.ui.widget.LoadingView;
import com.example.narek.project_mobilization_yandex.util.ViewHelper;

import java.util.List;

import butterknife.BindView;

import static com.example.narek.project_mobilization_yandex.util.constant.Constants.CHECKED_LANGUAGE_INTENT_KEY;
import static com.example.narek.project_mobilization_yandex.util.constant.Constants.FIRST_LANGUAGE_LIST;
import static com.example.narek.project_mobilization_yandex.util.constant.Constants.LANGUAGE_INTENT_KEY;
import static com.example.narek.project_mobilization_yandex.util.constant.Constants.LANGUAGE_LIST_TYPE_INTENT_KEY;
import static com.example.narek.project_mobilization_yandex.util.constant.Constants.SECOND_LANGUAGE_LIST;

public class LanguageListActivity extends BaseRepositoryActivity<LanguageListContract.IView, LanguageListContract.IPresenter>
        implements LanguageListContract.IView, LanguageListAdapter.OnItemClickListener {

    public static Intent getStartIntent(Context context) {
        return new Intent(context, LanguageListActivity.class);
    }

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.loading_view)
    LoadingView mLoadingView;

    @BindView(R.id.error_layout)
    ErrorLayout mErrorLayout;

    private int mLanguageListType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_list);


        Intent intent = getIntent();
        if (intent != null) {
            mLanguageListType = intent.getIntExtra(LANGUAGE_LIST_TYPE_INTENT_KEY, 0);
            String checkedLanguageCod = intent.getStringExtra(CHECKED_LANGUAGE_INTENT_KEY);
            presenter.init(checkedLanguageCod);
        }
        presenter.startLoadData();

        initToolBar();
    }


    @NonNull
    @Override
    public LanguageListContract.IPresenter createPresenter() {
        return new LanguageListPresenter();
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
        mErrorLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLanguageList(List<Language> languages, int selectedItemPosition) {
        setupAdapter(languages, selectedItemPosition);
    }

    @Override
    public void returnActivityResultOk(Language language) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(LANGUAGE_LIST_TYPE_INTENT_KEY, mLanguageListType);
        returnIntent.putExtra(LANGUAGE_INTENT_KEY, language);
        setResult(Activity.RESULT_OK, returnIntent);
    }

    @Override
    public void returnActivityResultCancel() {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
    }

    @Override
    public void finishLanguageListActivity() {
        finish();
    }

    @Override
    public void onItemClickListener(Language language) {
        presenter.handleLanguageSelected(language);
    }

    private void initToolBar() {
        String toolbarTitle = "";
        if (mLanguageListType == FIRST_LANGUAGE_LIST) {
            toolbarTitle = getResources().getString(R.string.language_list_tollbar_first_text);
        } else if (mLanguageListType == SECOND_LANGUAGE_LIST) {
            toolbarTitle = getResources().getString(R.string.language_list_tollbar_second_text);
        }
        mToolbar.setTitle(toolbarTitle);
        mToolbar.setTitleTextColor(Color.BLACK);
        mToolbar.setNavigationIcon(R.drawable.arrow_left);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.handleOnBackPressed();
            }
        });
    }

    private void setupAdapter(List<Language> languages, int selectedItemPosition) {
        mRecyclerView.setVisibility(View.VISIBLE);
        LanguageListAdapter adapter = new LanguageListAdapter(languages, selectedItemPosition, this);
        mRecyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }
}
