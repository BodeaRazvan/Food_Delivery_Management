package org.example.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.App;
import org.example.bll.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.example.bll.MenuItem;
import org.example.dataAccess.Serialization;
import org.example.model.Client;
import org.example.model.Order;

public class Controller{
    DeliveryServiceProcessing deliveryServiceProcessing = new DeliveryServiceProcessing();
    Serialization serialization = new Serialization();
    //employee
    @FXML private TableView<Order> orderTableEmploy;
    @FXML private TableColumn<Order,Integer> orderIdEmploy,clientIdEmploy,hourEmploy,minuteEmploy,secondEmploy,dayEmploy,orderedListEmploy;
    //order
    @FXML private TableView<Order> tableViewReports;
    @FXML private TableColumn<Order,Integer> colOrderId,colClientId,colHour,colMnute,colSecond,colDay,colOrderedItems;
    @FXML private TableColumn<Order,String> colOrderedItemsReport;
    @FXML private TextField reportFieldDayMin,reportFieldDayMax,reportFieldHourMin,reportFieldHourMax,reportFieldMinuteMin,reportFieldMinuteMax,reportFieldSecondMin,reportFieldSecondMax;
    //admin
    @FXML private TableView<MenuItem> adminProductTable;
    @FXML private TableColumn<MenuItem,Integer> adminColumnId;
    @FXML private TableColumn<MenuItem,String> adminColumnTitle;
    @FXML private TableColumn<MenuItem,Double> adminColumnRating;
    @FXML private TableColumn<MenuItem,Integer> adminColumnCalories;
    @FXML private TableColumn<MenuItem,Integer> adminColumnProtein;
    @FXML private TableColumn<MenuItem,Integer> adminColumnFat;
    @FXML private TableColumn<MenuItem,Integer> adminColumnSodium;
    @FXML private TableColumn<MenuItem,Integer> adminColumnPrice;
    @FXML private TableView<CompositeProduct> adminProductTable1;
    @FXML private TableColumn<MenuItem,Integer> adminColumnId1;
    @FXML private TableColumn<MenuItem,String> adminColumnTitle1;
    @FXML private TableColumn<MenuItem,Double> adminColumnRating1;
    @FXML private TableColumn<MenuItem,Integer> adminColumnCalories1;
    @FXML private TableColumn<MenuItem,Integer> adminColumnProtein1;
    @FXML private TableColumn<MenuItem,Integer> adminColumnFat1;
    @FXML private TableColumn<MenuItem,Integer> adminColumnSodium1;
    @FXML private TableColumn<MenuItem,Integer> adminColumnPrice1;
    @FXML private TextField idFieldAdmin,titleFieldAdmin,ratingFieldAdmin,caloriesFieldAdmin,proteinFieldAdmin,fatFieldAdmin,sodiumFieldAdmin,priceFieldAdmin,errorLogAdmin;
    @FXML private TextField menuFieldAdmin;
    //Client
    @FXML private TableView<MenuItem> clientProductTable;
    @FXML private TableColumn<MenuItem,Integer> clientColumnId;
    @FXML private TableColumn<MenuItem,String> clientColumnTitle;
    @FXML private TableColumn<MenuItem,Double> clientColumnRating;
    @FXML private TableColumn<MenuItem,Integer> clientColumnCalories;
    @FXML private TableColumn<MenuItem,Integer> clientColumnProtein;
    @FXML private TableColumn<MenuItem,Integer> clientColumnFat;
    @FXML private TableColumn<MenuItem,Integer> clientColumnSodium;
    @FXML private TableColumn<MenuItem,Integer> clientColumnPrice;
    @FXML private TableView<MenuItem> clientProductTable1;
    @FXML private TableColumn<MenuItem,Integer> clientColumnId1;
    @FXML private TableColumn<MenuItem,String> clientColumnTitle1;
    @FXML private TableColumn<MenuItem,Double> clientColumnRating1;
    @FXML private TableColumn<MenuItem,Integer> clientColumnCalories1;
    @FXML private TableColumn<MenuItem,Integer> clientColumnProtein1;
    @FXML private TableColumn<MenuItem,Integer> clientColumnFat1;
    @FXML private TableColumn<MenuItem,Integer> clientColumnSodium1;
    @FXML private TableColumn<MenuItem,Integer> clientColumnPrice1;
    @FXML private TextField orderFieldClient,adminProductName;
    @FXML private TextField textFieldClientId,textFieldClientTitle,textFieldClientRating,textFieldClientCalories,textFieldClientProtein,textFieldClientFat,textFieldClientSodium,textFieldClientPrice;
    @FXML private TextArea textAreaOrder;
    @FXML private TextField errorFieldClient;

