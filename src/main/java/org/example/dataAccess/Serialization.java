package org.example.dataAccess;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.bll.BaseProduct;
import org.example.bll.CompositeProduct;
import org.example.bll.MenuItem;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Serialization {
    private Set<BaseProduct> products;

    public Set<BaseProduct> getProducts() {
        return products;
    }

    public void setProducts(Set<BaseProduct> products) {
        this.products = products;
    }

    /**
     * Method that uses streams for deserializing the products from the .csv file
     * @return  Set<BaseProduct> - the BaseProducts created from deserializing
     */
    public Set<BaseProduct> readFromFile() {
        try {
            List<String[]> lines = Files.lines(Paths.get("C:\\PT\\pt2021_30422_bodea_razvan-marius_assignment_4\\products.csv"))
                    .filter(line -> !line.startsWith("Title"))
                    .map(line -> line.split(","))
                    .collect(Collectors.toList());

            products = lines.stream()
                    .distinct()
                    .map(a -> new BaseProduct(0,a[0], Double.parseDouble(a[1]), Integer.parseInt(a[2]), Integer.parseInt(a[3]),
                            Integer.parseInt(a[4]), Integer.parseInt(a[5]), Integer.parseInt(a[6])))
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
    /**
     * This method writes the information to a .json file for serializing
     * @param menuItems - The array of menu items that we want to serialize
     * @throws IOException -encountered an error
     */
    public void serializeMenu(ArrayList<MenuItem> menuItems) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        OutputStream outputStream = new FileOutputStream(new File("Menu.json"));
        JsonGenerator g = objectMapper.getFactory().createGenerator(outputStream);
        objectMapper.writeValue(g,menuItems);
        g.close();
    }
    /**
     * Method for deserializing the information from a file
     * @return  menuItems - Observable list of the items created from deserialization that are displayed in the TableView
     * @throws IOException - encountered an error
     */
    public ObservableList<BaseProduct> deSerializeMenu() throws IOException {
        ObservableList<BaseProduct> menuItems = FXCollections.observableArrayList();
        BaseProduct[] arrayMenuItems;
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = new FileInputStream("Menu.json");

        arrayMenuItems = objectMapper.readValue(inputStream,BaseProduct[].class);
        menuItems.addAll(arrayMenuItems);
        return menuItems;
    }

    /**
     * This method writes the information to a .json file for serializing
     * @param menuItems - The array of menu items that we want to serialize
     * @throws IOException -encountered an error
     */
    public void serializeCompositeProducts(ArrayList<CompositeProduct> menuItems) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        OutputStream outputStream = new FileOutputStream(new File("CompositeProducts.json"));
        JsonGenerator g = objectMapper.getFactory().createGenerator(outputStream);
        objectMapper.writeValue(g,menuItems);
        g.close();
    }

    /**
     * Method for deserializing the information from a file
     * @return  menuItems - Observable list of the items created from deserialization that are displayed in the TableView
     * @throws IOException - encountered an error
     */
    public ObservableList<CompositeProduct> deSerializeCompositeProducts() throws IOException {
        ObservableList<CompositeProduct> menuItems = FXCollections.observableArrayList();
        CompositeProduct[] arrayMenuItems;
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = new FileInputStream("CompositeProducts.json");

        arrayMenuItems = objectMapper.readValue(inputStream,CompositeProduct[].class);
        menuItems.addAll(arrayMenuItems);
        return menuItems;
    }

}