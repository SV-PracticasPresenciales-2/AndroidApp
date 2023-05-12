package com.svalero.globalfeed.presenter.token;

import com.svalero.globalfeed.contract.user.RegisterUserContract;
import com.svalero.globalfeed.domain.User;
import com.svalero.globalfeed.domain.dto.UserDTO;
import com.svalero.globalfeed.model.user.RegisterUserModel;
import com.svalero.globalfeed.view.AddUserView;


public class RegisterUserPresenter implements RegisterUserContract.Presenter, RegisterUserContract.Model.OnRegisterUserListener {

    private RegisterUserModel model;
    private AddUserView view;

    public RegisterUserPresenter(AddUserView view) {
        model = new RegisterUserModel();
        this.view = view;
    }

    @Override
    public void registerUser(UserDTO user) {
        model.registerUser(user, this);
    }

    @Override
    public void onRegisterUserSuccess(User user) {
        view.showMessage("El usero: " + user.getUsername() + " se ha añadido correctamente!");
    }

    @Override
    public void onRegisterUserError(String message) {
        view.showError(message);
    }


}