    @FXML
    private void goBackToAdmin() throws IOException {
        App.setRoot("AdminPage");
    }
    @FXML
    private void goBackToLogin() throws IOException{
        App.setRoot("LoginPage");
    }
    @FXML
    private void goToReportPage() throws IOException {
        App.setRoot("ReportsPage");
    }

    @FXML
    private void initializeHeaders(){
        initializeTableHeaders(adminColumnId, adminColumnTitle, adminColumnRating, adminColumnCalories, adminColumnProtein, adminColumnFat, adminColumnSodium, adminColumnPrice);
    }
    @FXML
    private void initializeHeaders1(){
        initializeTableHeaders(adminColumnId1, adminColumnTitle1, adminColumnRating1, adminColumnCalories1, adminColumnProtein1, adminColumnFat1, adminColumnSodium1, adminColumnPrice1);
    }
    @FXML
    private void refreshOrders() throws SQLException {
        OrderBll orderBll = new OrderBll();
        ObservableList<Order> oblist = FXCollections.observableArrayList();
        oblist.addAll(orderBll.initializeTablesOrders());
        tableViewReports.getColumns().setAll(orderBll.getOrderTableHeaders().getColumns());
        tableViewReports.getItems().setAll(oblist);
    }
    @FXML
    private void refreshOrdersEmploy() throws SQLException {
        OrderBll orderBll = new OrderBll();
        ObservableList<Order> observableList = FXCollections.observableArrayList();
        observableList.addAll(orderBll.initializeTablesOrders());
        orderTableEmploy.getColumns().setAll(orderBll.getOrderTableHeaders().getColumns());
        orderTableEmploy.getItems().setAll(observableList);
    }
    /**
     * Method for adding a new product in the table
     * This method is available only for the admin
     * @return  Integer - used for stopping if encountered a mistake/error
     */
    @FXML
    public int addProductAdmin(){
        errorLogAdmin.clear();
        if(titleFieldAdmin.getText().equals("") || ratingFieldAdmin.getText().equals("") || caloriesFieldAdmin.getText().equals("") || proteinFieldAdmin.getText().equals("") || fatFieldAdmin.getText().equals("") || sodiumFieldAdmin.getText().equals("")|| priceFieldAdmin.getText().equals("")){
            errorLogAdmin.setText("All fields except id must be completed");
            return 0;
        }
        try{
        if(Integer.parseInt(caloriesFieldAdmin.getText())<0 || Double.parseDouble(ratingFieldAdmin.getText())<0 || Integer.parseInt(proteinFieldAdmin.getText())<0 || Integer.parseInt(fatFieldAdmin.getText())<0 || Integer.parseInt(sodiumFieldAdmin.getText())<0 || Integer.parseInt(priceFieldAdmin.getText())<0 ){
            errorLogAdmin.setText("Rating, Calories, Protein, Fat, Sodium, Price must be numbers >0");
            return 0;
        }
        }catch (NumberFormatException e){
            errorLogAdmin.setText("Rating, Calories, Protein, Fat, Sodium, Price must be numbers >0");
            return 0;
        }
        adminProductTable.getItems().add(new BaseProduct(Math.max(adminProductTable.getItems().get(adminProductTable.getItems().size()-1).getId(),adminProductTable1.getItems().get(adminProductTable1.getItems().size()-1).getId())+1,
                titleFieldAdmin.getText(),Double.parseDouble(ratingFieldAdmin.getText()),Integer.parseInt(caloriesFieldAdmin.getText()),
                Integer.parseInt(proteinFieldAdmin.getText()),Integer.parseInt(fatFieldAdmin.getText()),Integer.parseInt(sodiumFieldAdmin.getText()),Integer.parseInt(priceFieldAdmin.getText())));
        errorLogAdmin.setText("Item was added successfully");
        return 0;
    }
    /**
     * Method for deleting a MenuItem from the list, available only for the admin
     */
    @FXML
    public int deleteProductAdmin(){
        errorLogAdmin.clear();
        try{
            Integer.parseInt(idFieldAdmin.getText());
        }catch (NumberFormatException e){
            errorLogAdmin.setText("Id must be a number !!");
            return 0;
        }
        if((Integer.parseInt(idFieldAdmin.getText()) < 0 || Integer.parseInt(idFieldAdmin.getText()) > adminProductTable.getItems().size()+adminProductTable1.getItems().size())){
            errorLogAdmin.setText("Id must be a number >0 and <= greatest id");
            return 0;
        }
        adminProductTable.getItems().removeIf(menuItem -> menuItem.getId() == Integer.parseInt(idFieldAdmin.getText()));
        adminProductTable1.getItems().removeIf(menuItem -> menuItem.getId() == Integer.parseInt(idFieldAdmin.getText()));
        errorLogAdmin.setText("Deleted product successfully");
        return 0;
    }
    /**
     * Method for modifying a product already existent in the menu, available only for the admin
     * This method finds the item with the specified id and updates the information in it's field with the given information
     */
    @FXML
    public int modifyProductAdmin(){
        errorLogAdmin.clear();
        try{
            if(Integer.parseInt(idFieldAdmin.getText())<0 || Integer.parseInt(idFieldAdmin.getText())>adminProductTable.getItems().size()+adminProductTable1.getItems().size()){
                errorLogAdmin.setText("Id must be a number >0 and <= greatest id");
                return 0;
            }
        }catch (NumberFormatException e){
            errorLogAdmin.setText("Id must be a number");
            return 0;
        }
        MenuItem menuItem = adminProductTable.getItems().get(Integer.parseInt(idFieldAdmin.getText())-1);
        menuItem.setId(Integer.parseInt(idFieldAdmin.getText())-1);
        if(!ratingFieldAdmin.getText().equals("")) menuItem.setRating(Double.parseDouble(ratingFieldAdmin.getText()));
        if(!titleFieldAdmin.getText().equals("")) menuItem.setTitle(titleFieldAdmin.getText());
        if(!caloriesFieldAdmin.getText().equals("")) menuItem.setCalories(Integer.parseInt(caloriesFieldAdmin.getText()));
        if(!proteinFieldAdmin.getText().equals("")) menuItem.setProtein(Integer.parseInt(proteinFieldAdmin.getText()));
        if(!fatFieldAdmin.getText().equals("")) menuItem.setFat(Integer.parseInt(fatFieldAdmin.getText()));
        if(!sodiumFieldAdmin.getText().equals("")) menuItem.setSodium(Integer.parseInt(sodiumFieldAdmin.getText()));
        if(!priceFieldAdmin.getText().equals("")) menuItem.setPrice(Integer.parseInt(priceFieldAdmin.getText()));
        errorLogAdmin.setText("Product updated successfully");
    return 0;
    }
    /**
     * Method that calls the deserialization and adds the resulted items in the tables
     */
    @FXML
    public void importOriginalProducts(){
        ObservableList<MenuItem> oblist = FXCollections.observableArrayList();
        Set<BaseProduct> initialItems= deliveryServiceProcessing.importInitialItems();
        oblist.addAll(initialItems);
        initializeHeaders();
        adminProductTable.getItems().setAll(oblist);
    }

