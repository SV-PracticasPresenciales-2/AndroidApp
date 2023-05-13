package com.svalero.globalfeed.presenter.user;

import com.svalero.globalfeed.contract.user.UserDetailsContract;
import com.svalero.globalfeed.domain.User;
import com.svalero.globalfeed.model.user.UserDetailModel;
import com.svalero.globalfeed.view.MainActivity;
import com.svalero.globalfeed.view.UserDetailsView;


public class UserDetailsPresenter implements UserDetailsContract.Presenter, UserDetailsContract.Model.OnUserDetailListener {

    private UserDetailModel model;
    private UserDetailsContract.View view;


    public UserDetailsPresenter(MainActivity view) {
        model = new UserDetailModel();
        this.view = view;
    }

    @Override
    public void onUserDetailSuccess(User user) {
        view.showUser(user);
    }

    @Override
    public void onUserDetailError(String message) {
        view.showError(message);
    }

    @Override
    public void getUser(String username) {
        model.getUserDetail(username, this);
    }
}
