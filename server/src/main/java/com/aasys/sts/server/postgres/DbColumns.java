package com.aasys.sts.server.postgres;

public class DbColumns {
    public static final String USERS = "users";
    public static final String USERS_ID = "userid";
    public static final String USERS_NAME = "name";
    public static final String USERS_ADDRESS = "address";
    public static final String USERS_PHONENUM = "phonenum";
    public static final String USERS_EMAIL = "rmail";
    public static final String USERS_PASSWD = "passwd";

    public static final String PAYMENTS_CARDNUM = "cardnum";
    public static final String PAYMENTS_EXPDATE = "expdate";
    public static final String PAYMENTS_CVCNUM = "cvcnum";
    public static final String PAYMENTS_ZIPCODE = "zipcode";
    public static final String PAYMENTS_USERID = "userid";

    public static final String CUISINES_NAME = "name";

    public static final String TASTES_FLAVOR = "flavor";

    public static final String RESTAURANTS_ID = "rid";
    public static final String RESTAURANTS_NAME = "name";
    public static final String RESTAURANTS_ADDRESS = "address";
    public static final String RESTAURANTS_PHONENUM = "phonenumber";

    public static final String RESTAURANTSCUISINE_ID = "id";
    public static final String RESTAURANTSCUISINE_NAME = "name";

    public static final String MENUS_MENUID = "menuid";
    public static final String MENUS_NAME = "name";
    public static final String MENUS_DESCRIPTION = "description";
    public static final String MENUS_PRICE = "price";
    public static final String MENUS_RID = "rid";

    public static final String MENUSTASTE_MENUID = "menuid";
    public static final String MENUSTASTE_FLAVOR = "flavor";

    public static final String RATINGS_RAID = "raid";
    public static final String RATINGS_STARS = "stars";
    public static final String RATINGS_COMMENTS = "comments";
    public static final String RATINGS_COMDATE = "comdate";
    public static final String RATINGSS_RID = "rid";

    public static final String INVOICE_TID = "tid";
    public static final String INVOICE_DATE = "invdate";
    public static final String INVOICE_DESCRIPTION = "decription";
    public static final String INVOICE_AMOUNT= "amount";
    public static final String INVOICES_CARDNUM = "cardnum";
    public static final String INVOICE_USERID = "userid";
    public static final String INVOICE_RID = "rid";


}
