package com.svalero.globalfeed.contract.user;

import com.svalero.globalfeed.domain.User;

public interface UserDetailsContract {
    interface Model {
        interface OnUserDetailListener {
            void onUserDetailSuccess(User user);

            void onUserDetailError(String message);
        }

        void getUserDetail(String username, OnUserDetailListener listener);
    }

    interface View {
        void showError(String error);

        void showUser(User user);
    }

    interface Presenter {
        void onUserDetailSuccess(User user);

        void getUser(String username);
    }
}
