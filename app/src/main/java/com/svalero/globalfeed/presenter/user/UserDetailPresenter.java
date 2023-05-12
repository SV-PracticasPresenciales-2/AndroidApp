package com.svalero.globalfeed.presenter.user;

import com.svalero.globalfeed.contract.user.UserDetailContract;
import com.svalero.globalfeed.domain.User;
import com.svalero.globalfeed.model.user.UserDetailModel;
import com.svalero.globalfeed.view.LoginView;
import com.svalero.globalfeed.view.MainActivity;


public class UserDetailPresenter implements UserDetailContract.Presenter, UserDetailContract.Model.OnUserDetailListener {

    private UserDetailModel model;
    private MainActivity view;

    public UserDetailPresenter(MainActivity view){
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
