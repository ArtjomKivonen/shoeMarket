package shoemarket;

import myclasses.App;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author artie
 */
public class ShoeMarket {


    public static void main(String[] args) {
        if(args.length==0){
            App.isBase = true;
        }else{
            App.isBase = false;
        }
        
        App app = new App();
        app.run();
        
        // TODO code application logic here
    }
    
}
