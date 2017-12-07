package com.aasys.sts.server;

import com.aasys.sts.shared.core.AssetCoin;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StaticStore {
    private static Map<Integer, List<AssetCoin>> user_assets = new HashMap<>();

    public static List<AssetCoin> get_assets(int user_id) {
        List<AssetCoin> lst = user_assets.get(user_id);
        if (lst == null) {
            lst = new LinkedList<AssetCoin>();
            user_assets.put(user_id, lst);
        }
        return lst;
    }

    public static boolean updateAsset(int user_id, AssetCoin assetCoin) {
        List<AssetCoin> lst = get_assets(user_id);
        AssetCoin ast = null;
        for (AssetCoin ac : lst) {
            if (assetCoin.coin_id.equals(ac.coin_id)) {
                ast = ac;
                break;
            }
        }
        if (ast != null)
            lst.remove(ast);
        lst.add(assetCoin);
        return true;
    }


    public static boolean addAsset(int userid, AssetCoin assetCoin) {
        List<AssetCoin> lst = get_assets(userid);
        AssetCoin ast = null;
        for (AssetCoin ac : lst) {
            if (assetCoin.coin_id.equals(ac.coin_id)) {
                ast = ac;
                break;
            }
        }
        if (ast == null)
            lst.add(assetCoin);
        else
            ast.holding += assetCoin.holding;
        return true;

    }
}
