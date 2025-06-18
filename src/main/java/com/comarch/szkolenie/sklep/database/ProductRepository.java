package com.comarch.szkolenie.sklep.database;

import com.comarch.szkolenie.sklep.model.Product;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

@Component
public class ProductRepository implements IProductRepository{
    private final Map<Integer, Product> products;
    private final String DB_FILE = "database.txt";

    private ProductRepository(){
        this.products = new HashMap<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(DB_FILE))) {
            String line;
            while((line = reader.readLine()) != null && !line.isEmpty()) {
                if (line.startsWith("PRODUCT")){
                    String[] parts = line.split(";");
                    Product product = new Product(Integer.parseInt(parts[1]), parts[2], parts[3],
                            Double.parseDouble(parts[4]), LocalDate.parse(parts[5]),
                            Integer.parseInt(parts[6]));
                    this.products.put(Integer.parseInt(parts[1]), product);
                }
            }
        } catch (IOException e){
            System.out.println("Plik nie działa!");
        }
    }

    @Override
    public Collection<Product> getProducts() {

        return this.products.values();
    }

    @Override
    public boolean checkIfAvailable(int id, int wantedQuantity){
        Product product = this.products.get(id);

        return wantedQuantity <= product.getQuantity();

    }

    @Override
    public double prizeCalculator(int id, int quantity){
        Product product = this.products.get(id);
        return (((double)quantity)*(product.getPrize()));
    }

    @Override
    public void addProductQuantity(int id, int quantity){
        Product product = this.products.get(id);
        int currQuantity = product.getQuantity();
        product.setQuantity(currQuantity + quantity);
    }

    @Override
    public void persist(){
        List<String> allLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(DB_FILE))){
            String line;
            while ((line = reader.readLine()) != null  && !line.isEmpty()){
                if (!line.startsWith("PRODUCT;")){
                    allLines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Nie udało się wczytać pliku");
        }

        for(Product product : getProducts()) {
            allLines.add(product.convertToDatabaseLine());
        }

        try (BufferedWriter writer = new BufferedWriter((new FileWriter(DB_FILE)))){
            for(String line : allLines) {
            writer.write(line);
            writer.newLine();
            }
        } catch (IOException e){
            System.out.println("Nie działa zapisywanie!");
            }
    }


}
