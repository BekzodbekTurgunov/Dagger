package uz.coder.dagger.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import uz.coder.dagger.SessionManager;
import uz.coder.dagger.models.User;
import uz.coder.dagger.network.auth.AuthApi;

public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";
    private final AuthApi authApi;
    private SessionManager sessionManager;

    @Inject
    public AuthViewModel(AuthApi authApi,SessionManager sessionManager) {
        Log.d(TAG, "AuthViewModel: ViewModel is working ");
        this.authApi = authApi;
        this.sessionManager = sessionManager;
    }

    public void authentetecateWithId(int userId){
        Log.d(TAG, "authentetecateWithId: attepting to login");
        sessionManager.authenticateWithId(queryUserId(userId));
        

    }
    private LiveData<AuthResource<User>> queryUserId(int userId){
        return LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(userId)
                        // instead  of calling onError (error happens)
                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception {
                                User errorUser =  new User();
                                errorUser.setId(-1);

                                return errorUser;
                            }
                        })

                        //wrap user object in AuthResource
                        .map(new Function<User, AuthResource<User>>() {
                            @Override
                            public AuthResource<User> apply(User user) throws Exception {
                                if (user.getId() ==-1){
                                    return AuthResource.error("Could not authenticate" , null);

                                }

                                return AuthResource.authenticated(user);
                            }
                        })
                        .subscribeOn(Schedulers.io())
        );
    }

    public LiveData<AuthResource<User>> observeAuthState(){

        return sessionManager.getAuthUser();
    }
}
//93 584 02 21