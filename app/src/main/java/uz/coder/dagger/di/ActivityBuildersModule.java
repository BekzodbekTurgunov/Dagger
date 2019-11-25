package uz.coder.dagger.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import uz.coder.dagger.di.auth.AuthModule;
import uz.coder.dagger.di.auth.AuthViewModelsModule;
import uz.coder.dagger.di.main.MainFragmentBuildersModule;
import uz.coder.dagger.di.main.MainModule;
import uz.coder.dagger.di.main.MainViewModelsModule;
import uz.coder.dagger.ui.auth.AuthActivity;
import uz.coder.dagger.ui.main.MainActivity;

@Module
public abstract class ActivityBuildersModule {


    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class,
                    AuthModule.class}
    )
    abstract AuthActivity contributeAuthActivity();

    @ContributesAndroidInjector(
            modules = {
                    MainFragmentBuildersModule.class,
                    MainViewModelsModule.class,
                    MainModule.class,
            }
    )
    abstract MainActivity contributeMainActivity();




}
