package com.aasys.sts.web;

import com.aasys.sts.web.panel.HomePanel;
import com.aasys.sts.web.panel.MaterialLogin;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class parent implements EntryPoint {

  public void onModuleLoad() {
    RootPanel.get().add(new MaterialLogin());
  }
}
