package com.svalero.globalfeed.contract.post;

public interface DeletePostContract {
    interface Model {
        interface OnDeletePostListener {
            void onDeletePostSuccess(String message);
            void onDeletePostError(String error);
        }
        void deletePost(String token, long id, OnDeletePostListener listener);
    }

    interface View {
        void showError(String message);
        void showMessage(String message);
    }

    interface Presenter {
        void deletePost(long id, String token);
    }
}
