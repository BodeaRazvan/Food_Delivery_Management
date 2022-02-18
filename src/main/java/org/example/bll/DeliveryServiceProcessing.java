package org.example.bll;

import org.example.dataAccess.Serialization;
import org.example.model.Order;

import java.util.*;
import java.util.stream.Collectors;

public class DeliveryServiceProcessing implements IDeliveryServiceProcessing{
    private Map<Order, Set<MenuItem>> orderMenu;
    private Set<MenuItem> menuItems;
    private List<MenuItem> foundMenuItems;
    private List<Order> foundOrders;

    public ArrayList<BaseProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(ArrayList<BaseProduct> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    private ArrayList<BaseProduct> orderedProducts;

    public ArrayList<CompositeProduct> getCompositeProducts() {
        return compositeProducts;
    }

    public void setCompositeProducts(ArrayList<CompositeProduct> compositeProducts) {
        this.compositeProducts = compositeProducts;
    }

    public void addCompositeProduct(CompositeProduct compositeProduct){
        this.compositeProducts.add(compositeProduct);
    }

    private ArrayList<CompositeProduct> compositeProducts =new ArrayList<>();

    /**
     * This method calls the serialization method for reading the BaseProducts from the .csv file
     * An unique id is also created for each item imported
     * @return  - Set of BaseProducts
     */
    @Override
    public Set<BaseProduct> importInitialItems() {
        Serialization serialization = new Serialization();
        Set<BaseProduct> baseProducts = serialization.readFromFile();
        ArrayList<BaseProduct> toRemove = new ArrayList<>();
        for(BaseProduct baseProduct:baseProducts){
            for(BaseProduct baseProduct1:baseProducts){
                if(baseProduct.getTitle().equals(baseProduct1.getTitle()) && !baseProduct.equals(baseProduct1)){
                    toRemove.add(baseProduct1);
                }
            }
        }
        baseProducts.removeAll(toRemove);
        int i=1;
        for(BaseProduct baseProduct:baseProducts){
        baseProduct.setId(i);
        i++;
    }
        return baseProducts;
}

    @Override
    public void createMenuItem() {

    }

    @Override
    public void deleteMenuItem() {

    }

    @Override
    public void modifyMenuItem() {

    }
    /**
     * This method uses streams to filter objects
     * @param menuItems A list of menuItems that we want to sort
     * @param id,title,rating,calories,protein,fat,sodium,price Data related to the menu item that we use for sorting
     * @return  - A list of menuItems filtered based on the given criteria
     */
    @Override
    public List<MenuItem> findMenuItem(List<MenuItem> menuItems,int id, String title, double rating,
                                       int calories, int protein, int fat, int sodium, int price) {
        try{
            foundMenuItems= menuItems.stream()
                    .filter(menuItem -> id==-1 || menuItem.getId()==id)
                    .filter(menuItem -> title.equals("") || menuItem.getTitle().contains(title))
                    .filter(menuItem -> rating==-1.0 || menuItem.getRating()==rating)
                    .filter(menuItem -> calories==-1 || menuItem.getCalories()==calories)
                    .filter(menuItem -> protein==-1 || menuItem.getProtein()==protein)
                    .filter(menuItem -> fat==-1 || menuItem.getFat()==fat)
                    .filter(menuItem -> sodium==-1 || menuItem.getSodium()==sodium)
                    .filter(menuItem -> price==-1 || menuItem.getPrice()==price)
                    .collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
        }
        return foundMenuItems;
    }
    /**
     * This method uses streams to sort orders
     * @param orderList - A list of orders that we want to sort
     * @param hourMin,hourMax,minuteMin,minuteMax,secondMin,secondMax,dayMin,dayMax - parameters that represent the filtering conditions
     * @return  - A list of orders after the filtering is performed
     */
    @Override
    public List<Order>  findOrders(List<Order> orderList,int hourMin, int hourMax,
                                   int minuteMin, int minuteMax, int secondMin, int secondMax, int dayMin, int dayMax){
        try{
            foundOrders = orderList.stream()
                    .filter(order -> order.getDay()>=dayMin && order.getDay()<=dayMax)
                    .filter(order -> order.getHour()>=hourMin && order.getHour()<=hourMax)
                    .filter(order -> order.getMinute()>=minuteMin && order.getMinute()<=minuteMax)
                    .filter(order -> order.getSecond()>=secondMin && order.getSecond()<=secondMax)
                    .collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
        }
        return foundOrders;
    }

    @Override
    public void createOrder(Order order, ArrayList<MenuItem> menuItems) {

    }

    @Override
    public void calculateOrderPrice() {

    }

    @Override
    public void generateBill() {

    }

    public Map<Order, Set<MenuItem>> getOrderMenu() {
        return orderMenu;
    }

    public void setOrderMenu(Map<Order, Set<MenuItem>> orderMenu) {
        this.orderMenu = orderMenu;
    }

    public Set<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(Set<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public List<MenuItem> getFoundMenuItems() {
        return foundMenuItems;
    }

    public void setFoundMenuItems(List<MenuItem> foundMenuItems) {
        this.foundMenuItems = foundMenuItems;
    }
}
