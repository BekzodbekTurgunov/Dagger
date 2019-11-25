package uz.coder.dagger.ui.main.posts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import uz.coder.dagger.R;
import uz.coder.dagger.models.Post;
import uz.coder.dagger.ui.main.Resource;
import uz.coder.dagger.util.VerticalSpacingItemDecoration;
import uz.coder.dagger.viewmodels.ViewModelProviderFactory;

public class PostsFragment extends DaggerFragment {
    private static final String TAG = "PostsFragment";
    //inject
    private PostsViewModel viewModel;
    private RecyclerView recyclerView;
    @Inject
     ViewModelProviderFactory providerFactory;
    @Inject
    PostRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(this,providerFactory).get(PostsViewModel.class);
        recyclerView = view.findViewById(R.id.recycler_view);
        subscribeObserve();
        initRecyclerView();

    }
    private void subscribeObserve(){
        viewModel.observePosts().removeObservers(getViewLifecycleOwner());
        viewModel.observePosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource) {
                if (listResource != null){
                    switch (listResource.status){
                        case LOADING:{
                            Log.d(TAG, "onChanged: Loading ....");
                            break;

                        }
                        case SUCCESS:{
                            adapter.setPosts(listResource.data);
                            Log.d(TAG, "onChanged: get Posts");
                            break;
                        }
                        case ERROR:{
                            Log.d(TAG, "onChanged: Eror... "+ listResource.message);
                            break;
                        }
                    }
                }
            }
        });
    }
    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        VerticalSpacingItemDecoration spacingItemDecoration = new VerticalSpacingItemDecoration(1);
        recyclerView.addItemDecoration(spacingItemDecoration);
        recyclerView.setAdapter(adapter);
    }
}
