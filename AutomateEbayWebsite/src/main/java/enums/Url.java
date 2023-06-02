package enums;

public enum Url {
    BASEURL("https://www.ebay.com/");

    String url;

    Url(String url){
        this.url = url;
    }

    public String getURL() {
        return url;
    }
}
