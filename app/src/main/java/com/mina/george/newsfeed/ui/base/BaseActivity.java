package com.mina.george.newsfeed.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.mina.george.newsfeed.NewsApplication;
import com.mina.george.newsfeed.R;
import com.mina.george.newsfeed.di.activity.ActivityModule;
import com.mina.george.newsfeed.di.scope.ActivityScope;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

@ActivityScope
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        NewsApplication.getComponent(this)
                .plus(new ActivityModule(this)).inject(this);
        ButterKnife.bind(this);
        onCreateActivityComponents();
    }

    protected abstract void onCreateActivityComponents();

    @LayoutRes
    protected abstract int getLayout();

    public void showErrorSnackbar(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        Snackbar.SnackbarLayout snackBarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackBarLayout.setBackgroundColor(0x00000000);
        snackBarLayout.setPadding(0, 0, 0, 0);
        snackBarLayout.findViewById(R.id.snackbar_text).setVisibility(View.INVISIBLE);
        View snackContainer = LayoutInflater.from(this).inflate(R.layout.view_error_snackbar, null, false);
        TextView contentView = snackContainer.findViewById(R.id.snackbar_content);
        Button dismiss = snackContainer.findViewById(R.id.btn_dismiss);
        contentView.setText(message);
        snackBarLayout.addView(snackContainer, 0);
        dismiss.setOnClickListener(v -> snackbar.dismiss());
        snackbar.show();
    }
}
