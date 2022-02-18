package org.example.bll;

import javafx.scene.control.TableView;
import org.example.dataAccess.OrderDAO;
import org.example.model.Order;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class OrderBll {
    private final OrderDAO orderDAO;

    public OrderBll() {
        orderDAO = new OrderDAO();
    }

    public void insertOrder(Order order) throws SQLIntegrityConstraintViolationException, IllegalAccessException {
        orderDAO.insert(order);
    }

    public List<Order> initializeTablesOrders(){
        return orderDAO.getTableData();
    }
    public TableView<Order> getOrderTableHeaders() throws SQLException {
        return orderDAO.getTableHeaders();
    }
}
