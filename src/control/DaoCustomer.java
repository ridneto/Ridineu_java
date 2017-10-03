package control;

import model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ridneto
 */
public class DaoCustomer {
    private Connection connection;
    
    public DaoCustomer(Connection connection) {
         this.connection = connection;
    }
    
    public void inserir(Customer customer) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO tb_customer_account(Id_customer, cpf_cnpj, Nm_customer, active, vl_total) " +
                                             "VALUES(?, ?, ?, ?, ?)");
            ps.setInt(1, customer.getId());
            ps.setString(2, customer.getCpf_cnpj());
            ps.setString(3, customer.getNome());
            if(customer.isActive()){
                ps.setInt(4, 1);
            }else{
                ps.setInt(4, 0);
            }
            ps.setDouble(5, customer.getValor_total());
            
        } catch (SQLException ex) {
             System.out.println(ex.toString() + " em daoCustomer");   
        }
    }
    
    public ArrayList<Customer> getALL() {
        ArrayList<Customer> customers = new ArrayList<>();
        Customer customer;
        boolean active;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * from tb_customer_account");
            ResultSet rs = ps.executeQuery();
           
            while(rs.next()){
                if(rs.getInt("active") == 1){
                    active = true;
                }else{
                    active = false;
                }
                
                customer = new Customer(rs.getInt("Id_customer"));
                customer.setCpf_cnpj(rs.getString("cpf_cnpj"));
                customer.setActive(active);
                customer.setValor_total(rs.getDouble("vl_total"));
                customer.setNome(rs.getString("Nm_customer"));
                customers.add(customer);
            }
        }
        catch (SQLException ex) { 
            System.out.println(ex.toString() + " em daoCustomer");    
        }
        
        return customers;
    }
    
    public boolean idLivre(int id){
        PreparedStatement ps = null;
        
        try{
            ps = connection.prepareStatement("SELECT * from tb_customer_account where Id_customer = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                return true;
            }else{
                return false;
            }            
        }
        catch (SQLException ex) { 
            System.out.println(ex.toString() + " em daoCustomer");    
        }
        
        return false;
    }
}
