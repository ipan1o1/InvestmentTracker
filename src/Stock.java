import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Stock extends Asset {
    private String symbol;

    public Stock(String n, String s, float p, String mc){ //Name, Symbol, Price, MarketCap
        super(n, p, mc);
        symbol = s;

        String filePath = "data/stock_prices.csv";
        String content = symbol + "," + getPrice();

        try(FileWriter writer = new FileWriter(filePath)){
        writer.append(content);
        System.out.println("File written!");
        }
        catch(FileNotFoundException er){
            System.out.println("File not found!");
        }
        catch(IOException e){
            System.out.println("Could not write file!");
        }
    }

    @Override
    public String toString(){ return symbol +" Price: $" + getPrice() + ", Market Cap: $" + getMarketCap(); }
}
