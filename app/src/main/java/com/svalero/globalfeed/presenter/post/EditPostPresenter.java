package com.svalero.globalfeed.presenter.post;

import com.svalero.globalfeed.contract.post.EditPostContract;
import com.svalero.globalfeed.domain.Post;
import com.svalero.globalfeed.domain.dto.PostDTO;
import com.svalero.globalfeed.model.post.EditPostModel;
import com.svalero.globalfeed.view.AddPostView;


public class EditPostPresenter implements EditPostContract.Presenter, EditPostContract.Model.OnEditPostListener {
    private EditPostModel model;
    private AddPostView view;

    public EditPostPresenter(AddPostView view) {
        model = new EditPostModel();
        this.view = view;
    }

    @Override
    public void editPost(long postId, PostDTO post, String token) {
        model.editPost(token, postId, post, this);
    }

    @Override
    public void onEditPostSuccess(Post post) {
        view.showMessage("El posto: " + post.getMessage() + " se ha editado correctamente!");
    }

    @Override
    public void onEditPostError(String message) {
        view.showError(message);
    }
}
