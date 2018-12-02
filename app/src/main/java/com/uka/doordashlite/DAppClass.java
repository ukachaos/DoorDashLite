package com.uka.doordashlite;

import android.app.Application;

import Dagger.DComponent;
import Dagger.DModule;
import Dagger.DaggerDComponent;

/**
 * Application class
 * @author uka
 */
public class DAppClass extends Application {
    DComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerDComponent.builder().dModule(new DModule(this)).build();
    }

    DComponent getComponent() {
        return component;
    }
}
