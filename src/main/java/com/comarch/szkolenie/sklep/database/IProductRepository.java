package com.comarch.szkolenie.sklep.database;

import com.comarch.szkolenie.sklep.model.Product;

import java.util.Collection;

public interface IProductRepository {

    public boolean checkIfAvailable(int id, int wantedQuantity);
    public double prizeCalculator(int id, int quantity);
    public void addProductQuantity(int id, int quantity);
    public Collection<Product> getProducts();
}
