package com.mina.george.newsfeed.ui.postactivity;

import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.mina.george.newsfeed.NewsApplication;
import com.mina.george.newsfeed.R;
import com.mina.george.newsfeed.di.activity.ActivityModule;
import com.mina.george.newsfeed.ui.base.BaseActivity;
import com.mina.george.newsfeed.utils.GlideApp;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.OnClick;

public class PostActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_image)
    ImageView imageView;
    @BindView(R.id.tv_title)
    TextView titleTextView;
    @BindView(R.id.tv_source)
    TextView authorTextView;
    @BindView(R.id.tv_date)
    TextView dateTextView;
    @BindView(R.id.tv_content)
    TextView contentTextView;
    private String webSite;

    @Override
    protected void onCreateActivityComponents() {
        NewsApplication.getComponent(this)
                .plus(new ActivityModule(this)).inject(this);
        intUi();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_post;
    }

    private void intUi() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getIntent() != null) {
            titleTextView.setText(getIntent().getStringExtra("title"));
            authorTextView.setText(getIntent().getStringExtra("author"));
            dateTextView.setText(getIntent().getStringExtra("date"));
            contentTextView.setText(getIntent().getStringExtra("content"));
            webSite = getIntent().getStringExtra("url");
            GlideApp.with(this)
                    .load(getIntent().getStringExtra("image"))
                    .placeholder(R.drawable.placeholder)
                    .centerCrop().into(imageView);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @OnClick(R.id.tv_open_web)
    void onWebSiteClicked(){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(webSite));
        startActivity(i);
    }
}
