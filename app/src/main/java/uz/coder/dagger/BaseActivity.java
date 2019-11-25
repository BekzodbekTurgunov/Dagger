package uz.coder.dagger;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import uz.coder.dagger.models.User;
import uz.coder.dagger.ui.auth.AuthActivity;
import uz.coder.dagger.ui.auth.AuthResource;

public abstract class BaseActivity extends DaggerAppCompatActivity {
    private static final String TAG = "BaseActivity";

    @Inject
    public SessionManager sessionManager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeObserves();
    }
    private void subscribeObserves(){
        sessionManager.getAuthUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource !=null){
                    switch (userAuthResource.status){
                        case LOADING:{

                            break;
                        }
                        case AUTHENTICATED:{
                            Log.d(TAG, "onChanged: LOGIN SUCCES " + userAuthResource.data.getEmail());
                            break;
                        }
                        case ERROR:{

                            Toast.makeText(BaseActivity.this, userAuthResource.message +"\n did you enter a" +
                                    " number between 1 and 10? ", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case NOT_AUTHENTICATED:{
                                navLoginScreen();
                            break;
                        }
                    }
                }
            }
        });
    }
    private void navLoginScreen(){
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }
}
