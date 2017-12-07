package com.aasys.sts.server;

import com.aasys.sts.server.postgres.DbColumns;
import com.aasys.sts.server.postgres.PostgreSQLJDBC;
import com.aasys.sts.shared.core.AssetCoin;
import com.aasys.sts.shared.core.Coin;
import com.aasys.sts.shared.core.User;
import com.aasys.sts.web.PastOrdersService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("serial")
public class PastOrdersImpl extends RemoteServiceServlet implements PastOrdersService {

    private static final String PASTORDERS_QUERY_TEST = "SELECT invoices.tid,invoices.decription,invoices.amount,invoices.invdate, restaurants.name " +
            "FROM invoices " +
            "LEFT OUTER JOIN restaurants " +
            "ON invoices.rid = restaurants.rid " +
            "where invoices.userid =1;";

    private static final String PASTORDERS_QUERY = "SELECT invoices.tid,invoices.decription,invoices.amount,invoices.invdate, restaurants.name " +
            "FROM invoices " +
            "LEFT OUTER JOIN restaurants " +
            "ON invoices.rid = restaurants.rid " +
            "where invoices.userid =$1 ORDER BY invoices.invdate DESC;";

    private static final String USER_PAYMENTS = "select * From payments WHERE userid=$1;";

    private static final String INSERT_INVOICE = "INSERT INTO invoices (tid, invdate, decription, amount, cardnum, userid, rid) VALUES ($1, '$2', '$3', $4, '$5', $6, $7);";
    private static final String MAX_TID = "select max(invoices.tid) From invoices;";

    @Override
    public List<Coin> getCoins() throws Exception {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(UriBuilder.fromUri("https://api.coinmarketcap.com/v1/ticker/").build());
        // getting JSON data
        String out = service.accept(MediaType.APPLICATION_JSON).get(String.class);

        JsonParser jsonParser = new JsonParser();
        JsonArray array = jsonParser.parse(out).getAsJsonArray();
        List<Coin> coins = new LinkedList<>();
        for (int i = 0; i < array.size(); i++) {
            JsonElement element = array.get(i);
            Coin coin = new Coin();
            coin.id = ((JsonObject) element).get("id").getAsString();
            coin.coin_name = ((JsonObject) element).get("name").getAsString();
            coin.symbol = ((JsonObject) element).get("symbol").getAsString();
            coin.rank = ((JsonObject) element).get("rank").getAsInt();
            coin.price_usd = ((JsonObject) element).get("price_usd").getAsFloat();
            coin.day_vol_usd = ((JsonObject) element).get("24h_volume_usd").getAsFloat();
            coin.price_btc = ((JsonObject) element).get("price_btc").getAsFloat();
            coin.change_24 = ((JsonObject) element).get("percent_change_24h").getAsFloat();
            coins.add(coin);
        }
        return coins;
    }

    @Override
    public List<AssetCoin> getAsset(int userid) throws Exception {
        return StaticStore.get_assets(userid);
    }

    @Override
    public boolean modifyAsset(int userid, AssetCoin assetCoin) throws Exception {
        return StaticStore.updateAsset(userid, assetCoin);
    }

    @Override
    public boolean addAsset(int userid, AssetCoin assetCoin) {
        return StaticStore.addAsset(userid, assetCoin);
    }
}
