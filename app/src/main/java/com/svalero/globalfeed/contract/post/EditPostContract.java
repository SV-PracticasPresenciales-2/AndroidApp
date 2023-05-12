package com.svalero.globalfeed.contract.post;

import com.svalero.globalfeed.domain.Post;

public interface EditPostContract {
    interface Model {
        interface OnEditPostListener {
            void onEditPostSuccess(Post post);
            void onEditPostError(String message);
        }
        void editPost(String token, Post post, OnEditPostListener listener);
    }
    interface View {
        void showError(String message);
        void showMessage(String message);
    }

    interface Presenter {
        void editPost(Post post, String token);
    }

}
