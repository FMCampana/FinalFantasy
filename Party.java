/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package finalfantasy;
import java.util.*;
import javax.swing.*;
import java.awt.Image.*;
import java.awt.Component.*;
/**
 *
 * @author Karan
 */
public class Party {
    int gil, input;
    ArrayList itemList = new ArrayList();
    ArrayList<Weapon> weaponList = new ArrayList<Weapon>();
    Scanner kb = new Scanner(System.in);
    
    Party(){
    gil=100;
    itemList.add("Potion");
    itemList.add("Phoenix Down");
    itemList.ensureCapacity(100);
    weaponList.ensureCapacity(100);}
    
    void rest(Character one, Character two, Character three, Character four){
        one.rest(); two.rest(); three.rest(); four.rest();
    }
    
    int useItem(Character one, Character two, Character three, Character four, Character user){
        int validChoice=0;
        
        ArrayList tempItemList = itemList;
        if(!tempItemList.get(tempItemList.size()-1).equals("Return"))//if return is already in the arralist, don't add it!
        tempItemList.add("Return");
        Object[] tempItems = new String[tempItemList.size()+1];
        tempItems=tempItemList.toArray();
        
        int input = JOptionPane.showOptionDialog(null, "Select an item.", "Battle!", JOptionPane.DEFAULT_OPTION, 
                JOptionPane.QUESTION_MESSAGE, user.image, tempItems, tempItems[0]);
        
        if("Potion".equals(itemList.get(input))){//heals a party member for 40 points
                
            while(validChoice==0){
            
            String[] members = new String[5-one.death-two.death-three.death-four.death];
            
            int x=0;
            
            if(one.death==0){
            members[x]=one.name + " (HP: " + one.tempHP + "/" + one.hp +")";x++;}
            if(two.death==0){
            members[x]=two.name + " (HP: " + two.tempHP + "/" + two.hp +")";x++;}
            if(three.death==0){
            members[x]=three.name + " (HP: " + three.tempHP + "/" + three.hp +")";x++;}
            if(four.death==0){
            members[x]=four.name + " (HP: " + four.tempHP + "/" + four.hp +")";x++;}
            members[x]="Return";
        
            int target= JOptionPane.showOptionDialog(null, "Select a character to use the "+itemList.get(input)+" on:", 
                "Final Fantasy", JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE, user.image, members, members[0]);
            
            if(members[target].equals(one.name + " (HP: " + one.tempHP + "/" + one.hp +")")&&one.death==0){one.getHit(-40,null);validChoice=1;itemList.remove("Potion");}
            else if(members[target].equals(two.name + " (HP: " + two.tempHP + "/" + two.hp +")")&&two.death==0){two.getHit(-40,null);validChoice=1;itemList.remove("Potion");}
            else if(members[target].equals(three.name + " (HP: " + three.tempHP + "/" + three.hp +")")&&three.death==0){three.getHit(-40,null);validChoice=1;itemList.remove("Potion");}
            else if(members[target].equals(four.name + " (HP: " + four.tempHP + "/" + four.hp +")")&&four.death==0){four.getHit(-40,null);validChoice=1;itemList.remove("Potion");}
            else if(members[x]=="Return"){validChoice=2;}
            }
        }
        
        else if("Return".equals(itemList.get(input)))
            validChoice=2;
        
        else if("Phoenix Down".equals(itemList.get(input))){//revives fallen party member
                
            while(validChoice==0){
            
            String[] members = new String[1+one.death+two.death+three.death+four.death];
 
            int x=0;
            
            if(one.death==1){
            members[x]=one.name + " (HP: " + one.tempHP + "/" + one.hp +")";x++;}
            if(two.death==1){
            members[x]=two.name + " (HP: " + two.tempHP + "/" + two.hp +")";x++;}
            if(three.death==1){
            members[x]=three.name + " (HP: " + three.tempHP + "/" + three.hp +")";x++;}
            if(four.death==1){
            members[x]=four.name + " (HP: " + four.tempHP + "/" + four.hp +")";x++;}
            members[x]="Return";
            
            int target= JOptionPane.showOptionDialog(null, "Select a character to use the "+itemList.get(input)+" on:", 
                "Final Fantasy", JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE, user.image, members, members[0]);
            
            if(members[target].equals(one.name + " (HP: " + one.tempHP + "/" + one.hp +")")&&one.death==1){one.revive();validChoice=1;itemList.remove("Phoenix Down");}
            else if(members[target].equals(two.name + " (HP: " + two.tempHP + "/" + two.hp +")")&&two.death==1){two.revive();validChoice=1;itemList.remove("Phoenix Down");}
            else if(members[target].equals(three.name + " (HP: " + three.tempHP + "/" + three.hp +")")&&three.death==1){three.revive();validChoice=1;itemList.remove("Phoenix Down");}
            else if(members[target].equals(four.name + " (HP: " + four.tempHP + "/" + four.hp +")")&&four.death==1){four.revive();validChoice=1;itemList.remove("Phoenix Down");}
            else if(members[x]=="Return"){validChoice=2;}
            }}
        
        return validChoice;//If validchoice==2, then the menu will restart
    }
    
