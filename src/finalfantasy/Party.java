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
    ArrayList<String> itemList = new ArrayList<String>();
    ArrayList<Weapon> weaponList = new ArrayList<Weapon>();
    ArrayList<Armor> armorList = new ArrayList<Armor>();
    Random generator = new Random();
    
    Party(){
    //gil=100;
    gil=1000;
    itemList.add("Potion");
    itemList.add("Phoenix Down");
    itemList.ensureCapacity(100);
    weaponList.ensureCapacity(100);
    armorList.ensureCapacity(100);}
    
    void rest(Character one, Character two, Character three, Character four){
        one.rest(); two.rest(); three.rest(); four.rest();
    }
    
    int useItem(Character one, Character two, Character three, Character four, Character user,monster a,monster b,monster c){
        int validChoice=0;
        
        ArrayList<String> tempItemList = itemList;
        if(!tempItemList.get(tempItemList.size()-1).equals("Return"))//if return is already in the arralist, don't add it!
        tempItemList.add("Return");
        Object[] tempItems = new String[tempItemList.size()+1];
        tempItems=tempItemList.toArray();
        
        int input = JOptionPane.showOptionDialog(null, "Select an item.", "Battle!", JOptionPane.DEFAULT_OPTION, 
                JOptionPane.QUESTION_MESSAGE, user.imageicon, tempItems, tempItems[0]);
        
        if("Potion".equals(itemList.get(input))){//heals a party member for 40 points
                
            while(validChoice==0){
            
            String[] members = new String[5-one.death-two.death-three.death-four.death];
            
            int x=0;
            
            Character[] players = {one,two,three,four};
            for(Character player:players)//shows data for each party member
                if(player.death==0){
                    members[x]=player.name + " (HP: " + player.tempHP + "/" + player.hp +")";x++;}
            members[x]="Return";
        
            int target= JOptionPane.showOptionDialog(null, "Select a character to use the "+itemList.get(input)+" on:", 
                "Final Fantasy", JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE, user.imageicon, members, members[0]);
            
            if(members[target].equals(one.name + " (HP: " + one.tempHP + "/" + one.hp +")")&&one.death==0){one.getHit(-40,null);validChoice=1;remove("item","Potion");}
            else if(members[target].equals(two.name + " (HP: " + two.tempHP + "/" + two.hp +")")&&two.death==0){two.getHit(-40,null);validChoice=1;remove("Item","Potion");}
            else if(members[target].equals(three.name + " (HP: " + three.tempHP + "/" + three.hp +")")&&three.death==0){three.getHit(-40,null);validChoice=1;remove("Item","Potion");}
            else if(members[target].equals(four.name + " (HP: " + four.tempHP + "/" + four.hp +")")&&four.death==0){four.getHit(-40,null);validChoice=1;remove("Item","Potion");}
            else if(members[x]=="Return"){validChoice=2;}
            }
        }
        
        else if("Return".equals(itemList.get(input)))
            validChoice=2;
        
        else if("Phoenix Down".equals(itemList.get(input))){//revives fallen party member
                
            while(validChoice==0){
            
            String[] members = new String[1+one.death+two.death+three.death+four.death];
 
            int x=0;
            
            Character[] players = {one,two,three,four};
            for(Character player:players)//shows data for each party member
                if(player.death==0){
                    members[x]=player.name + " (HP: " + player.tempHP + "/" + player.hp +")";x++;}
            members[x]="Return";
            
            int target= JOptionPane.showOptionDialog(null, "Select a character to use the "+itemList.get(input)+" on:", 
                "Final Fantasy", JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE, user.imageicon, members, members[0]);
            
            if(members[target].equals(one.name + " (HP: " + one.tempHP + "/" + one.hp +")")&&one.death==1){one.revive();validChoice=1;remove("Item","Phoenix Down");}
            else if(members[target].equals(two.name + " (HP: " + two.tempHP + "/" + two.hp +")")&&two.death==1){two.revive();validChoice=1;remove("Item","Phoenix Down");}
            else if(members[target].equals(three.name + " (HP: " + three.tempHP + "/" + three.hp +")")&&three.death==1){three.revive();validChoice=1;remove("Item","Phoenix Down");}
            else if(members[target].equals(four.name + " (HP: " + four.tempHP + "/" + four.hp +")")&&four.death==1){four.revive();validChoice=1;remove("Item","Phoenix Down");}
            else if(members[x]=="Return"){validChoice=2;}
            }}
        
        else if("Blizzard Fang".equals(itemList.get(input))){
            int blizz;//fire damage
        JLabel[] message = new JLabel[3];
        monster[] monsters = {a,b,c};
        int i=0;
            for(monster enemy:monsters){
            if(enemy.death==0){blizz=user.mag+generator.nextInt(5);
                enemy.getHit(blizz,false,user.name,"Ice",false);//reduce enemy hp based on fire value
                if(enemy.tempHP<=0)
                    message[i]=new JLabel(enemy.name+" took "+blizz+" damage. "+enemy.name+" was slain!",enemy.image,SwingConstants.LEFT);
                else if(enemy.weakness.equalsIgnoreCase("Fire")){
                    message[i]=new JLabel(enemy.name+" took "+blizz+" damage! Weak to ice!",enemy.image,SwingConstants.LEFT);}
                else
                    message[i]=new JLabel(enemy.name+" took "+blizz+" damage!",enemy.image,SwingConstants.LEFT);}i++;}
            JOptionPane.showMessageDialog(null,message,user.name+" cast Blizzard!",JOptionPane.DEFAULT_OPTION);
            remove("item","Blizzard Fang");
    }else if("Cura Scroll".equals(itemList.get(input))){
            user.cure(user, one, two, three, four,true);
            remove("item","Cura Scroll");
    }
        
        return validChoice;//If validchoice==2, then the menu will restart
    }
    
    void remove(String ioW, String removable){
        if(ioW.equalsIgnoreCase("Item"))
                itemList.remove(removable);
        if(ioW.equalsIgnoreCase("Weapon"))
                weaponList.remove(removable);
        if(ioW.equalsIgnoreCase("Armor"))
                weaponList.remove(removable);
    }
    void remove(String ioW, int removable){
        if(ioW.equalsIgnoreCase("Item"))
                itemList.remove(removable);
        if(ioW.equalsIgnoreCase("Weapon"))
                weaponList.remove(removable);
        if(ioW.equalsIgnoreCase("Armor"))
                weaponList.remove(removable);
    }
    
    void equipWeapon(Character one, Character two, Character three, Character four){
        
        int weaponArraySize=1;
        
        for(Weapon weap: weaponList)
           if(!weap.equals("None"))
            weaponArraySize++;
        
        String[] weaponArray = new String[weaponArraySize];
       
       for(int i=0;i<weaponList.size();i++)
           if(!weaponList.get(i).equals("None"))
            weaponArray[i]=weaponList.get(i).name;//places the weapons into the weapon array
       
            weaponArray[weaponList.size()] = "Return";
            
            //weaponList = permanent inventory of Weapons, and not the stupid FF7 kind
            //weaponArray = temporary String array of weapon names + Return
            
        input= JOptionPane.showOptionDialog(null, "Select a weapon:", 
                "Equip Screen", JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE, ImageFetcher.fetchIcon("mog.gif"), 
                weaponArray, weaponArray[0]);
       String weaponName;
        if(!weaponArray[input].equals("None")&&!weaponArray[input].equals("Return")){
            weaponName=weaponArray[input];
            
            Weapon selectedWeapon = new Weapon(weaponName);
            
            Object[] characterArray = {one.name, two.name, three.name, four.name, "Return"};
            
            int character=5;
            
            while(character!=4){//as long as the player does not select Return
            
            character= JOptionPane.showOptionDialog(null, "Select a character:", 
                "Equip Screen", JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE, ImageFetcher.fetchIcon("mog.gif"), 
                characterArray, characterArray[4]);
            
            Character selectedChar=null;
            
            if(character==0)
                selectedChar=one;
            if(character==1)
                selectedChar=two;
            if(character==2)
                selectedChar=three;
            if(character==3)
                selectedChar=four;
            
            if(character!=4){
                if(selectedWeapon.equipCheck[selectedChar.job-1]==true){//if the character can equip this weapon
                    weaponList.add(selectedChar.equip(weaponList.get(input)));//adds the previous weapon
                    remove("Weapon",input);;//removes weapon from inventory
                    break;}//breaks out of the while loop once a weapon has been equipped
                else
                JOptionPane.showMessageDialog(null, selectedChar.name+" cannot equip " + weaponArray[input], "Equip Screen", 0, selectedChar.imageicon);
            
            }
            }
    }
    }
    
    void addItem(String x){
        itemList.add(x);
       }
    
    void addWeapon(String y){
        weaponList.add(new Weapon(y));
        JOptionPane.showMessageDialog(null, "Obtained "+y+"!\n\nCurrent gil: "+gil, "You got a weapon!", JOptionPane.INFORMATION_MESSAGE, ImageFetcher.fetchIcon("mog.gif"));
    }
    
    void addGil(int g){
        gil+=g;
    }
    
    String weaponListToString(){
        String returnedString="";
        
        for (Weapon leftArm : weaponList)
            returnedString=leftArm.name;
        
        return returnedString;
    }
    String armorListToString(){
        String returnedString="";
        
        for (Armor rightLeg : armorList)
            returnedString=rightLeg.name;
        
        return returnedString;
    }
    
    void status(Character one, Character two, Character three, Character four){
        String[] members = {
           one.name+": Lv. "+one.lv+" "+one.jobclass, 
           two.name+": Lv. "+two.lv+" "+two.jobclass,  
           three.name+": Lv. "+three.lv+" "+three.jobclass, 
           four.name+": Lv. "+four.lv+" "+four.jobclass, 
           "Equip Weapons","Continue"};
        // ^ lists all party members ^
        
        int choice=0;
        while(choice!=5){
            choice= JOptionPane.showOptionDialog(null, "Your gil: "+gil+"\nYour items: "+itemList+"\nYour weapons: "+weaponListToString()+"\nYour armor: "+armorListToString(), 
                "Party Info", JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE, ImageFetcher.fetchIcon("Mog.gif"), members, members[5]);
            if(choice==0)
                one.display();
            if(choice==1)
                two.display();
            if(choice==2)
                three.display();
            if(choice==3)
                four.display();
            if(choice==4){
                equipWeapon(one, two, three, four);}
        }
        
    }
}
