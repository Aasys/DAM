package com.aasys.sts.web;

import com.aasys.sts.shared.core.AssetCoin;
import com.aasys.sts.shared.core.Coin;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;


public interface PastOrdersServiceAsync {


    void getCoins(AsyncCallback<List<Coin>> async);

    void getAsset(int userid, AsyncCallback<List<AssetCoin>> async);

    void modifyAsset(int userid, AssetCoin assetCoin, AsyncCallback<Boolean> async);

    void addAsset(int userid, AssetCoin assetCoin, AsyncCallback<Boolean> async);
}