    void showWeapons(){
        for(int i = 0; i<weaponList.size(); i++){
            System.out.println(i + 1 + ". " + weaponList.get(i));
        }
    }
    
    void equip(Character one, Character two, Character three, Character four){
        
        String[] weaponArray = new String[weaponList.size()+1];
       
       for(int i=0;i<weaponList.size();i++)
        weaponArray[i]=weaponList.get(i).name;//places the weapons into the weapon array
       
            weaponArray[weaponList.size()] = "Return";
            
            //weaponList = permanent inventory of Weapons, and not the stupid FF7 kind
            //weaponArray = temporary String array of weapon names + Return
            
        input= JOptionPane.showOptionDialog(null, "Select a weapon:", 
                "Equip Screen", JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE, new ImageIcon("mog.gif"), 
                weaponArray, weaponArray[weaponList.size()-1]);
       
        if("Rapier".equals(weaponArray[input])){
            
            Object[] characterArray = {one.name, two.name, three.name, four.name, "Return"};
            
            int character= JOptionPane.showOptionDialog(null, "Select a character:", 
                "Equip Screen", JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE, new ImageIcon("mog.gif"), 
                characterArray, characterArray[4]);
            
            if(character==0){
                if(one.job==1||one.job==2||one.job==6){
                    
                weaponList.add(one.equip(weaponList.get(input)));//adds the previous weapon
                
                JOptionPane.showMessageDialog(null, one.name+" equipped " + weaponArray[input], "Equip Screen", 0, one.image);
                weaponList.remove(input);   }
                else
                JOptionPane.showMessageDialog(null, one.name+" cannot equip " + weaponArray[input], "Equip Screen", 0, one.image);
            }
            if(character==1){
                if(two.job==1||two.job==2||two.job==6){
                    
                weaponList.add(two.equip(weaponList.get(input)));//adds the previous weapon
                
                JOptionPane.showMessageDialog(null, two.name+" equipped " + weaponArray[input], "Equip Screen", 0, two.image);
                weaponList.remove(input);   }
                else
                JOptionPane.showMessageDialog(null, two.name+" cannot equip " + weaponArray[input], "Equip Screen", 0, two.image);
            }
            if(character==2){
                if(three.job==1||three.job==2||three.job==6){
                    
                weaponList.add(three.equip(weaponList.get(input)));//adds the previous weapon
                
                JOptionPane.showMessageDialog(null, three.name+" equipped " + weaponArray[input], "Equip Screen", 0, three.image);
                weaponList.remove(input);   }
                else
                JOptionPane.showMessageDialog(null, three.name+" cannot equip " + weaponArray[input], "Equip Screen", 0, three.image);
            }
             if(character==3){
                if(four.job==1||four.job==2||four.job==6){
                    
                weaponList.add(four.equip(weaponList.get(input)));//adds the previous weapon
                
                JOptionPane.showMessageDialog(null, four.name+" equipped " + weaponArray[input], "Equip Screen", 0, four.image);
                weaponList.remove(input);   }
                else
                JOptionPane.showMessageDialog(null, four.name+" cannot equip " + weaponArray[input], "Equip Screen", 0, four.image);
            }
    }
    }
    
    void addItem(String x){
        itemList.add(x);
       }
    
    void addWeapon(String x){
        weaponList.add(new Weapon(x));
        JOptionPane.showMessageDialog(null, "Obtained "+x+"!\n\nCurrent gil: "+gil, "You got a weapon!", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("mog.gif"));
    }
    
    void addGil(int g){
        gil+=g;
    }
    
    String weaponLustToString(){
        String returnedString="";
        
        for (Weapon deliciousLeftArm : weaponList) 
            returnedString=deliciousLeftArm.name;
        
        return returnedString;
    }
    
    void status(Character one, Character two, Character three, Character four){
        String[] members = {
           one.name+": Lv. "+one.lv+" "+one.jobclass, 
           two.name+": Lv. "+two.lv+" "+two.jobclass,  
           three.name+": Lv. "+three.lv+" "+three.jobclass, 
           four.name+": Lv. "+four.lv+" "+four.jobclass, 
           "Continue"};
        // ^ lists all party members ^
        
        int choice=0;
        while(choice!=4){
            choice= JOptionPane.showOptionDialog(null, "Your gil: "+gil+"\nYour items: "+itemList+"\nYour weapons: "+weaponLustToString(), 
                "Party Info", JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE, new ImageIcon("mog.gif"), members, members[4]);
            if(choice==0)
                one.display();
            if(choice==1)
                two.display();
            if(choice==2)
                three.display();
            if(choice==3)
                four.display();
        }
    }
}
