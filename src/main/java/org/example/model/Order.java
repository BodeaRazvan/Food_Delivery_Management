package org.example.model;

import java.util.Objects;

public class Order {
    private int orderId;
    private int clientId;
    private int hour;
    private int minute;
    private int second;
    private int day;
    private String orderedItemsIds;

    public Order(){

    }

    public Order(int orderId, int clientId, int hour, int minute, int second, int day, String orderedItemsIds) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.day = day;
        this.orderedItemsIds = orderedItemsIds;
    }

    public Order(int clientId, int hour, int minute, int second, int day, String orderedItemsIds) {
        this.clientId = clientId;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.day = day;
        this.orderedItemsIds = orderedItemsIds;
    }


    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return  clientId == order.clientId &&
                hour == order.hour &&
                minute == order.minute &&
                second == order.second &&
                day == order.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, hour, minute, second, day);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderedItemsIds() {
        return orderedItemsIds;
    }

    public void setOrderedItemsIds(String orderedItemsIds) {
        this.orderedItemsIds = orderedItemsIds;
    }
}
