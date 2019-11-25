package uz.coder.dagger.ui.main.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import uz.coder.dagger.R;
import uz.coder.dagger.models.User;
import uz.coder.dagger.ui.auth.AuthResource;
import uz.coder.dagger.ui.main.MainActivity;
import uz.coder.dagger.viewmodels.ViewModelProviderFactory;

public class ProfileFragment extends DaggerFragment {
    private static final String TAG = "ProfileFragment";
    private TextView email, username, website;

    @Inject
    ViewModelProviderFactory providerFactory;
    private ProfileViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(this,providerFactory).get(ProfileViewModel.class);
        AuthResource<User> user  = ((MainActivity)getActivity()).sessionManager.getAuthUser().getValue();
        Log.d(TAG, "onViewCreated: " + user);
        email = view.findViewById(R.id.email);
        username = view.findViewById(R.id.username);
        website = view.findViewById(R.id.website);
        subscribeObservers();
    }
    private void subscribeObservers(){
        viewModel.getAuthenticatedUser().removeObservers(getViewLifecycleOwner());
        viewModel.getAuthenticatedUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource !=null){
                    switch (userAuthResource.status){
                        case AUTHENTICATED:{
                            setUserDetails(userAuthResource.data);
                            break;
                        }
                        case ERROR:{
                            setErrorDetails(userAuthResource.message);
                            break;
                        }
                    }
                }
            }

            private void setErrorDetails(String message) {
                email.setText(message);
                username.setText("Error");
                website.setText("Error");

            }

            private void setUserDetails(User data) {
                email.setText(data.getEmail());
                username.setText(data.getUsername());
                website.setText(data.getWebsite());

            }
        });
    }

}
