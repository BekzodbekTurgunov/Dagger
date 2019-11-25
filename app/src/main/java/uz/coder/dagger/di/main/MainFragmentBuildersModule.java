package uz.coder.dagger.di.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import uz.coder.dagger.ui.main.posts.PostsFragment;
import uz.coder.dagger.ui.main.profile.ProfileFragment;

@Module
public abstract class MainFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector
    abstract PostsFragment contributePostsFragment();

}
