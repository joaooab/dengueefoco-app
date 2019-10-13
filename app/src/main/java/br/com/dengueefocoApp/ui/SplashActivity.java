package br.com.dengueefocoApp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import br.com.dengueefocoApp.R;

public class SplashActivity extends AppCompatActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
	}

	@Override
	protected void onResume() {
		super.onResume();
		new Handler().postDelayed(() -> {
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
		}, 3000);
	}

}