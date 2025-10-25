public class Asset {
    private final String name;
    private String marketCap;
    private float price;

    public Asset(String n, float p, String mc){
        name = n;
        price = p;
        marketCap = mc;
    }
    
    public String getName(){ return this.name; }

    public float getPrice(){ return this.price; }

    public String getMarketCap(){ return this.marketCap; }

    public void setPrice(float p){
        price = p;
    }

    public void setMarketCap(String m){
        marketCap = m;
    }

}
