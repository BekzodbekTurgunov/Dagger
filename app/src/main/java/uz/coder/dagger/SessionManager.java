package uz.coder.dagger;

import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import javax.inject.Inject;
import javax.inject.Singleton;

import uz.coder.dagger.models.User;
import uz.coder.dagger.ui.auth.AuthResource;

@Singleton
public class SessionManager {

    private static final String TAG = "SessionManager";
    private MediatorLiveData<AuthResource<User>> cachedUser =  new MediatorLiveData<>();

    @Inject
    public SessionManager() {
    }

    public void authenticateWithId(final LiveData<AuthResource<User>> source){
        if (cachedUser !=null){
            cachedUser.setValue(AuthResource.loading((User)null)); //dastlabki qiymat.
           cachedUser.addSource(source, new Observer<AuthResource<User>>() {
               @Override
               public void onChanged(AuthResource<User> userAuthResource) {
                   cachedUser.setValue(userAuthResource);
                   cachedUser.removeSource(source);
               }
           });
        }
        else {
            Log.d(TAG, "authenticateWithId: previous session detected. Retrieving user from cache. ");
        }
    }
    public void logOut(){
        Log.d(TAG, "logOut: Loging out...");
        cachedUser.setValue(AuthResource.<User>logout());

    }
    public LiveData<AuthResource<User>> getAuthUser(){
        return cachedUser;
    }
}
