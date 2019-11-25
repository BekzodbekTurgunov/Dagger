package uz.coder.dagger;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import uz.coder.dagger.di.DaggerAppComponent;


public class BaseApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
