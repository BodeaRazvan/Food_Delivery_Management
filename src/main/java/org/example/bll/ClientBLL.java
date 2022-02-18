package org.example.bll;

import org.example.dataAccess.ClientDAO;
import org.example.model.Client;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class ClientBLL {
    private final ClientDAO clientDAO;

    public ClientBLL() {
        clientDAO= new ClientDAO();
    }
    public int insertClient(Client client) throws IllegalAccessException, SQLIntegrityConstraintViolationException {
        int nr= clientDAO.insert(client);
        if(nr==1){
            return 1;
        }
        return 0;
    }
    public List<Client> getClients(){
        return clientDAO.getTableData();
    }
    public List<Client> findByIdClient(String id) {
        return clientDAO.findById(id);
    }

}
