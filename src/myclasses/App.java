/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myclasses;

import datatools.BaseSaver;
import datatools.FileSaver;
import entities.Customer;
import entities.Purchase;
import entities.Shoe;
import interfaces.Retentive;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import javax.print.attribute.HashAttributeSet;

/**
 *
 * @author artie
 */
public class App {
    Scanner scanner = new Scanner(System.in);
    List<Shoe> shoes = new ArrayList<>();
    List<Customer> customers = new ArrayList<>();
    List<Purchase> purchases = new ArrayList<>();
//    Retentive saver = new FileSaver();
    Retentive saver = new BaseSaver();
    public App(){
        shoes = saver.loadShoes();
        customers = saver.loadCustomers();
        purchases = saver.loadPurchases();
        
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
            
            int menu = intInputCheck();
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
                    printListShoes();
                    break;
                    
                
                case 3:
                    System.out.println("Добавление покупателя");
                    customers.add(addCustomer());
                    break;
                case 4:
                    printListCustomers();
                    break;
                case 5:
                    System.out.println("Покупка покупателем обуви");
                    addPurchase();
                    break;
                case 6:
                    double countIncome=incomeCount();
                    System.out.println("Доход магазина за время работа="+countIncome+" euro");
                    break;
                case 7:
                    
                    break;
                case 8:
                    editShoe();
                    break;
                    
                case 9:
                    
                    break;
            }
            
            }while("y".equals(appLive));
        }
    
        private Shoe addShoe(){
            System.out.println("Добавление модели обуви");
//            if(quit()) return;
            Shoe shoe = new Shoe();
            System.out.println("Название модели:");
            shoe.setName(scanner.nextLine());
            System.out.println("Название бренда:");
            shoe.setBrand(scanner.nextLine());
            System.out.println("Цена модели:");
            shoe.setPrice(intInputCheck());
            scanner.nextLine();
            return shoe;
        }
        
        private Customer addCustomer(){
            Customer customer = new Customer();
            System.out.println("Введите имя покупателя:");
            customer.setFname(scanner.nextLine());
            System.out.println("Введите фамилию покупателя:");
            customer.setLname(scanner.nextLine());
            System.out.println("Введите количество денег покупателя(eurocents):");
            customer.setFunds(scanner.nextInt());
            scanner.nextLine();
            return customer;
        }
        private void addPurchase(){
            System.out.println("Добавление покупки");
            if(quit()) return;
            Purchase purchase = new Purchase();
            Set<Integer> setNumbersShoes = printListShoes();
            
            if(setNumbersShoes.isEmpty()) {
                return;
            }
            System.out.println("Введие номер модели: ");
            int shoeNum = chooseFromList(setNumbersShoes);scanner.nextLine();
            purchase.setShoe(shoes.get(shoeNum-1));
            purchase.setCost(shoes.get(shoeNum-1).getPrice());
            System.out.println();
            
            Set<Integer> setNumbersCustomers = printListCustomers();
            
            System.out.println("Введите номер покупателя: ");
            int customerNum = scanner.nextInt();scanner.nextLine();
            purchase.setCustomer(customers.get(customerNum-1));
            System.out.println();
            Calendar c = new GregorianCalendar();
            purchase.setPurchaseDate(c.getTime());
                   
            
        
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

    private int intInputCheck() {
        do {            
            try {
                String strInt = scanner.nextLine();
                return Integer.parseInt(strInt);
            } catch (Exception e) {
                System.out.println("Некорректный ввод, введите целое число: ");
            }
        } while (true);

    }

    private int chooseFromList(Set<Integer> setNumbers) {
        do {            
            int chooseNum = intInputCheck();
            if(setNumbers.contains(chooseNum)) {
                return chooseNum;
            }
            System.out.println("Некорректный ввод, введите номер позиции из списка: ");
        } while (true);
    }

    private boolean quit(){
        System.out.println("Чтобы закончить операцию нажмите \"q\", для продолжения любой другой символ");
        String quit = scanner.nextLine();
        if("q".equals(quit)) return true;
      return false;
    }

    private Set<Integer> printListShoes() {
        System.out.println("Список моделей обуви: ");
            Set<Integer> setNumbersShoes = new HashSet<>();
            for (int i = 0; i < shoes.size(); i++) {
                if(shoes.get(i) != null){
                            System.out.printf("%d.%s%n", i+1, shoes.get(i).toString());
                }
                setNumbersShoes.add(i+1);
            }
        return setNumbersShoes;    
    }

    private void printListBoots() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Set<Integer> printListCustomers() {
        System.out.println("Список покупателей: ");
        Set<Integer> setNumbersCustomers = new HashSet<>();
            for (int i = 0; i < customers.size(); i++) {
                if(customers.get(i) != null){
                    System.out.printf("%d.%s%n", i+1, customers.get(i).toString());
                       
                }
                setNumbersCustomers.add(i+1);
            }    
        
            return setNumbersCustomers;
    }

    private void editShoe() {
        Set<Integer> changeNumber = new HashSet<>();
        changeNumber.add(1);
        changeNumber.add(2);
        System.out.println("Изменение названия модели");
        Set<Integer> setNumbersShoes = printListShoes();
        if(setNumbersShoes.isEmpty()){
            return;
        }
        System.out.println("Выберите номер модели из списка: ");
        int chooseNum = chooseFromList(setNumbersShoes);
    
    
    }

    
}
