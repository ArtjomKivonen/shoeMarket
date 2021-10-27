/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myclasses;

import entities.Customer;
import entities.Purchase;
import entities.Shoe;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author artie
 */
public class App {
    Scanner scanner = new Scanner(System.in);
    List<Shoe> shoes = new ArrayList<>();
    List<Customer> customers = new ArrayList<>();
    List<Purchase> purchases = new ArrayList<>();
    
    public App(){
        
        
        
    }
    
    public void run() {
        String appLive = "y";
        do{
            System.out.println("Выберите задачу: ");
            System.out.println("0: Закончить программу");
            System.out.println("1: Добавить модель");
            System.out.println("2: Список продаваемых моделей");
            System.out.println("3: Добавить покупателя");
            System.out.println("4: Список зарегистрированных покупателей");
            System.out.println("5: Покупка покупателем обуви");
            System.out.println("6: Доход магазина за все время работы");
            
            int menu = scanner.nextInt();
            scanner.nextLine();
            
            switch (menu){
            
                case 0:
                    appLive="q";
                    System.out.println("Программа закончена");
                    break;
                
                case 1:
                    System.out.println("Добавление модели");
                    shoes.add(addShoe());
                    break;
                     
                case 2:
                    System.out.println("Список продаваемых моделей");
                    for (int i = 0; i < shoes.size(); i++) {
                        if(shoes.get(i) != null){
                            System.out.println(shoes.get(i).toString());
                        }
                        
                    }
                    break;
                    
                
                case 3:
                    System.out.println("Добавление покупателя");
                    customers.add(addCustomer());
                    break;
                case 4:
                    System.out.println("Список покупателей");
                    for (int i = 0; i < customers.size(); i++) {
                        if(customers.get(i) != null){
                            System.out.println(customers.get(i).toString());
                        }
                        
                    }
                    break;
                case 5:
                    System.out.println("Покупка покупателем обуви");
                    purchases.add(addPurchase());
                    break;
                case 6:
                    double countIncome=incomeCount();
                    System.out.println("Доход магазина за время работа="+countIncome+" euro");
                    break;
            }
            
            }while("y".equals(appLive));
        }
    
        private Shoe addShoe(){
            Shoe shoe = new Shoe();
            System.out.println("Название модели:");
            shoe.setName(scanner.nextLine());
            System.out.println("Название бренда:");
            shoe.setBrand(scanner.nextLine());
            System.out.println("Цена модели:");
            shoe.setPrice(scanner.nextInt());
            scanner.nextLine();
            return shoe;
        }
        
        private Customer addCustomer(){
            Customer customer = new Customer();
            System.out.println("Введите имя покупателя:");
            customer.setFname(scanner.nextLine());
            System.out.println("Введите фамилию покупателя:");
            customer.setLname(scanner.nextLine());
            System.out.println("Введите id покупателя:");
            customer.setId(scanner.nextInt());
            scanner.nextLine();
            System.out.println("Введите количество денег покупателя(eurocents):");
            customer.setFunds(scanner.nextInt());
            scanner.nextLine();
            return customer;
        }
        private Purchase addPurchase(){
            Purchase purchase = new Purchase();
            System.out.println("Список моделей обуви: ");
            for (int i = 0; i < shoes.size(); i++) {
                if(shoes.get(i) != null){
                            System.out.printf("%d.%s%n", i+1, shoes.get(i).toString());
                        }  
            }
            System.out.println("Введие номер модели: ");
            int shoeNum = scanner.nextInt();scanner.nextLine();
            purchase.setShoe(shoes.get(shoeNum-1));
            purchase.setCost(shoes.get(shoeNum-1).getPrice());
            System.out.println();
            System.out.println("Список покупателей: ");
            for (int i = 0; i < customers.size(); i++) {
                if(customers.get(i) != null){
                            System.out.printf("%d.%s%n", i+1, customers.get(i).toString());
                        }
            }
            System.out.println("Введите номер покупателя: ");
            int customerNum = scanner.nextInt();scanner.nextLine();
            purchase.setCustomer(customers.get(customerNum-1));
            System.out.println();
            Calendar c = new GregorianCalendar();
            purchase.setPurchaseDate(c.getTime());
                   
            
        return purchase;
        }
        private double incomeCount(){
            double income=0;
            for (int i = 0; i < purchases.size(); i++) {
                if(purchases.get(i)!=null){
                    income=income+purchases.get(i).getCost();
                }
            }
            income=income/100;
        return income;
        }
        
        
    }

