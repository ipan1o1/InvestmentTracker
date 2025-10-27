import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Account {

    String filePath = "docs/transactions.csv";

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
        a.displayTransactions();
        a.SELL();
        a.displayTransactions();
        a.BUY();
        a.BUY();
        a.displayTransactions();
    }
}