    @FXML
    public void updateTableWithCurrentItems() throws IOException {
        initializeHeaders();
        initializeHeaders1();
        ObservableList<BaseProduct> menuItems = FXCollections.observableArrayList();
        menuItems=serialization.deSerializeMenu();
        ObservableList<CompositeProduct> compositeProducts = FXCollections.observableArrayList();
        compositeProducts=serialization.deSerializeCompositeProducts();
        adminProductTable.getItems().setAll(menuItems);
        adminProductTable1.getItems().setAll(compositeProducts);
    }
    /**
     * Method that serializes the current menu in order to save it for further use
     */
    @FXML
    public void saveCurrentMenu() throws IOException {
        ObservableList<MenuItem> observableList = FXCollections.observableArrayList();
        observableList=adminProductTable.getItems();
        ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>(observableList);
        serialization.serializeMenu(menuItems);
        ArrayList<CompositeProduct> compositeProducts = new ArrayList<>(adminProductTable1.getItems());
        serialization.serializeCompositeProducts(compositeProducts);
    }

    @FXML
    private void initializeHeadersClient(){
        initializeTableHeaders(clientColumnId, clientColumnTitle, clientColumnRating, clientColumnCalories, clientColumnProtein, clientColumnFat, clientColumnSodium, clientColumnPrice);
    }
    @FXML
    private void initializeHeadersClient1(){
        initializeTableHeaders(clientColumnId1, clientColumnTitle1, clientColumnRating1, clientColumnCalories1, clientColumnProtein1, clientColumnFat1, clientColumnSodium1, clientColumnPrice1);
    }

