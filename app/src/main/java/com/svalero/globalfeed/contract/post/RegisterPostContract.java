package com.svalero.globalfeed.contract.post;

import com.svalero.globalfeed.domain.Post;

public interface RegisterPostContract {
    interface Model{
        interface OnRegisterPostListener {
            void onRegisterPostSuccess(Post post);
            void onRegisterPostError(String message);
        }
        void registerPost(String token, Post post, OnRegisterPostListener listener);
    }

    interface View {
        void showError(String error);
        void showMessage(String message);
    }

    interface Presenter {
        void registerPost(Post post, String token);
    }
}
