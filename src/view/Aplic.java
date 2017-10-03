package view;

import model.*;
import control.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author 0030481521033
 */
public class Aplic {

    public static void main(String[] args) {
       Scanner input = new Scanner(System.in);
       
       DaoCustomer daocustomer;
       Conexao conexao;
       ArrayList<Customer> customers = new ArrayList<>();
       Customer customer = null;
       DecimalFormat df = new DecimalFormat("#,##0.00");
       
       int id;
       String resposta;
       Double media=0.0;
       
       conexao = new Conexao("BD1521033","BD1521033");
       conexao.setDriver("oracle.jdbc.driver.OracleDriver");
       conexao.setConnectionString("jdbc:oracle:thin:@apolo:1521:xe");
       daocustomer = new DaoCustomer(conexao.conectar()); 
                     
       while(true){
           while(true){
                System.out.println("Informe um ID para o cliente:");
                id = input.nextInt();
                
                if(!daocustomer.idLivre(id)){
                    System.out.println("ID já utilizado, informe outro!");
                }else{
                    break;
                }
           }
           customer = new Customer(id);
           
           System.out.println("Informe o CPF/CNPJ (APENAS NÚMEROS):");
           customer.setCpf_cnpj(input.next());
           System.out.println("Informe o nome: ");
           customer.setNome(input.next());
           System.out.println("Informe um (1) para conta ativa e zero (0) para desativa");
           id = input.nextInt();
           customer.setActive(id == 1);
           System.out.println("Informe o valor total do cliente:");
           customer.setValor_total(input.nextDouble());
           
           daocustomer.inserir(customer);
           //customers.add(customer);
           System.out.println("Deseja cadastrar outro cliente? (S | N)");
           resposta = input.next();
           
           if(resposta.equals("N") || resposta.equals("n")){
               break;
           }
       }
       
       customers = daocustomer.getALL();
       
        for(int i=0; i<customers.size(); i++){
            if(customers.get(i).getValor_total() <= 560 || 
            customers.get(i).getId() < 1500 || customers.get(i).getId() > 2700){
                customers.remove(i);
            }
        }  
        
        Collections.sort(customers, new ComparadorDeCustomer());
        
        System.out.println("Clientes utilizadas na média: ");
        
        for (Customer customer1 : customers) {
            System.out.println("\nID: " + customer1.getId() + "\nNome: " + customer1.getNome() +
                    "\nValor Total: " + df.format(customer1.getValor_total()));
            media += customer1.getValor_total();
        }
        
        System.out.println("\nMédia dos clientes utilizadas: " + df.format(media / customers.size()));
    }
}
