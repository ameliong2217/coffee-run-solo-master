package com.cmsc355.coffeerun.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cmsc355.coffeerun.CoffeeRun;

class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 480;
        config.height = 800;
        config.title = CoffeeRun.TITLE;
		new LwjglApplication(new CoffeeRun(), config);

	}
}