    private void initializeTableHeaders(TableColumn<MenuItem, Integer> clientColumnId, TableColumn<MenuItem, String> clientColumnTitle, TableColumn<MenuItem, Double> clientColumnRating, TableColumn<MenuItem, Integer> clientColumnCalories, TableColumn<MenuItem, Integer> clientColumnProtein, TableColumn<MenuItem, Integer> clientColumnFat, TableColumn<MenuItem, Integer> clientColumnSodium, TableColumn<MenuItem, Integer> clientColumnPrice) {
        clientColumnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        clientColumnTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        clientColumnRating.setCellValueFactory(new PropertyValueFactory<>("Rating"));
        clientColumnCalories.setCellValueFactory(new PropertyValueFactory<>("Calories"));
        clientColumnProtein.setCellValueFactory(new PropertyValueFactory<>("Protein"));
        clientColumnFat.setCellValueFactory(new PropertyValueFactory<>("Fat"));
        clientColumnSodium.setCellValueFactory(new PropertyValueFactory<>("Sodium"));
        clientColumnPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
    }

    @FXML
    public void updateTableWithCurrentItemsClient() throws IOException {
        initializeHeadersClient();
        initializeHeadersClient1();
        ObservableList<BaseProduct> menuItems = FXCollections.observableArrayList();
        menuItems=serialization.deSerializeMenu();
        clientProductTable.getItems().setAll(menuItems);
        ObservableList<CompositeProduct> menuItems1;
        menuItems1 = serialization.deSerializeCompositeProducts();
        clientProductTable1.getItems().setAll(menuItems1);
    }
    /**
     * Method used for finding specific items based on some criteria given by the user
     * This method takes the items form the TableView and passes through the filter method in order to obtain
     * a new ObservableList with the found menu items
     */
    @FXML
    public void clientFindItem(){
        int id,calories,protein,fat,sodium,price;
        double rating;
        String title;
        if(textFieldClientId.getText().equals("")) id=-1; else id=Integer.parseInt(textFieldClientId.getText());
        title=textFieldClientTitle.getText();
        if(textFieldClientRating.getText().equals("")) rating=-1; else rating=Double.parseDouble(textFieldClientRating.getText());
        if(textFieldClientCalories.getText().equals("")) calories=-1; else calories=Integer.parseInt(textFieldClientCalories.getText());
        if(textFieldClientProtein.getText().equals("")) protein=-1; else protein=Integer.parseInt(textFieldClientProtein.getText());
        if(textFieldClientFat.getText().equals("")) fat=-1; else fat=Integer.parseInt(textFieldClientFat.getText());
        if(textFieldClientSodium.getText().equals("")) sodium=-1; else sodium=Integer.parseInt(textFieldClientSodium.getText());
        if(textFieldClientPrice.getText().equals("")) price=-1; else price=Integer.parseInt(textFieldClientPrice.getText());
        List<MenuItem> foundMenuItems;
        foundMenuItems = deliveryServiceProcessing.findMenuItem(clientProductTable.getItems(),id,title,rating,calories,protein,fat,sodium,price);
        ObservableList<MenuItem> observableList = FXCollections.observableArrayList();
        observableList.addAll(foundMenuItems);
        initializeHeadersClient();
        clientProductTable.getItems().setAll(foundMenuItems);
    }
    @FXML
    void adminFindItem(){
        int id,calories,protein,fat,sodium,price;
        double rating;
        String title;
        if(idFieldAdmin.getText().equals("")) id=-1; else id=Integer.parseInt(idFieldAdmin.getText());
        title=titleFieldAdmin.getText();
        if(ratingFieldAdmin.getText().equals("")) rating=-1; else rating=Double.parseDouble(ratingFieldAdmin.getText());
        if(caloriesFieldAdmin.getText().equals("")) calories=-1; else calories=Integer.parseInt(caloriesFieldAdmin.getText());
        if(proteinFieldAdmin.getText().equals("")) protein=-1; else protein=Integer.parseInt(proteinFieldAdmin.getText());
        if(fatFieldAdmin.getText().equals("")) fat=-1; else fat=Integer.parseInt(fatFieldAdmin.getText());
        if(sodiumFieldAdmin.getText().equals("")) sodium=-1; else sodium=Integer.parseInt(sodiumFieldAdmin.getText());
        if(priceFieldAdmin.getText().equals("")) price=-1; else price=Integer.parseInt(priceFieldAdmin.getText());
        List<MenuItem> foundMenuItems;
        foundMenuItems = deliveryServiceProcessing.findMenuItem(adminProductTable.getItems(),id,title,rating,calories,protein,fat,sodium,price);
        ObservableList<MenuItem> observableList = FXCollections.observableArrayList();
        observableList.addAll(foundMenuItems);
        initializeHeaders();
        adminProductTable.getItems().setAll(foundMenuItems);
    }
    /**
     * Method for creating a CompositeProduct , available only for the admin
     * This method takes the ids of BaseProducts specified by the user and creates a CompositeProduct that
     * contains the new items.
     * An unique id is generated for the new CompositeProduct
     */
    @FXML
    private void createMenuItemAdmin() throws IOException {
        int id;
        String[] splitted = menuFieldAdmin.getText().split("\\s+");
        for (String s : splitted) {
           try {
               id=Integer.parseInt(s);
               if(id<0 || id>adminProductTable.getItems().size()+adminProductTable1.getItems().size()){
                   errorLogAdmin.setText("All ids must be >0 and <nr of products");
                   return;
               }
           }catch(NumberFormatException e){
               errorLogAdmin.setText("All ids must be numbers");
               return;
           }
        }
        String title = adminProductName.getText();
        ArrayList<BaseProduct> baseProducts = new ArrayList<>();
        double rating = 0;
        int calories=0,protein=0,fat=0,sodium=0,price=0;
        for(String s : splitted){
            BaseProduct baseProduct = null;
            for(MenuItem menuItem : adminProductTable.getItems()){
                if(menuItem.getId()==Integer.parseInt(s)){
                    baseProduct= (BaseProduct) menuItem;
                }
            }
            baseProducts.add(baseProduct);
            rating+=baseProduct.getRating();
            calories+=baseProduct.getCalories();
            protein+=baseProduct.getProtein();
            fat+=baseProduct.getFat();
            sodium+=baseProduct.getSodium();
            price+=baseProduct.getPrice();
        }
        CompositeProduct compositeProduct = new CompositeProduct(title,baseProducts);
        compositeProduct.setRating(rating/baseProducts.size());
        compositeProduct.setCalories(calories);
        compositeProduct.setPrice(price);
        compositeProduct.setProtein(protein);
        compositeProduct.setFat(fat);
        compositeProduct.setSodium(sodium);
        compositeProduct.setId(Math.max(adminProductTable.getItems().get(adminProductTable.getItems().size()-1).getId(),adminProductTable1.getItems().get(adminProductTable1.getItems().size()-1).getId())+1);
        adminProductTable1.getItems().add(compositeProduct);
    }

