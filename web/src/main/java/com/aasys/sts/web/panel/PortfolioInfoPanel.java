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

public class PortfolioInfoPanel extends Composite {
    private static InfoPanelUiBinder uiBinder = GWT.create(InfoPanelUiBinder.class);

    interface InfoPanelUiBinder extends UiBinder<Widget, PortfolioInfoPanel> {
    }
    private final PastOrdersServiceAsync backendService = GWT.create(PastOrdersService.class);

    @UiField
    MaterialCard resCard;
    @UiField
    MaterialLabel txtBtc;
    @UiField
    MaterialLabel txtAmount;

    private final Coin coin;

    public PortfolioInfoPanel(final Coin coin, AssetCoin assetCoin) {
        initWidget(uiBinder.createAndBindUi(this));
        this.coin = coin;

        resCard.setTitle(coin.coin_name + " (" + coin.symbol + ")");
        resCard.setType("image");
        Image img = new Image();
        img.setUrl("/assets/" + coin.symbol.toLowerCase() + ".svg");

        DOM.setElementAttribute(img.getElement(), "style", "height: 50px;float: left;");
        resCard.addContent(img);

        MaterialTitle change_title = new MaterialTitle();
        NumberFormat nf = NumberFormat.getFormat(".##");
        NumberFormat nfc = NumberFormat.getFormat("###,###,###,###.##");
        String usd = "$" + String.valueOf(nf.format(coin.price_usd*assetCoin.holding));
        change_title.setTitle(usd);
        DOM.setElementAttribute(change_title.getElement(), "style", "right: 25px;top: -25px;position: absolute;");

        resCard.addContent(change_title);

        MaterialButton addButton = new MaterialButton();
        addButton.setColor("red");
        addButton.setType("floating");
        addButton.setWaves("light");
        addButton.setIcon("mdi-content-remove");
        DOM.setElementAttribute(addButton.getElement(), "style", "bottom: 25px;right: 25px;position: absolute;");

        resCard.addWidget(addButton);
        NumberFormat nfd = NumberFormat.getFormat("#.#########");
        txtBtc.setText(String.valueOf(nfd.format(coin.price_btc * assetCoin.holding)));
        txtAmount.setText(String.valueOf(nfd.format(assetCoin.holding)));




        final MaterialCard materialCard = new MaterialCard();
        MaterialTitle title = new MaterialTitle("Withdraw " + coin.coin_name + " (" + coin.symbol + ")");
        final MaterialTextBox textBox = new MaterialTextBox();
        textBox.setType("number");
        textBox.setPlaceholder("amount");
        MaterialButton materialButton = new MaterialButton("WITHDRAW", "teal", "light");

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
                assetCoin.holding = Float.parseFloat(textBox.getValue()) * -1.0f;
                backendService.addAsset(SessionCache.getCurrentUser().getUserId(), assetCoin, new AsyncCallback<Boolean>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        MaterialToast.alert("Error occurred");
                    }

                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        MaterialToast.alert("Withdrew " + coin.coin_name);
                        MaterialModal.closeModal();
                    }
                });
            }
        });

    }
}

