package com.redhat.cloudnative;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import com.redhat.cloudnative.client.InventoryRestClient;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class CatalogService {

    // TODO: Inject RestClient Bean
    @Inject
    @RestClient
    InventoryRestClient inventoryRestClient;

    public Product getProductById(String itemId) {
        //TODO: Update the quantity for the product by calling the Inventory service
        Product product = Product.<Product>streamAll()
        .filter(p -> p.itemId.equals(itemId))
        .collect(Collectors.toList()).get(0);
        product.quantity = inventoryRestClient.getAvailability(itemId).get(0).quantity;
        return product;
    }

    public List<Product> getAll() {
        //TODO: Update the quantity for the products by calling the Inventory service
        List<Product> productList = Product.listAll();
        for ( Product p : productList ) {
            p.quantity = inventoryRestClient.getAvailability(p.itemId).get(0).quantity;
        }
        return productList; 
    }

}