    @FXML
    private ArrayList<BaseProduct> createOrderClient2() throws IOException {
        textAreaOrder.clear();
        int ok=0;
        ArrayList<BaseProduct> orderedProducts = new ArrayList<>();
        ArrayList<BaseProduct> temporaryProducts = new ArrayList<>();
        String orderString = orderFieldClient.getText();
        String[] splitted = orderString.split( "\\s+");
        for(String s:splitted){
            try {
                for(MenuItem menuItem: clientProductTable.getItems()){
                    if(menuItem.getId()==Integer.parseInt(s)){
                        orderedProducts.add((BaseProduct) menuItem);
                        ok=1;
                    }
                }
                for(MenuItem menuItem: clientProductTable1.getItems()){
                    if(menuItem.getId()==Integer.parseInt(s)){
                        CompositeProduct compositeProduct = (CompositeProduct) menuItem;
                        temporaryProducts.addAll(compositeProduct.getArrayOfBaseProducts());
                        ok=1;
                    }
                }
                if(temporaryProducts.size()>1){
                    orderedProducts.add(temporaryProducts.get(0));
                    int i=1;
                    while(!temporaryProducts.get(i).getTitle().equals(orderedProducts.get(0).getTitle())){
                        orderedProducts.add(temporaryProducts.get(i));
                        i++;
                    }}
                if(ok==0){
                    errorFieldClient.setText("You must enter a valid Id");
                    break;
                }

            }catch (NumberFormatException e){
                errorFieldClient.setText("Ids must be numbers");
                break;
            }
        }
        return new ArrayList<>(orderedProducts);
    }
    /**
     * Method used for creating a order for the user
     * The method takes the ids of the items selected by the user and generates a bill in a TextArea where the
     * user can see information about the current order
     * If he confirms the order, the data is further processed
     */
    @FXML
    private void createOrderClient() throws IOException {
        textAreaOrder.clear();
        int ok=0;
        ArrayList<BaseProduct> orderedProducts = new ArrayList<>();
        ArrayList<BaseProduct> temporaryProducts = new ArrayList<>();
        String orderString = orderFieldClient.getText();
        String[] splitted = orderString.split( "\\s+");
        for(String s:splitted){
            try {
                 for(MenuItem menuItem: clientProductTable.getItems()){
                     if(menuItem.getId()==Integer.parseInt(s)){
                         orderedProducts.add((BaseProduct) menuItem);
                         ok=1;
                     }
                 }
                 for(MenuItem menuItem: clientProductTable1.getItems()){
                     if(menuItem.getId()==Integer.parseInt(s)){
                          CompositeProduct compositeProduct = (CompositeProduct) menuItem;
                          temporaryProducts.addAll(compositeProduct.getArrayOfBaseProducts());
                          ok=1;
                      }
                 }
                 if(temporaryProducts.size()>1){
                 orderedProducts.add(temporaryProducts.get(0));
                 int i=1;
                 while(!temporaryProducts.get(i).getTitle().equals(orderedProducts.get(0).getTitle())){
                     orderedProducts.add(temporaryProducts.get(i));
                     i++;
                 }}
                 if(ok==0){
                     errorFieldClient.setText("You must enter a valid Id");
                     return;
                 }

            }catch (NumberFormatException e){
                errorFieldClient.setText("Ids must be numbers");
                return;
            }
        }
        handleOrder(orderedProducts);
    }

