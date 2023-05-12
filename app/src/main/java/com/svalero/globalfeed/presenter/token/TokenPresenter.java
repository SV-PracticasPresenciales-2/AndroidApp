package com.svalero.globalfeed.presenter.token;

import com.svalero.globalfeed.contract.token.TokenContract;
import com.svalero.globalfeed.domain.PersonLogin;
import com.svalero.globalfeed.domain.Token;
import com.svalero.globalfeed.model.token.TokenModel;

public class TokenPresenter implements TokenContract.Presenter, TokenContract.Model.OnLoadTokenListener {

    private TokenModel model;
    private TokenContract.View view;

    public TokenPresenter(TokenContract.View view){
        this.view = view;
        this.model = new TokenModel();
    }
    @Override
    public void onLoadTokenSuccess(Token token) {
        view.showToken(token);
    }

    @Override
    public void onLoadTokenError(String message) {
        view.showError(message);
    }

    @Override
    public void loadToken(PersonLogin personLogin) {
        model.loadToken(personLogin, this);
    }
}
