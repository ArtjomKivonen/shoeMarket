/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entities.Purchase;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import tools.Singleton;

/**
 *
 * @author pupil
 */
public class PurchaseFacade extends AbstractFacade<Purchase> {
    private EntityManager em;
    
    public PurchaseFacade(Class<Purchase> entityClass){
      super(entityClass);
      Singleton singleton = Singleton.getInstance();
      em = singleton.getEntityManager();
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    

}
