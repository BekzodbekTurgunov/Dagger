package uz.coder.dagger.network.main;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import uz.coder.dagger.models.Post;

public interface MainApi {

    //posts?userId = 1
    @GET("posts")
    Flowable<List<Post>> getPostsFromUser(@Query("userId")int userId);
}
