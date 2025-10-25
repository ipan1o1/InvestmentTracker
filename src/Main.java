public class Main {
    
    public static void main(String[] args) {
    Crypto btc = new Crypto("Bitcoin", "BTC", 111547.95f, "2.22T");
    System.out.println(btc);
    Stock sp500 = new Stock("Standard and Poor's 500", "S&P500" , 6791.69f, "N/A");
    System.out.println(sp500);
    btc.setPrice(115678.93f);
    System.out.println(btc);
    System.out.println(System.getProperty("user.dir"));
    }
}
