package com.svalero.globalfeed.presenter.post;

import com.svalero.globalfeed.contract.post.PostListContract;
import com.svalero.globalfeed.domain.Post;
import com.svalero.globalfeed.model.post.PostListModel;

import java.util.List;


public class PostListPresenter implements PostListContract.Presenter, PostListContract.Model.OnLoadPostListener {
    private PostListModel model;
    private PostListContract.View view;

    public PostListPresenter(PostListContract.View view) {
        this.view = view;
        this.model = new PostListModel();
    }

    @Override
    public void loadAllPosts() {
        model.loadAllPosts(this);
    }

    @Override
    public void loadAllPostsByUser(String username) {
        model.loadAllPostsByUser(username, this);
    }

    @Override
    public void onLoadPostsSuccess(List<Post> postList) {
        view.showPosts(postList);
    }

    @Override
    public void onLoadPostsError(String message) {
        view.showMessage(message);
    }
}
