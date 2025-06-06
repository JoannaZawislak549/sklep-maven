package com.comarch.szkolenie.sklep.database;

import com.comarch.szkolenie.sklep.model.Product;

import java.util.Collection;

public interface IProductRepository {

    boolean checkIfAvailable(int id, int wantedQuantity);
    double prizeCalculator(int id, int quantity);
    void addProductQuantity(int id, int quantity);
    Collection<Product> getProducts();
    void persist();
}
