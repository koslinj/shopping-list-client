import shopping.Product;
import shopping.Products;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class MyFrame extends JFrame {

    private Products shoppingProducts = new Products();
    private Products list = new Products();
    MyFrame(){
        try {
            File myObj = new File("list_of_products.txt");
            Scanner myReader = new Scanner(myObj);
            String actualCategory="";
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.charAt(0)=='*'){
                    actualCategory = data.substring(1);
                }
                else{
                    String type = myReader.nextLine();
                    Product prod = new Product(data,type,0);
                    shoppingProducts.addProduct(actualCategory,prod,false);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Wystąpił błąd związany z plikiem!");
            e.printStackTrace();
        }

        try {
            Socket socket = new Socket("localhost", 9000);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String actualCategory="";
            String data;
            while ((data = in.readLine()) != null) {
                if(data.length()==0) break;
                if(data.charAt(0)=='*'){
                    System.out.println(data);
                    actualCategory = data.substring(1);
                }
                else{
                    System.out.println(data);
                    String type = in.readLine();
                    System.out.println(type);
                    String countString = in.readLine();
                    System.out.println(countString);
                    double count = Double.parseDouble(countString);
                    Product prod = new Product(data,type,count);
                    list.addProduct(actualCategory,prod,false);
                }
            }
            in.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Wystąpił błąd związany z plikiem!");
            System.out.println(e.getMessage());
            System.exit(1);
            e.printStackTrace();
        }

        HomePanel homePanel = new HomePanel(this);
        homePanel.setVisible(true);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(Color.BLACK);
        this.setSize(800,660);
        this.setTitle("Lista Zakupów");
        //this.setResizable(false);
        this.add(homePanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public Products getShoppingProducts(){
        return shoppingProducts;
    }

    public Products getList() {
        return list;
    }
}
