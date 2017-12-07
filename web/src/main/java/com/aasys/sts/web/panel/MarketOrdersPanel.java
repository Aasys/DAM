package com.aasys.sts.web.panel;

import com.aasys.sts.shared.core.Coin;
import com.aasys.sts.web.PastOrdersService;
import com.aasys.sts.web.PastOrdersServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.ui.*;

import java.util.List;

/**
 * Created by kb on 2/26/17.
 */
public class MarketOrdersPanel extends Composite {
    private static PastOrdersPanlUiBinder uiBinder = GWT.create(PastOrdersPanlUiBinder.class);

    interface PastOrdersPanlUiBinder extends UiBinder<Widget, MarketOrdersPanel> {
    }

    private final PastOrdersServiceAsync pastOrdersService = GWT.create(PastOrdersService.class);

    @UiField
    MaterialColumn mCol;

    @UiField
    MaterialTextBox txtSearch;

    @UiField
    MaterialTitle txtOrderTotal;


    public MarketOrdersPanel() {
        initWidget(uiBinder.createAndBindUi(this));
        populate();
    }

    private void populate() {
        AsyncCallback<List<Coin>> coins_cb = new AsyncCallback<List<Coin>>() {
            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onSuccess(List<Coin> coins) {
                mCol.clear();
                int total = 0;
                for (Coin coin : coins) {
                    mCol.add(new CoinInfoPanel(coin));
                }
            }
        };

        pastOrdersService.getCoins(coins_cb);
    }

}
