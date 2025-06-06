package com.comarch.szkolenie.sklep.database;

import com.comarch.szkolenie.sklep.model.Product;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProductRepository implements IProductRepository{
    private final Map<Integer, Product> products;
    private final String DB_FILE = "products.txt";

    private ProductRepository(){
        this.products = new HashMap<>();
        this.products.put(0001, new Product(0001, "Jogurt owocowy",
                "Jogurt owocowy 250g, smak truskawkowy", 2.50,
                LocalDate.of(2026,1,25), 12));
        this.products.put(0002, new Product(0002, "Jogurt owocowy",
                "Jogurt owocowy 250g, smak malinowy", 2.50,
                LocalDate.of(2025,9,20), 10));
        this.products.put(0003, new Product(0003, "Jogurt naturalny mały",
                "Jogurt naturalny 250g, mały", 2.20,
                LocalDate.of(2025,7,19), 8));
        this.products.put(0004, new Product(0004, "Jogurt naturalny duży",
                "Jogurt naturalny 500g, duży", 3.50,
                LocalDate.of(2025,7,20), 9));
        this.products.put(0005, new Product(0005, "Jajka opakowanie",
                "Jajka z wolnego wybiegu opakowanie 10 szt.", 12.50,
                LocalDate.of(2025,5,30), 10));
        this.products.put(0006, new Product(0006, "Chleb żytni",
                "Chleb żytni pełnoziarnisty, 200g", 4.50,
                LocalDate.of(2025,5,9), 10));
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

    public void persist(){
        try{
        BufferedWriter writer = new BufferedWriter((new FileWriter(DB_FILE)));
            for(Product product : getProducts()) {
            writer.write(product.convertToDatabaseLine());
            writer.newLine();
            }
        } catch (IOException e){
            System.out.println("Nie działa zapisywanie!");
            }
    }
}
