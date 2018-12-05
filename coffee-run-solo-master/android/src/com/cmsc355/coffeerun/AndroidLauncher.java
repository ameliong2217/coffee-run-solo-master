package com.cmsc355.coffeerun;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.cmsc355.coffeerun.CoffeeRun;
public class AndroidLauncher extends AndroidApplication {




	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		initialize(new CoffeeRun(), config);
	}
}
