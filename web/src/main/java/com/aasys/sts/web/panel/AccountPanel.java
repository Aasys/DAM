package com.aasys.sts.web.panel;

import com.aasys.sts.shared.core.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.MaterialTextBox;

/**
 * Created by kb on 2/26/17.
 */
public class AccountPanel extends Composite {
    private static AccountPanelUiBinder uiBinder = GWT.create(AccountPanelUiBinder.class);

    interface AccountPanelUiBinder extends UiBinder<Widget, AccountPanel> {
    }

    @UiField
    MaterialRow mRow;

    @UiField
    MaterialTextBox UI_txtName;
    @UiField
    MaterialTextBox UI_txtAddress;
    @UiField
    MaterialTextBox UI_txtEmail;
    @UiField
    MaterialTextBox UI_txtPassword;
    @UiField
    MaterialTextBox UI_txtPhoneNum;

    private final User user;

    public AccountPanel(User _user) {
        user = _user;
        initWidget(uiBinder.createAndBindUi(this));
        UI_txtAddress.setText(user.getAddress());
        UI_txtEmail.setText(user.getEmail());
        UI_txtName.setText(user.getName());
        UI_txtPassword.setText("********");
        UI_txtPhoneNum.setText(user.getPhonenum());
        //populate();
    }

    private void populate() {
    }

}
