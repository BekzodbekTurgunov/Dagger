package uz.coder.dagger.di.auth;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import uz.coder.dagger.network.auth.AuthApi;

@Module
public class AuthModule {
    @Provides
    static AuthApi provideAuthApi(Retrofit retrofit){
        return retrofit.create(AuthApi.class);
    }
}
