package com.aasys.sts.web.panel;

import com.aasys.sts.shared.core.AssetCoin;
import com.aasys.sts.shared.core.Coin;
import com.aasys.sts.shared.util.StringUtil;
import com.aasys.sts.web.PastOrdersService;
import com.aasys.sts.web.PastOrdersServiceAsync;
import com.aasys.sts.web.SessionCache;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialTitle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aasys on 2/25/2017.
 */
public class PortfolioPanel extends Composite {
    private static ResturatnsPanelUiBinder uiBinder = GWT.create(ResturatnsPanelUiBinder.class);

    public void refresh() {
        populate();
    }

    interface ResturatnsPanelUiBinder extends UiBinder<Widget, PortfolioPanel> {
    }

    private final PastOrdersServiceAsync backendService = GWT.create(PastOrdersService.class);

    @UiField
    MaterialColumn mCol;

    @UiField
    MaterialTextBox txtSearch;

    @UiField
    MaterialTitle txtValue;
    public PortfolioPanel() {
        initWidget(uiBinder.createAndBindUi(this));
        populate();
        txtSearch.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent keyUpEvent) {
                if (keyUpEvent.getNativeKeyCode() == KeyCodes.KEY_ENTER || StringUtil.isEmptyOrNull(txtSearch.getText())) {
                    populate();
                }
            }
        });


    }

    AsyncCallback<List<Coin>> coins_cb = new AsyncCallback<List<Coin>>() {
        @Override
        public void onFailure(Throwable throwable) {

        }

        @Override
        public void onSuccess(List<Coin> coins) {
            coinInfos.clear();
            for (Coin c : coins) {
                coinInfos.put(c.symbol, c);
            }
            backendService.getAsset(SessionCache.getCurrentUser().getUserId(), asset_coins_cb);
        }
    };

    Map<String, Coin> coinInfos = new HashMap<>();

    AsyncCallback<List<AssetCoin>> asset_coins_cb = new AsyncCallback<List<AssetCoin>>() {
        @Override
        public void onFailure(Throwable throwable) {

        }

        @Override
        public void onSuccess(List<AssetCoin> assetCoins) {
            mCol.clear();
            int total = 0;
            float t_usd = 0.0f;
            float t_btc = 0.0f;
            for (AssetCoin assetCoin : assetCoins) {
                if (assetCoin.holding > 0.0) {
                    mCol.add(new PortfolioInfoPanel(coinInfos.get(assetCoin.coin_id), assetCoin));
                    t_usd += coinInfos.get(assetCoin.coin_id).price_usd * assetCoin.holding;
                    t_btc += coinInfos.get(assetCoin.coin_id).price_btc * assetCoin.holding;
                    total += 1;
                }
            }
            if (total == 0)
                txtValue.setTitle("No coins yet. Add some from market");
            else {

                com.google.gwt.i18n.client.NumberFormat nfd = com.google.gwt.i18n.client.NumberFormat.getFormat("#.####");
                com.google.gwt.i18n.client.NumberFormat nfc = com.google.gwt.i18n.client.NumberFormat.getFormat("###,###,###,###.##");

                txtValue.setTitle("$" + nfc.format(t_usd) + "    =   " + nfd.format(t_btc) + " BTC");

            }
        }
    };

    private void populate() {
        try {
            if (StringUtil.isEmptyOrNull(txtSearch.getText())) {
                backendService.getCoins(coins_cb);
            } else {
//                backendService.getCoins(txtSearch.getText(), callback);
            }
        } catch (Exception e) {
//            /e.printStackTrace();
        }
    }


}
