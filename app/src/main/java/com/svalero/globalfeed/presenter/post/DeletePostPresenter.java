package com.svalero.globalfeed.presenter.post;

import com.svalero.globalfeed.adapter.PostAdapter;
import com.svalero.globalfeed.contract.post.DeletePostContract;
import com.svalero.globalfeed.model.post.DeletePostModel;

public class DeletePostPresenter implements DeletePostContract.Presenter, DeletePostContract.Model.OnDeletePostListener {

    private DeletePostModel model;
    private PostAdapter view;

    public DeletePostPresenter(PostAdapter view){
        model = new DeletePostModel();
        this.view = view;
    }
    @Override
    public void onDeletePostSuccess(String message) {
        view.showMessage(message);
    }

    @Override
    public void onDeletePostError(String error) {
        view.showError(error);
    }

    @Override
    public void deletePost(long id, String token) {
        model.deletePost(token, id, this);
    }
}
