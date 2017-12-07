package com.aasys.sts.web;

import com.aasys.sts.shared.core.User;
import com.aasys.sts.web.panel.MaterialLogin;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.ui.MaterialContainer;
import gwt.material.design.client.ui.MaterialNavBar;

/**
 * Created by aasys on 2/25/2017.
 */
public class SessionCache {
    public static User user;
    public static MaterialContainer UI_canvas;
    public static MaterialNavBar UI_navBar;

    public static void setToCanvas(Widget widget, String title) {
        if (UI_canvas != null) {
            UI_canvas.clear();
            UI_canvas.addWidget(widget);
        }
        if (UI_navBar != null) {
            UI_navBar.setText(title);
        }
    }

    public static void logout() {
        RootPanel.get().clear();
        RootPanel.get().add(new MaterialLogin());
        user = null;
        UI_canvas = null;
        UI_navBar = null;
    }

    public static User getCurrentUser() {
        return user;
    }

}
