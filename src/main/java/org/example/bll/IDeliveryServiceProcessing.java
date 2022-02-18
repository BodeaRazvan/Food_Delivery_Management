package org.example.bll;

import org.example.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface IDeliveryServiceProcessing {

    public Set<BaseProduct> importInitialItems();

    public void createMenuItem();
    public void deleteMenuItem();
    public void modifyMenuItem();
    public List<MenuItem> findMenuItem(List<MenuItem> menuItems,int id, String title, double rating,
                                       int calories, int protein, int fat, int sodium, int price);
    public List<Order>  findOrders(List<Order> orderList,int hourMin, int hourMax,
                                   int minuteMin, int minuteMax, int secondMin, int secondMax, int dayMin, int dayMax);
    public void createOrder(Order order , ArrayList<MenuItem> menuItems);
    public void calculateOrderPrice();
    public void generateBill();
}
