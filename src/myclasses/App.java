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
import java.util.Date;
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
    public static boolean isBase;
    Scanner scanner = new Scanner(System.in);
    List<Shoe> shoes = new ArrayList<>();
    List<Customer> customers = new ArrayList<>();
    List<Purchase> purchases = new ArrayList<>();
//    Retentive saver = new FileSaver();
    Retentive saver;
    public App(){
//        if(App.isBase){
//            saver = new BaseSaver();
//        }else{
            saver = new FileSaver();
//        }
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
            System.out.println("7: Доход магазина за указанный месяц");
            System.out.println("8: Редактировать товар магазина");
            System.out.println("9: Редактировать покупателя");
            
            
            int menu = intInputCheck();
            
            
            switch (menu){
            
                case 0:
                    appLive="q";
                    break;
                case 1:
                    addShoe();
                    break;
                case 2:
                    printListShoes();
                    break;
                case 3:
                    addCustomer();
                    break;
                case 4:
                    printListCustomers();
                    break;
                case 5:
                    addPurchase();
                    break;
                case 6:
                    double countIncome=incomeCount();
                    System.out.println("Доход магазина за время работа="+countIncome+" euro");
                    break;
                case 7:
                    countIncomeMonth();
                    break;
                case 8:
                    editShoe();
                    break;
                    
                case 9:
                    editCustomer();
                    break;
            }
            
            }while("y".equals(appLive));
        }
    
        private void addShoe(){
            System.out.println("Добавление модели обуви");
            if(quit()) return;
            Shoe shoe = new Shoe();
            System.out.println("Название модели:");
            shoe.setName(scanner.nextLine());
            System.out.println("Название бренда:");
            shoe.setBrand(scanner.nextLine());
            System.out.println("Цена модели:");
            shoe.setPrice(intInputCheck());
            shoes.add(shoe);
            saver.saveShoes(shoes);
        }
        
        private void addCustomer(){
            System.out.println("Добавление покупателя");
            if(quit()) return;
            Customer customer = new Customer();
            System.out.println("Введите имя покупателя:");
            customer.setFname(scanner.nextLine());
            System.out.println("Введите фамилию покупателя:");
            customer.setLname(scanner.nextLine());
            System.out.println("Введите количество денег покупателя(eurocents):");
            customer.setFunds(intInputCheck());
            customers.add(customer);
            saver.saveCustomers(customers);
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
            int shoeNum = chooseFromList(setNumbersShoes);
            purchase.setShoe(shoes.get(shoeNum-1));
            purchase.setCost(shoes.get(shoeNum-1).getPrice());
            System.out.println();
            Set<Integer> setNumbersCustomers = printListCustomers();
            System.out.println("Введите номер покупателя: ");
            int customerNum = chooseFromList(setNumbersCustomers);
            if(customers.get(customerNum - 1).getFunds() < shoes.get(shoeNum - 1).getPrice()){
                System.out.println("Недостаточно денег у покупателя, покупка не оформлена");
                return;
            }
            purchase.setCustomer(customers.get(customerNum-1));
            System.out.println();
            customers.get(customerNum - 1).setFunds
                    (customers.get(customerNum - 1).getFunds()
                            - shoes.get(shoeNum - 1).getPrice());
            Calendar c = new GregorianCalendar();
            purchase.setPurchaseDate(c.getTime());
            purchase.setCost(shoes.get(shoeNum - 1).getPrice());
            purchases.add(purchase);
            saver.savePurchases(purchases);
        
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
        System.out.println("Название модели: "+shoes.get(chooseNum - 1).getName());
        System.out.println("Если хотите изменить - введите 1, пропустить - введите 2");
        int change = chooseFromList(changeNumber);
        if(change == 1){
            System.out.println("Введите новое название модели обуви: ");
            shoes.get(chooseNum - 1).setName(scanner.nextLine());
        }
        System.out.println("Бренд обуви: "+shoes.get(chooseNum - 1).getBrand());
        System.out.println("Если хотите изменить - введите 1, пропустить - введите 2");
        change = chooseFromList(changeNumber);
        if(change == 1){
            System.out.println("Введите новый бренд обуви: ");
            shoes.get(chooseNum - 1).setBrand(scanner.nextLine());
        }
        System.out.println("Цена модели: "+shoes.get(chooseNum - 1).getPrice());
        System.out.println("Если хотите изменить - введите 1, пропустить - введите 2");
        change = chooseFromList(changeNumber);
        if (change == 1){
            System.out.println("Введите новую цену обуви");
            shoes.get(chooseNum - 1).setPrice(intInputCheck());
        }
        saver.saveShoes(shoes);
    }

    private void editCustomer() {
        Set<Integer> changeNumber = new HashSet<>();
        changeNumber.add(1);
        changeNumber.add(2);
        System.out.println("---Изменение имени покупателя---");
        Set<Integer> setNumbersCustomers = printListCustomers();
        if(setNumbersCustomers.isEmpty()) return;
        int chooseNum = chooseFromList(setNumbersCustomers);
        System.out.println("Имя покупателя: "+customers.get(chooseNum - 1).getFname());
        System.out.println("Если хотите изменить - введите 1, пропустить - введите 2");
        int change = chooseFromList(changeNumber);
        if(change == 1){
            System.out.println("Введите новое имя покупателя: ");
            customers.get(chooseNum - 1).setFname(scanner.nextLine());
        }
        System.out.println("---Изменение фамилии покупателя---");
        System.out.println("Фамилия покупателя: "+customers.get(chooseNum - 1).getLname());
        System.out.println("Если хотите изменить - введите 1, пропустить - введите 2");
        change = chooseFromList(changeNumber);
        if(change == 1){
            System.out.println("Введите новую фамилию покупателя: ");
            customers.get(chooseNum - 1).setLname(scanner.nextLine());
        }
        System.out.println("---Изменение количества денег покупателя---");
        System.out.println("Количество денег покупателя: "+customers.get(chooseNum - 1).getFunds());
        System.out.println("Если хотите изменить - введите 1, пропустить - введите 2");
        change = chooseFromList(changeNumber);
        if(change == 1){
            System.out.println("Введите новое количество денег покупателя: ");
            customers.get(chooseNum - 1).setFunds(intInputCheck());
        }
        saver.saveCustomers(customers);
    }

    private void countIncomeMonth() {
        System.out.println(purchases.get(0).getPurchaseDate());
        double incomeMonth = 0;
        Calendar c = new GregorianCalendar();
        Date todaysDate = c.getTime();
        int todaysMonth = todaysDate.getMonth();
        int todaysYear = todaysDate.getYear();
        for (int i = 0; i < purchases.size(); i++) {
            if(purchases.get(i) != null &&
                    todaysMonth == purchases.get(i).getPurchaseDate().getMonth() &&
                    todaysYear == purchases.get(i).getPurchaseDate().getYear()){
                incomeMonth = incomeMonth + purchases.get(i).getCost();
            }
        }
        incomeMonth = incomeMonth / 100;
        System.out.println("Доход магазина за указанный месяц в euro: "+incomeMonth);
    }
}
