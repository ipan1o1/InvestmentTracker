import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Account {

    private HashMap<String, Float> positions = new HashMap<>();

    String filePath = "docs/transactions.csv";
    String filePathPrices = "data/crypto_prices.csv";

    public void displayTransactions(){

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }

        }catch(FileNotFoundException e){
            System.out.println("File not found!\n");

        }catch(IOException er){
            System.out.println("Could not read file!\n");
        }

    }

    public void displayPositions(){
        positions.clear();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
        String line;
        while ((line = reader.readLine()) != null){
            String[] parts = line.split(",");
            String transaction = parts[0].trim();
            String asset = parts[1].trim();
            float value = Float.parseFloat(parts[2].trim());

            float updatedPosition = positions.getOrDefault(asset, 0.0f);

            if (transaction.equals("BUY")){
                updatedPosition += value;
            }else if (transaction.equals("SELL")){
                updatedPosition -= value;
            }

            positions.put(asset, updatedPosition);
        }

        }catch(FileNotFoundException e){
            System.out.println("File not found!\n");

        }catch(IOException er){
            System.out.println("Could not read file!\n");
        }

        System.out.println("\nYour Portfolio:");
        for (Map.Entry<String, Float> entry: positions.entrySet()){
            System.out.println(entry.getKey() +": " +entry.getValue());
        }
        System.out.println("\n");
    }

    public void displayNetWorth(){

        float sum = 0f;

        try(BufferedReader reader = new BufferedReader(new FileReader(filePathPrices))){
        String line;
        while ((line = reader.readLine()) != null){
            String[] parts = line.split(",");
            float price = Float.parseFloat(parts[1].trim());
            HashMap<String, Float> convertedPositions = new HashMap<>(positions);
            for (Float value : convertedPositions.values()){
                value *= price;
                sum += value;
            }

        }
        System.out.println("Your net worth is: " + sum);

        }catch(FileNotFoundException e){
            System.out.println("File not found!\n");

        }catch(IOException er){
            System.out.println("Could not read file!\n");
        }
        
        

    }

    public void BUY(){
        System.out.println("Please enter the symbol of the asset you would like to purchase:");
        Scanner scn = new Scanner(System.in);
        String asset = scn.next();
        System.out.println("Please enter how many shares you would like to purchase:");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        String content = "BUY,"+asset + "," + amount+"\n";

        try(FileWriter writer = new FileWriter(filePath, true)){
        writer.append(content);
        }
        catch(FileNotFoundException er){
            System.out.println("File not found!");
        }
        catch(IOException e){
            System.out.println("Could not write file!");
        }
    }

    public void SELL(){
        System.out.println("Please enter the symbol of the asset you would like to liquidate:");
        Scanner scn = new Scanner(System.in);
        String asset = scn.next();
        System.out.println("Please enter how many shares you would like to sell:");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        String content = "SELL,"+asset + "," + amount+"\n";

        try(FileWriter writer = new FileWriter(filePath, true)){
        writer.append(content);
        }
        catch(FileNotFoundException er){
            System.out.println("File not found!");
        }
        catch(IOException e){
            System.out.println("Could not write file!");
        }
    }

    public static void main(String[] args) {
        Account a = new Account();
        a.BUY();
        a.SELL();
        a.BUY();
        a.BUY();
        a.displayTransactions();
        a.displayPositions();
        a.displayNetWorth();
    }
}
