package uz.coder.dagger.ui.main.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import uz.coder.dagger.SessionManager;
import uz.coder.dagger.models.User;
import uz.coder.dagger.ui.auth.AuthResource;

public class ProfileViewModel extends ViewModel {
    private SessionManager sessionManager;

    private static final String TAG = "ProfileViewModel";
    @Inject
    public ProfileViewModel(SessionManager sessionManager){
        this.sessionManager = sessionManager;
        Log.d(TAG, "ProfileViewModel: viewModel is ready...");
    }
    public LiveData<AuthResource<User>> getAuthenticatedUser(){
        return sessionManager.getAuthUser();
    }
}