    private void handleOrder(ArrayList<BaseProduct> orderedProducts) throws FileNotFoundException {
        ClientBLL clientBLL = new ClientBLL();
        File myFile = new File("CurrentClient.txt");
        Scanner myScanner = new Scanner(myFile);
        int price =0;
        List<Client> clients = clientBLL.findByIdClient(myScanner.nextLine());
        myScanner.close();
        textAreaOrder.appendText("Client username: "+clients.get(0).getUsername() +"\n");
        textAreaOrder.appendText("Client email: "+clients.get(0).getEmail() + "\n");
        textAreaOrder.appendText("Client address: "+clients.get(0).getAddress() + "\n");
        textAreaOrder.appendText("\n");
        for(BaseProduct baseProduct:orderedProducts){
            textAreaOrder.appendText( baseProduct.getTitle() + " ");
            textAreaOrder.appendText("Price: "+ baseProduct.getPrice());
            textAreaOrder.appendText(" Rating: " + baseProduct.getRating());
            textAreaOrder.appendText("\n");
            price+=baseProduct.getPrice();
        }
        String timeStamp = new SimpleDateFormat(" dd.MM.yyyy  HH:mm:ss").format(Calendar.getInstance().getTime());
        textAreaOrder.appendText("\nDate:" + timeStamp + " ");
        textAreaOrder.appendText("\n");
        textAreaOrder.appendText("Total price: " + price);
    }
    /**
     * This method is called when the user confirms the order
     * The information abut the order is saved to a txt file to create a bill
     * The order is also saved so that it can be seen by the admin and employees
     * @exception FileNotFoundException - did not found a file to write to
     */
    @FXML
    private void confirmOrder() throws IOException, SQLIntegrityConstraintViolationException, IllegalAccessException {
        File myFile = new File("CurrentClient.txt");
        Scanner myScanner = new Scanner(myFile);
        int clientId=Integer.parseInt(myScanner.nextLine());

        FileWriter myWriter = new FileWriter("Bill.txt");
        myWriter.write(textAreaOrder.getText());
        myWriter.close();
        textAreaOrder.setText("Thank you for ordering from us !");

        String timeStamp = new SimpleDateFormat("dd:HH:mm:ss").format(Calendar.getInstance().getTime());
        String[] splitted = timeStamp.split( ":");
        StringBuilder sb = new StringBuilder();
        for(BaseProduct baseProduct: createOrderClient2()){
            sb.append(baseProduct.getTitle());
            sb.append(", ");
        }
        Order order = new Order(clientId,Integer.parseInt(splitted[1]),Integer.parseInt(splitted[2]),Integer.parseInt(splitted[3]),Integer.parseInt(splitted[0]),sb.toString());
        OrderBll orderBll = new OrderBll();
        orderBll.insertOrder(order);
    }

