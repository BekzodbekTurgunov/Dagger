package uz.coder.dagger.network.auth;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import uz.coder.dagger.models.User;

public interface AuthApi {
    @GET("users/{id}")
    Flowable<User> getUser(@Path("id") int id);
}
