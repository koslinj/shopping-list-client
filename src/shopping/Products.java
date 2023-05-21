package shopping;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Products {
    public Map<String, ArrayList<Product>> categories = new HashMap<>();

    public Map<String, ArrayList<Product>> getCategories() {
        return categories;
    }

    public void saveToFile(){
        if(categories.size()>0){
            try {
                Socket socket = new Socket("localhost", 9000);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                StringBuilder shoppingListBuilder = new StringBuilder();
                for (String cat: categories.keySet()) {
                    shoppingListBuilder.append("*").append(cat).append("\n");
                    for (Product prod: categories.get(cat)) {
                        shoppingListBuilder.append(prod.getName()).append("\n");
                        shoppingListBuilder.append(prod.getType()).append("\n");
                        shoppingListBuilder.append(prod.getCount()).append("\n");
                    }
                }
                out.println(shoppingListBuilder);
                out.close();
                socket.close();
                System.out.println("Zapis do pliku przebiegl pomyslnie!");
            }catch (IOException e){
                System.out.println("Wystąpił błąd związany z plikiem!");
            }
        }
        else{
            System.out.println("Lista jest pusta!");
        }
    }

    public void updateListOfProducts(){
        if(categories.size()>0){
            try {
                FileWriter myWriter = new FileWriter("list_of_products.txt");
                for (String cat: categories.keySet()) {
                    myWriter.write("*"+cat+"\n");
                    for (Product prod: categories.get(cat)) {
                        myWriter.write(prod.getName()+"\n");
                        myWriter.write(prod.getType()+"\n");
                    }
                }
                myWriter.close();
                System.out.println("Zapis do pliku przebiegl pomyslnie!");
            }catch (IOException e){
                System.out.println("Wystąpił błąd związany z plikiem!");
            }
        }
        else{
            System.out.println("Lista jest pusta!");
        }
    }

    public void deleteAll(){
        categories.clear();
    }

    public void deleteCategory(String c){
        categories.remove(c);
    }

    public void deleteProduct(String c,Product p){
        categories.get(c).remove(p);
        if(categories.get(c).size()==0){
            categories.remove(c);
        }
    }

    public void addProduct(String category, Product prod, boolean print){
        if(!categories.containsKey(category)){
            categories.put(category, new ArrayList<>());
        }
        for(Product p : categories.get(category)){
            if(p.getName().equals(prod.getName())){
                categories.get(category).remove(p);
                break;
            }
        }
        categories.get(category).add(prod);
        if(print) System.out.println("Dodano do listy: " + prod.getName());
    }

}