    @FXML
    private void filterOrders() throws SQLException {
        int dayMin,dayMax,hourMin,hourMax,minuteMin,minuteMax,secondMin,secondMax;
        if(reportFieldDayMin.getText().equals("")) dayMin=-1; else dayMin = Integer.parseInt(reportFieldDayMin.getText());
        if(reportFieldDayMax.getText().equals("")) dayMax=90; else dayMax = Integer.parseInt(reportFieldDayMax.getText());
        if(reportFieldHourMin.getText().equals("")) hourMin=-1; else hourMin = Integer.parseInt(reportFieldHourMin.getText());
        if(reportFieldHourMax.getText().equals("")) hourMax=90; else hourMax = Integer.parseInt(reportFieldHourMax.getText());
        if(reportFieldMinuteMin.getText().equals("")) minuteMin=-1; else minuteMin = Integer.parseInt(reportFieldMinuteMin.getText());
        if(reportFieldMinuteMax.getText().equals("")) minuteMax=90; else minuteMax = Integer.parseInt(reportFieldMinuteMax.getText());
        if(reportFieldSecondMin.getText().equals("")) secondMin=-1; else secondMin = Integer.parseInt(reportFieldSecondMin.getText());
        if(reportFieldSecondMax.getText().equals("")) secondMax=90; else secondMax = Integer.parseInt(reportFieldSecondMax.getText());

        OrderBll orderBll = new OrderBll();
        ObservableList<Order> observableList = FXCollections.observableArrayList();
        observableList.addAll(orderBll.initializeTablesOrders());
        List<Order> orderList;
        orderList = deliveryServiceProcessing.findOrders(tableViewReports.getItems(),hourMin,hourMax,minuteMin,minuteMax,secondMin,secondMax,dayMin,dayMax);
        tableViewReports.getItems().setAll(orderList);
    }
}
