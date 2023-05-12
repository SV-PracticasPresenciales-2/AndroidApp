package com.svalero.globalfeed.contract.user;

import com.svalero.globalfeed.domain.User;
import com.svalero.globalfeed.domain.dto.UserDTO;

public interface RegisterUserContract {
    interface Model{
        interface OnRegisterUserListener {
            void onRegisterUserSuccess(User user);
            void onRegisterUserError(String message);
        }
        void registerUser(UserDTO user, OnRegisterUserListener listener);
    }

    interface View {
        void showError(String error);
        void showMessage(String message);
    }

    interface Presenter {


        void registerUser(UserDTO user);
    }
}
