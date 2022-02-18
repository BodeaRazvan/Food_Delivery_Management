package org.example.bll;

import org.example.dataAccess.AdminDAO;
import org.example.model.Admin;

import java.util.List;

public class AdminBLL {
    private final AdminDAO adminDAO;

    public AdminBLL() {
        adminDAO=new AdminDAO();
    }

    public List<Admin> getAdmins(){
        return adminDAO.getTableData();
    }
}
