/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package finalfantasy;

import java.awt.Graphics;
import java.awt.Image;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Karan
 */
public class Shop extends JPanel{
        Scanner kb = new Scanner(System.in);
        String location;
        String inventory[]=new String[4];
        Boolean type[]=new Boolean[3];//true is an item, false is a weapon
        String display[]=new String[4];
        int cost[]=new int[3];
        Image backdrop =new ImageIcon("shop.png").getImage();
        
        public void paintComponent(Graphics g) {
        g.drawImage(backdrop,0,0,null);}
        
    Shop(String i){
        location=i;
        if(i.equalsIgnoreCase("Cornelia")){
            inventory[0]="Potion";cost[0]=40;type[0]=true;
            inventory[1]="Rapier";cost[1]=80;type[1]=false;
            inventory[2]="Phoenix Down";cost[2]=100;type[2]=true;
        }
        if(i.equalsIgnoreCase("Pravoka")){
            inventory[0]="Potion";cost[0]=40;type[0]=true;
            inventory[1]="Hammer";cost[1]=105;type[1]=false;
            inventory[2]="Phoenix Down";cost[2]=100;type[2]=true;
        }
        if(i.equalsIgnoreCase("Elfheim")){
            inventory[0]="Potion";cost[0]=40;type[0]=true;
            inventory[1]="Saber";cost[1]=160;type[1]=false;
            inventory[2]="Phoenix Down";cost[2]=100;type[2]=true;
        }
        for(int x=0;x<3;x++)
            display[x]=inventory[x]+ " (Cost: "+cost[x]+")";
        display[3]="Return";}
    
    void shopping(Party party){
            boolean shopLeave=false;
            
            JFrame shopScreen = new JFrame("Town");
        shopScreen.setBounds(200, 100,backdrop.getWidth(this),backdrop.getHeight(this));
        shopScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        shopScreen.setResizable(false);
        shopScreen.add(new Shop(location));
        shopScreen.setIconImage(backdrop);
        shopScreen.setVisible(true);
        
                   do{
                int input=JOptionPane.showOptionDialog(null, "What do you want to buy? (Current gil: "+party.gil+")", 
                "Shop", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null,display,display[3]);
                  
                if(display[input].equals("Return")){
                        shopLeave=true;
                        shopScreen.dispose();}
                else{
                        if(party.gil>=cost[input]){
                            party.addGil(-cost[input]);
                            if(type[input])
                                party.addItem(inventory[input]);
                            if(!type[input])//this happens if bought item is a weapon
                                party.addWeapon((inventory[input]));
                            }
                        else
                                JOptionPane.showMessageDialog(null, "Not enough gil!", "Transaction failure", JOptionPane.WARNING_MESSAGE);
    }
                }while(shopLeave==false);
        }
    }
    
