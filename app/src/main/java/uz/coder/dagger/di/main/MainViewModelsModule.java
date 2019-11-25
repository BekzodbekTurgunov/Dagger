package uz.coder.dagger.di.main;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import uz.coder.dagger.di.ViewModelKey;
import uz.coder.dagger.ui.main.posts.PostsViewModel;
import uz.coder.dagger.ui.main.profile.ProfileViewModel;

@Module
public abstract class MainViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileModul(ProfileViewModel profileViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel.class)
    public abstract ViewModel bindPostsModul(PostsViewModel postsViewModel);

}
