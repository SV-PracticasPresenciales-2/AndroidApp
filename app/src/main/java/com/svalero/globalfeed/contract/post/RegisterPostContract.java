package com.svalero.globalfeed.contract.post;

import com.svalero.globalfeed.domain.Post;
import com.svalero.globalfeed.domain.dto.PostDTO;

public interface RegisterPostContract {
    interface Model{
        interface OnRegisterPostListener {
            void onRegisterPostSuccess(Post post);
            void onRegisterPostError(String message);
        }
        void registerPost(String token, PostDTO post, OnRegisterPostListener listener);
    }

    interface View {
        void showError(String error);
        void showMessage(String message);
    }

    interface Presenter {

        void registerPost(PostDTO post, String token);
    }
}
