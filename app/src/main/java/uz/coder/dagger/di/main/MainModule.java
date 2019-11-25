package uz.coder.dagger.di.main;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import uz.coder.dagger.network.main.MainApi;
import uz.coder.dagger.ui.main.posts.PostRecyclerAdapter;

@Module
public class MainModule {
    @Provides
    static MainApi provideMainApi(Retrofit retrofit)
    {
        return retrofit.create(MainApi.class);
    }

    @Provides
    static PostRecyclerAdapter provideAdapter(){
        return new PostRecyclerAdapter();
    }
}
