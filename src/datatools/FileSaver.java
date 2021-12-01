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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pupil
 */
public class FileSaver implements Retentive{
  
  @Override
  public void saveShoes(List<Shoe> shoes){
    FileOutputStream fos = null;
    ObjectOutputStream oos = null;
    try {
      fos = new FileOutputStream("shoes");
      
        oos = new ObjectOutputStream(fos);
        oos.writeObject(shoes);
        oos.flush();
        
    } catch (FileNotFoundException ex) {
      Logger.getLogger(FileSaver.class.getName()).log(Level.SEVERE, "File shoes not found", ex);
    } catch (IOException ex) {
      Logger.getLogger(FileSaver.class.getName()).log(Level.SEVERE, "Input error", ex);
    }
            
  
  }
  
  @Override
  public List<Shoe> loadShoes(){
      List<Shoe> listShoes = new ArrayList<>();
      FileInputStream fis = null;
      ObjectInputStream ois = null;
      try {
        fis = new FileInputStream("shoes");
        ois = new ObjectInputStream(fis);
        listShoes = (List<Shoe>) ois.readObject();
    } catch (FileNotFoundException ex) {
            Logger.getLogger(FileSaver.class.getName()).log(Level.SEVERE, "No file shoes", ex);
        } catch (IOException ex) {
            Logger.getLogger(FileSaver.class.getName()).log(Level.SEVERE, "Input error", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileSaver.class.getName()).log(Level.SEVERE, "Class not found", ex);
        }
      return listShoes;
  }
  
  
  public void saveCustomers(List<Customer> customers){
    
    FileOutputStream fos = null;
    ObjectOutputStream oos = null;
    try {
      fos = new FileOutputStream("customers");
      
        oos = new ObjectOutputStream(fos);
        oos.writeObject(customers);
        oos.flush();
        
    } catch (FileNotFoundException ex) {
      Logger.getLogger(FileSaver.class.getName()).log(Level.SEVERE, "File customers not found", ex);
    } catch (IOException ex) {
      Logger.getLogger(FileSaver.class.getName()).log(Level.SEVERE, "Input error", ex);
    }
  }
  public List<Customer> loadCustomers(){
    
    List<Customer> listCustomers = new ArrayList<>();
      FileInputStream fis = null;
      ObjectInputStream ois = null;
      try {
        fis = new FileInputStream("shoes");
        ois = new ObjectInputStream(fis);
        listCustomers = (List<Customer>) ois.readObject();
    } catch (FileNotFoundException ex) {
            Logger.getLogger(FileSaver.class.getName()).log(Level.SEVERE, "File customers not found", ex);
        } catch (IOException ex) {
            Logger.getLogger(FileSaver.class.getName()).log(Level.SEVERE, "Input error", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileSaver.class.getName()).log(Level.SEVERE, "Class not found", ex);
        }
      return listCustomers;
  }
  public void savePurchases(List<Purchase> purchases){
  
    FileOutputStream fos = null;
    ObjectOutputStream oos = null;
    try {
      fos = new FileOutputStream("purchases");
      
        oos = new ObjectOutputStream(fos);
        oos.writeObject(purchases);
        oos.flush();
        
    } catch (FileNotFoundException ex) {
      Logger.getLogger(FileSaver.class.getName()).log(Level.SEVERE, "File purchases not found", ex);
    } catch (IOException ex) {
      Logger.getLogger(FileSaver.class.getName()).log(Level.SEVERE, "Input error", ex);
    }
  }
  public List<Purchase> loadPurchases(){
    
      List<Purchase> listPurchases = new ArrayList<>();
      FileInputStream fis = null;
      ObjectInputStream ois = null;
      try {
        fis = new FileInputStream("shoes");
        ois = new ObjectInputStream(fis);
        listPurchases = (List<Purchase>) ois.readObject();
    } catch (FileNotFoundException ex) {
            Logger.getLogger(FileSaver.class.getName()).log(Level.SEVERE, "File purchases not found", ex);
        } catch (IOException ex) {
            Logger.getLogger(FileSaver.class.getName()).log(Level.SEVERE, "Input error", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileSaver.class.getName()).log(Level.SEVERE, "Class not found", ex);
        }
      return listPurchases;
  }
  
  
  
}
