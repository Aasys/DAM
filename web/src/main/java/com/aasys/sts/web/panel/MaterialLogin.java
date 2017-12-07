package com.aasys.sts.web.panel;

import com.aasys.sts.shared.LoginUser;
import com.aasys.sts.shared.core.User;
import com.aasys.sts.shared.util.StringUtil;
import com.aasys.sts.web.LoginService;
import com.aasys.sts.web.LoginServiceAsync;
import com.aasys.sts.web.SessionCache;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialSearch;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

public class MaterialLogin extends Composite {

    private static MaterialLoginUiBinder uiBinder = GWT.create(MaterialLoginUiBinder.class);

    interface MaterialLoginUiBinder extends UiBinder<Widget, MaterialLogin> {
    }

    public MaterialLogin() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiField
    MaterialTextBox UI_txtEmail;

    @UiField
    MaterialTextBox UI_txtPassword;

    @UiField
    MaterialLabel UI_lblError;

    @UiHandler("UI_btnLogin")
    void onLogin(ClickEvent e) {
        if (!prevalidate())
            return;
        LoginUser loginUser = new LoginUser(UI_txtEmail.getText(), UI_txtPassword.getText());
        //loginUser = new LoginUser("joedrexel@drexel.net", "default");
        _loginService.loginServer(loginUser, new AsyncCallback<User>() {
            @Override
            public void onFailure(Throwable throwable) {
                toast(throwable.getMessage());
//                UI_lblError.setText(throwable.getMessage());
//                UI_lblError.setTextColor("red");
//                UI_lblError.setVisible(true);
            }

            @Override
            public void onSuccess(User user) {
                removeFromParent();
                RootPanel.get().clear();
                RootPanel.get().add(new HomePanel(user));
                SessionCache.user = user;
            }
        });

    }

    //TODO: add Resize event
    public void onResize(ResizeEvent event) {

        int height = RootPanel.getBodyElement().getOffsetHeight();
        //getElement().getStyle().setPaddingTop((int)((height)/6), Style.Unit.PX);
    }

    // my panel is not initially visible
    @Override
    public void onAttach() {
        super.onAttach();
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
                onResize(null);
            }
        });
    }

    private final LoginServiceAsync _loginService = GWT.create(LoginService.class);

    private void toast(String message) {
        MaterialToast.alert(message, "rounded");
    }

    private boolean prevalidate() {
        if (StringUtil.isEmptyOrNull(UI_txtEmail.getText()) || StringUtil.isEmptyOrNull(UI_txtPassword.getText())) {
            toast("Enter email and password");
            return false;
        }
        return true;
    }


}
