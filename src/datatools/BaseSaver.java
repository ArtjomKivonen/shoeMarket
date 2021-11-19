/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datatools;

import entities.Customer;
import entities.Purchase;
import entities.Shoe;
import interfaces.Retentive;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author pupil
 */
public class BaseSaver implements Retentive {
  
    private final EntityManager em;
    private final EntityTransaction tx;
    public BaseSaver(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ShoeMarketPU");
        em = emf.createEntityManager();
        tx = em.getTransaction();
        
    } 
    
    

  
  
  @Override
  public void saveShoes(List<Shoe> shoes){
    
    tx.begin();
        for (int i = 0; i < shoes.size(); i++) {
           if(shoes.get(i).getId() == null) {
              Shoe shoe = shoes.get(i);
              em.persist(shoe);
             }
           }
    em.persist(shoes);
    tx.commit();
    
    
  }
  @Override
  public List<Shoe> loadShoes() {

      List<Shoe> shoes = null;
      
    try {
            shoes = em.createQuery("SELECT shoe FROM Shoe shoe")
                    .getResultList();
        } catch (Exception e) {
            shoes = new ArrayList<>();
        }
        return shoes;
  }

  public void saveCustomers(List<Customer> customers){
    
    tx.begin();
        for (int i = 0; i < customers.size(); i++) {
           if(customers.get(i).getId() == null) {
              Customer customer = customers.get(i);
              em.persist(customer);
             }
           }
    em.persist(customers);
    tx.commit();
    
  }
  
  public List<Customer> loadCustomers(){
  
    List<Customer> customers = null;
      
    try {
            customers = em.createQuery("SELECT customer FROM Customer customer")
                    .getResultList();
        } catch (Exception e) {
            customers = new ArrayList<>();
        }
        return customers;
  }
  
  public void savePurchases(List<Purchase> purchases){
    
    tx.begin();
        for (int i = 0; i < purchases.size(); i++) {
           if(purchases.get(i).getId() == null) {
              Purchase purchase = purchases.get(i);
              em.persist(purchase);
             }
           }
    em.persist(purchases);
    tx.commit();
    
  }
  
  public List<Purchase> loadPurchases(){
  
    List<Purchase> purchases = null;
      
    try {
            purchases = em.createQuery("SELECT purchase FROM Purchase purchase")
                    .getResultList();
        } catch (Exception e) {
            purchases = new ArrayList<>();
        }
        return purchases;
  }
  
}
