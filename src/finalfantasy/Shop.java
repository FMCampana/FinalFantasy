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
        String inventory[];
        String type[];
        String display[];
        int cost[];
        String info[];
                
        Image backdrop =new ImageIcon("shop.png").getImage();
        
        public void paintComponent(Graphics g) {
        g.drawImage(backdrop,0,0,null);}
        
    Shop(String i){
        location=i;
        if(i.equalsIgnoreCase("Crescent Lake")||i.equalsIgnoreCase("Pravoka")||i.equalsIgnoreCase("Elfheim")){//some towns have 4 items
            type=new String[4];inventory=new String[4];cost=new int[4];display=new String[6];info=new String[4];}
        else{//but most have 3
            type=new String[3];inventory=new String[3];cost=new int[3];display=new String[5];info=new String[3];}
        if(i.equalsIgnoreCase("Cornelia")){
            inventory[0]="Potion";cost[0]=40;type[0]="Item";info[0]="An item that heals a party member for 40 points.";
            inventory[1]="Rapier";cost[1]=80;type[1]="Weapon";info[1]="+4 STR, Equip: Warrior, Thief, Red Mage";
            inventory[2]="Phoenix Down";cost[2]=100;type[2]="Item";info[2]="An item that revives a party member.";
        }
        if(i.equalsIgnoreCase("Pravoka")){
            inventory[0]="Potion";cost[0]=40;type[0]="Item";info[0]="An item that heals a party member for 40 points.";
            inventory[1]="Hammer";cost[1]=105;type[1]="Weapon";info[1]="+4 STR, Equip: Warrior, White Mage";
            inventory[2]="Phoenix Down";cost[2]=100;type[2]="Item";info[2]="An item that revives a party member.";
            inventory[3]="Battle Axe";cost[3]=105;type[3]="Weapon";info[3]="+16 STR, -3 AGI, Equip: Warrior";
        }
        if(i.equalsIgnoreCase("Elfheim")){
            inventory[0]="Potion";cost[0]=40;type[0]="Item";info[0]="An item that heals a party member for 40 points.";
            inventory[1]="Saber";cost[1]=160;type[1]="Weapon";info[1]="+8 STR, Equip: Warrior, Thief, Red Mage";
            inventory[2]="Phoenix Down";cost[2]=100;type[2]="Item";info[2]="An item that revives a party member.";
            inventory[3]="Dagger";cost[3]=70;type[3]="Weapon";info[1]="+3 STR, +3 AGI, Equip: Warrior, Thief, Black Mage, Red Mage";
        }
        if(i.equalsIgnoreCase("Melmond")){
            inventory[0]="Potion";cost[0]=40;type[0]="Item";info[0]="An item that heals a party member for 40 points.";
            inventory[1]="Coral Sword";cost[1]=190;type[1]="Weapon";info[1]="+9 STR, Element: Thunder, Equip: Warrior, Thief, Red Mage";
            inventory[2]="Phoenix Down";cost[2]=100;type[2]="Item";info[2]="An item that revives a party member.";
        }
        if(i.equalsIgnoreCase("Lufenia")){
            inventory[0]="Potion";cost[0]=40;type[0]="Item";info[0]="An item that heals a party member for 40 points.";
            inventory[1]="Defender";cost[1]=700;type[1]="Weapon";info[1]="+12 STR, +100HP, Equip: Warrior";
            inventory[2]="Phoenix Down";cost[2]=100;type[2]="Item";info[2]="An item that revives a party member.";
        }
        if(i.equalsIgnoreCase("Crescent Lake")){
            inventory[0]="Potion";cost[0]=40;type[0]="Item";info[0]="An item that heals a party member for 40 points.";
            inventory[1]="Mythril Hammer";cost[1]=230;type[1]="Weapon";info[1]="+8 STR, Equip: Warrior, White Mage";
            inventory[2]="Phoenix Down";cost[2]=100;type[2]="Item";info[2]="An item that revives a party member.";
            inventory[3]="Blizzard Fang";cost[3]=100;type[3]="Item";info[3]="An item that deals ice damage to all foes.";
        }
        for(int x=0;x<inventory.length;x++)
            display[x]=inventory[x]+ " (Cost: "+cost[x]+")";
        display[display.length-2]="Info";
        display[display.length-1]="Return";
     }
     
    void shopping(Party party){
            boolean shopLeave=false;
            
            JFrame shopScreen = new JFrame("Welcome to the "+location+" Shop!");
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
                else if(display[input].equals("Info")){
                        showInfo();}
                else{
                        if(party.gil>=cost[input]){
                            party.addGil(-cost[input]);
                            if(type[input].equals("Item"))//this happens if bought item is an item
                                party.addItem(inventory[input]);
                            if(type[input].equals("Weapon"))//this happens if bought item is a weapon
                                party.addWeapon((inventory[input]));
                            }
                        else
                                JOptionPane.showMessageDialog(null, "Not enough gil!", "Transaction failure", JOptionPane.WARNING_MESSAGE);
    }
                }while(shopLeave==false);
        }
    
    void showInfo(){
        String message="";
        int x=0;
        for(String item: inventory){
            message=message+"\n"+inventory[x]+": "+info[x]+"\n";x++;}
        JOptionPane.showMessageDialog(null, message, 
                "Shop: Info", JOptionPane.PLAIN_MESSAGE);
    }
    }
    
