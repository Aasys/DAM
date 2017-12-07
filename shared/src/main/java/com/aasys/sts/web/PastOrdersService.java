package com.aasys.sts.web;

import com.aasys.sts.shared.core.AssetCoin;
import com.aasys.sts.shared.core.Coin;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

/**
 * Created by aasys on 2/25/2017.
 */
@RemoteServiceRelativePath("pastorder")
public interface PastOrdersService extends RemoteService {
    List<Coin> getCoins() throws Exception;

    List<AssetCoin> getAsset(int userid) throws Exception;

    boolean modifyAsset(int userid, AssetCoin assetCoin) throws Exception;

    boolean addAsset(int userid, AssetCoin assetCoin);
}
