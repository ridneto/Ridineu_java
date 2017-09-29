package model;

import java.util.Comparator;

/**
 *
 * @author ridneto
 */
public class ComparadorDeCustomer implements Comparator<Customer> {
@Override
    public int compare(Customer t, Customer t1) {
       if(t.getValor_total() > t1.getValor_total() ){
           return -1;
       }else if(t.getValor_total()  < t1.getValor_total() ){
           return 1;
       }else{
           return 0;
       }
    }
}