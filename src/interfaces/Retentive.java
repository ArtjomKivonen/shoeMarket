/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Shoe;
import entities.Customer;
import entities.Purchase;
import java.util.List;


/**
 *
 * @author pupil
 */
public interface Retentive {
  public void saveShoes(List<Shoe> shoes);
  public List<Shoe> loadShoes();
  public void saveCustomers(List<Customer> customers);
  public List<Customer> loadCustomers();
  public void savePurchases(List<Purchase> purchases);
  public List<Purchase> loadPurchases();
  
}
