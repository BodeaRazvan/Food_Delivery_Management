package org.example.bll;

import org.example.dataAccess.EmployeeDAO;
import org.example.model.Employee;

import java.util.List;

public class EmployeeBLL {
    private final EmployeeDAO employeeDAO;

    public EmployeeBLL() {
        employeeDAO=new EmployeeDAO();
    }
    public List<Employee> getEmployees(){
        return employeeDAO.getTableData();
    }

}
