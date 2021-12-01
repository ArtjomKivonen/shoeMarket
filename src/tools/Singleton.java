/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author artie
 */
public class Singleton {
    private static Singleton instance;
    private EntityManager entityManager;
    private Singleton(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ShoeMarketPU");
        entityManager = emf.createEntityManager();;
    }
    
    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
