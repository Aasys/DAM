package com.aasys.sts.web.panel;

import com.aasys.sts.shared.core.AssetCoin;
import com.aasys.sts.shared.core.Coin;
import com.aasys.sts.web.PastOrdersService;
import com.aasys.sts.web.PastOrdersServiceAsync;
import com.aasys.sts.web.SessionCache;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.ui.*;

/**
 * Created by aasys on 2/25/2017.
 */
public class CoinInfoPanel extends Composite {
    private static PastOrdersInfoPanelUiBinder uiBinder = GWT.create(PastOrdersInfoPanelUiBinder.class);

    interface PastOrdersInfoPanelUiBinder extends UiBinder<Widget, CoinInfoPanel> {
    }

    private final PastOrdersServiceAsync backendService = GWT.create(PastOrdersService.class);

    @UiField
    MaterialCard resCard;
    @UiField
    MaterialLabel txtBtc;
    @UiField
    MaterialLabel txtUsd;
    @UiField
    MaterialLabel txtVol;

    private final Coin coin;

    public CoinInfoPanel(final Coin coin) {
        initWidget(uiBinder.createAndBindUi(this));
        this.coin = coin;

        resCard.setTitle(coin.rank + ". " + coin.coin_name + " (" + coin.symbol + ")");
        resCard.setType("image");
        Image img = new Image();
        img.setUrl("/assets/" + coin.symbol.toLowerCase() + ".svg");

        DOM.setElementAttribute(img.getElement(), "style", "height: 50px;float: left;");
        resCard.addContent(img);

        MaterialTitle change_title = new MaterialTitle();
        NumberFormat nf = NumberFormat.getFormat(".##");
        String change = String.valueOf(nf.format(coin.change_24)) + "%";

        if (coin.change_24 > 0.0) {
            change = "+" + change;
            change_title.setColor("green");
        } else
            change_title.setColor("red");
        change_title.setTitle(change);
        DOM.setElementAttribute(change_title.getElement(), "style", "right: 25px;top: -25px;position: absolute;");

        resCard.addContent(change_title);

        MaterialButton addButton = new MaterialButton();
        addButton.setType("floating");
        addButton.setWaves("light");
        addButton.setIcon("mdi-content-add");
        addButton.setColor("teal");
        DOM.setElementAttribute(addButton.getElement(), "style", "bottom: 25px;right: 25px;position: absolute;");

        resCard.addWidget(addButton);

        NumberFormat nfc = NumberFormat.getFormat("###,###,###,###.##");
        txtBtc.setText("$" + String.valueOf(NumberFormat.getFormat("#.#########").format(coin.price_btc)));
        txtUsd.setText("$" + String.valueOf(nfc.format(coin.price_usd)));
        txtVol.setText("$" + String.valueOf(nfc.format(coin.day_vol_usd)));


        final MaterialCard materialCard = new MaterialCard();
        MaterialTitle title = new MaterialTitle("Add " + coin.coin_name + " (" + coin.symbol + ")");
        final MaterialTextBox textBox = new MaterialTextBox();
        textBox.setType("text");
        textBox.setPlaceholder("amount");
        MaterialButton materialButton = new MaterialButton("ADD", "teal", "light");

        materialCard.addContent(title);
        materialCard.addWidget(textBox);
        materialCard.addWidget(materialButton);
        addButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                MaterialModal.showModal(materialCard, MaterialModal.TYPE.BOTTOM_SHEET);
            }
        });

        materialButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                AssetCoin assetCoin = new AssetCoin();
                assetCoin.coin_id = coin.symbol;
                assetCoin.holding = Float.parseFloat(textBox.getValue());
                backendService.addAsset(SessionCache.getCurrentUser().getUserId(), assetCoin, new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        MaterialToast.alert("Error occurred");
                    }

                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        MaterialToast.alert("Added " + coin.coin_name);
                        MaterialModal.closeModal();
                    }
                });
            }
        });
    }
}

