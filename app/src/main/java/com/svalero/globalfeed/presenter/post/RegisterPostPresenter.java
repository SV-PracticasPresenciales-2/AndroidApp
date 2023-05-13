package com.svalero.globalfeed.presenter.post;

import com.svalero.globalfeed.contract.post.RegisterPostContract;
import com.svalero.globalfeed.domain.Post;
import com.svalero.globalfeed.domain.dto.PostDTO;
import com.svalero.globalfeed.model.post.RegisterPostModel;
import com.svalero.globalfeed.view.AddPostView;


public class RegisterPostPresenter implements RegisterPostContract.Presenter, RegisterPostContract.Model.OnRegisterPostListener{

    private RegisterPostModel model;
    private AddPostView view;

    public RegisterPostPresenter(AddPostView view){
        model = new RegisterPostModel();
        this.view = view;
    }

    @Override
    public void registerPost(PostDTO post, String token) {
        model.registerPost(token, post, this);
    }

    @Override
    public void onRegisterPostSuccess(Post post) {
        view.showMessage("El posto: " + post.getMessage() + " se ha añadido correctamente!");
    }

    @Override
    public void onRegisterPostError(String message) {
        view.showError(message);
    }
}
