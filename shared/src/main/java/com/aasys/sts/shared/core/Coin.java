package com.aasys.sts.shared.core;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Coin implements IsSerializable {

    public String id;
    public String coin_name;
    public String symbol;
    public int rank;
    public float price_usd;
    public float change_24;
    public float day_vol_usd;
    public float price_btc;
}
