/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package finalfantasy;

import java.awt.Font;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author karanshukla
 */
public class Character {
    
    int lv = 1, hp = 0, str = 0, mag = 0, agi = 0, job = 0, speed = 0, tempHP = hp, hit, atk=0, covername = 0,
            exp = 0, death=0;
    
    int levelUp;//Determines the chance of stat increasing upon level up
    
    String name = " ", jobclass = " ", ability = " ", ability2 = " ";
    
    Weapon weapon=new Weapon("None");
    Armor armor=new Armor("None");
            
    ImageIcon image;
    boolean focus=false,blind=false;
    Random generator = new Random();
    Scanner kb = new Scanner(System.in);
    String[] commands = new String[4];
    String[] commandsRed = new String[5];
    
    
    //job is the job integer identifier, jobclass is the job's actual name
    
    //Constructor Method
    Character(int lv, int hp, int str, int mag, int agi, String jobclass){
        lv = lv;
        hp = hp;
        tempHP = hp;
        str = str;
        mag = mag;
        agi = agi;
        jobclass = jobclass;
      
    }
    Character(){
    
    }
    
    void name(String input, int cover){
        name = input;
        covername = cover;
    }
    
    int validChoice;
            
    //combatInput is the actual action performed in combat
    void combatInput(Combat combat, monster a, monster b, monster c, Character one, Character two, Character three,
            Character four, Party party){
        do{
        int command = combat.input(this,a,b,c);//This is the interface for combat
        
        if(command<1){//if no input was given, turn is skipped
            JOptionPane.showMessageDialog(null,name + " did nothing.","Battle!",JOptionPane.DEFAULT_OPTION,image);
            validChoice=1;}
           
        else if(command==1){
            
            hit = combat.attack(str,atk);
            boolean miss=false;
            
            if(blind)
                if(generator.nextInt(3)==0)
                    miss=true;//if blinded, there's a 1/3 chance of attack missing
            
                validChoice=0; //This will reset the menu if the player chooses a dead or non-existent target
                
            while(validChoice==0){
                
                String[] enemies = new String[4-a.death-b.death-c.death];
                int x=0;
                monster[] foes = {a,b,c};
                
                for(monster targeted:foes){//assigns foes to enemy array
                    if(targeted.death==0){
                        enemies[x]=targeted.name + " (HP: " + targeted.tempHP + "/" + targeted.hp +")";
                        x++;}
                }
                enemies[x]="Return";
            
            int target=JOptionPane.showOptionDialog(null, "Choose which monster to attack.", 
                "Battle!", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, image, enemies, enemies[0]);

            for(monster targeted:foes){
            if(enemies[target].startsWith(targeted.name)&&targeted.death==0){
                if (focus==true){
                    hit*=2.5;
                    focus=false;}
                targeted.getHit(hit,true,name,weapon.weaponElement,miss);
                validChoice=1;}
            }
            if(enemies[target].equalsIgnoreCase("Return")){validChoice=2;}//returns to previous menu
            }
        }
        else if(command==2 && ability.equalsIgnoreCase("Fire")){
            fire(mag,this,a,b,c);
            validChoice=1;}
        
        else if(commands[command-1].equalsIgnoreCase("Cure")//if white mage
                ||commandsRed[command-1].equalsIgnoreCase("Cure")){//if red mage
            cure(this,one,two,three,four,false);
            validChoice=1;}
        
        else if(commands[command-1].equalsIgnoreCase("Check Status")){
            combat.checkStatus(one,two,three,four,a,b,c);
            validChoice=2;}
        
        else if(command==2 && ability.equalsIgnoreCase("Steal")){
           int steal = generator.nextInt(lv*30);
           String item="";
           boolean gilOrItem = generator.nextBoolean();
           
           if(gilOrItem){
           System.out.println(name + " stole " + steal + " gil!");
            JOptionPane.showMessageDialog(null,name + " stole " + steal + " gil!","Battle!",JOptionPane.DEFAULT_OPTION,image);
           party.addGil(steal);}
           
           if(!gilOrItem){
               if(steal<=lv*15)
            item="Potion";
               if(steal>=lv*15)
            item="Phoenix Down";
            JOptionPane.showMessageDialog(null,name + " stole " + item + "!","Battle!",JOptionPane.DEFAULT_OPTION,image);
            party.addItem(item);}
           
           validChoice=1;}
        else if(command==2 && ability.equalsIgnoreCase("Cover")){
           if(combat.cover!=covername){
            JOptionPane.showMessageDialog(null,name + " is covering the party!","Battle!",JOptionPane.DEFAULT_OPTION,image);
           combat.cover(covername);
           validChoice=1;}
           else{
            JOptionPane.showMessageDialog(null,name + " is no longer covering the party!","Battle!",JOptionPane.DEFAULT_OPTION,image);
           combat.cover(0);//removes cover
           validChoice=2;
           }}
        else if(command==2 && ability.equalsIgnoreCase("Focus")){
            if(focus==false){
            JOptionPane.showMessageDialog(null,name + " is focusing!","Battle!",JOptionPane.DEFAULT_OPTION,image);
           focus = true;
           validChoice=1;}
            else{
            JOptionPane.showMessageDialog(null,name + " is already focusing!","Battle!",JOptionPane.DEFAULT_OPTION,image);
           validChoice=2;
            }}
        else if(command==3 && job!=6){//item use
           validChoice = party.useItem(one, two, three, four, this,a,b,c);}
        else if(command==4 && job==6){
           validChoice = party.useItem(one, two, three, four, this,a,b,c);}
        }while(validChoice==2);
    }
    
    void fire(int mag, Character caster, monster a, monster b, monster c){
        int fire;//fire damage
        JLabel[] message = new JLabel[3];
        monster[] monsters = {a,b,c};
        int i=0;
            for(monster enemy:monsters){
            if(enemy.death==0){fire=caster.mag+generator.nextInt(5);
                enemy.getHit(fire,false,name,"Fire",false);//reduce enemy hp based on fire value
                if(enemy.tempHP<=0)
                    message[i]=new JLabel(enemy.name+" took "+fire+" damage. "+enemy.name+" was slain!",enemy.image,SwingConstants.LEFT);
                else if(enemy.weakness.equalsIgnoreCase("Fire")){
                    message[i]=new JLabel(enemy.name+" took "+fire+" damage! Weak to fire!",enemy.image,SwingConstants.LEFT);}
                else
                    message[i]=new JLabel(enemy.name+" took "+fire+" damage!",enemy.image,SwingConstants.LEFT);}i++;}
            JOptionPane.showMessageDialog(null,message,caster.name+" cast Fire!",JOptionPane.DEFAULT_OPTION);
    }
    
    void cure(Character caster, Character a, Character b, Character c, Character d, boolean cura){
        int cure;//healing value
        JLabel[] message = new JLabel[4];
            
            if(a.death==0){cure = caster.mag-generator.nextInt(5);
                a.getHit(-cure,null);//heal based on cure value
                message[0]=new JLabel(a.name+" was healed for "+cure+" points!",a.image,SwingConstants.LEFT);}
            if(b.death==0){cure = caster.mag-generator.nextInt(5);
                b.getHit(-cure,null);//heal based on cure value
                message[1]=new JLabel(b.name+" was healed for "+cure+" points!",b.image,SwingConstants.LEFT);}
            if(c.death==0){cure = caster.mag-generator.nextInt(5);
                c.getHit(-cure,null);//heal based on cure value
                message[2]=new JLabel(c.name+" was healed for "+cure+" points!",c.image,SwingConstants.LEFT);}
            if(d.death==0){cure = caster.mag-generator.nextInt(5);
                d.getHit(-cure,null);//heal based on cure value
                message[3]=new JLabel(d.name+" was healed for "+cure+" points!",d.image,SwingConstants.LEFT);}
            if(cura)
                JOptionPane.showMessageDialog(null,message,caster.name+" used the Cura Scroll!",JOptionPane.DEFAULT_OPTION);
            else
                JOptionPane.showMessageDialog(null,message,caster.name+" cast Cure!",JOptionPane.DEFAULT_OPTION);
    }
    
    Weapon equip(Weapon newWeapon){
        
        Weapon oldWeapon = weapon;
        weapon = newWeapon;
        
        JOptionPane.showMessageDialog(null,name+" equipped the " + newWeapon.name,"Final Fantasy",JOptionPane.DEFAULT_OPTION, image);
            
        if(oldWeapon.bonusStat.equalsIgnoreCase("hp")){//removes bonus effect from old weapon
            hp-=weapon.bonus;rest();}
        else if(oldWeapon.bonusStat.equalsIgnoreCase("agi"))
            agi-=weapon.bonus;
        
        if(weapon.bonusStat.equalsIgnoreCase("hp")){//adds bonus effect from new weapon
            hp+=weapon.bonus;rest();}
        else if(weapon.bonusStat.equalsIgnoreCase("agi"))
            agi+=weapon.bonus;
        
        return oldWeapon;//The old weapon is returned to the inventory, while the new one is equipped
    }
    Armor equip(Armor newArmor){
        
        Armor oldArmor = armor;
        armor = newArmor;
        
        JOptionPane.showMessageDialog(null,name+" equipped the " + newArmor.name,"Final Fantasy",JOptionPane.DEFAULT_OPTION, image);
         
        return oldArmor;//The old weapon is returned to the inventory, while the new one is equipped
    }
    
    void rest(){//resets to full health
        tempHP=hp;
        death=0;
        focus=false;
        blind=false;//BLIND IS NOT DONE WORK ON THIS NAO
    }
    
    void inflictStatus(String status){//take a status effect
        if(status.equals("Blind"))blind=true;
    }
    
    void takeDamage(int hit){//used for taking damage from other attacks
        tempHP-=hit;
        if(tempHP<=0){//This occurs if damage exceeds temporary HP
            death=1;
            tempHP=0;
        }
    }
    
    void getHit(int hit, monster mon){//used for taking damage from basic physical attacks
        
        tempHP = tempHP - hit;
        if(hit>0){//This occurs if character is damaged
            JLabel[] message = new JLabel[4];//message displayed when physically hit
        
            message[0]=new JLabel(mon.name+" attacks!",mon.image,SwingConstants.LEFT);
            message[1]=new JLabel("");
            message[1]=new JLabel("");
            message[2]=new JLabel(name + " took " + hit + " damage!",image,SwingConstants.LEFT);
        if(tempHP<=0){//This occurs if damage exceeds temporary HP
            death=1;
            tempHP=0;
            message[3]=new JLabel(name + " was knocked out!");
        }
        JOptionPane.showMessageDialog(null,message,mon.name +" attacks!",JOptionPane.DEFAULT_OPTION);//outputs the message
        }
        if(hit<0){//This occurs if character is healed
            int heal = hit * -1;
        if(tempHP>hp)//This occurs if healing exceeds maxiumum HP
            tempHP=hp;
        }
    }
    
    void job(int classjob){
        if(classjob==1)
        {jobclass = "Warrior";
        job = classjob;
        image = new ImageIcon("Warrior.png","Warrior");
    }   if(classjob==2)
        {jobclass = "Thief";
        job = classjob;
        image = new ImageIcon("Thief.png","Thief");
    }   if(classjob==3)
        {jobclass = "Monk";
        job = classjob;
        image = new ImageIcon("Monk.png","Monk");
    }   if(classjob==4)
        {jobclass = "BlackMage";
        job = classjob;
        image = new ImageIcon("BlackMage.png","Black Mage");
    }   if(classjob==5)
        {jobclass = "WhiteMage";
        job = classjob;
        image = new ImageIcon("WhiteMage.png","White Mage");
    }   if(classjob==6)
        {jobclass = "RedMage";
        job = classjob;
        image = new ImageIcon("RedMage.png","Red Mage");
    }
}
   
    void calculate(){//calculates stats
        if(job==1){
        hp = 82;
        str = 7;
        mag = 2;
        agi = 4;
    }   if(job==2){
        hp = 32;
        str = 5;
        mag = 3;
        agi = 7;
    }   if(job==3){
        hp = 54;
        str = 10;
        mag = 1;
        agi = 5;
    }   if(job==4 || job==5){
        hp = 36;
        str = 4;
        mag = 9;
        agi = 4;
    }
        if(job==6){
        hp = 44;
        str = 6;
        mag = 6;
        agi = 4;
    }
    for(int i=0; i<3; i++){
        int x = generator.nextInt(4);
        int y=0;//determines minor details
                
        if(x==0)//If x=0, HP increases by a value between 8 and 15
            hp=hp+8+generator.nextInt(9);
        
        if(x==1 && job==4)//if x=1, str increases for all classes but BM/WM, who gain mag
            mag++;
        else if(x==1 && job==5)
            mag++;
        else if(x==1)
            str++;
        
        if(x==2 && job==1)//if x=2, mag increases for mages but has different effects for non-mages
            {y = generator.nextInt(1);
            if(y==0)
                str++;
            else
                hp=hp+8+generator.nextInt(9);}
        else if(x==2 && job==2)
            i--;
        else if(x==2 && job==3)
            {y = generator.nextInt(1);
            if(y==0)
                str++;
            else
                hp=hp+8+generator.nextInt(9);}
        else if(x==2)
            mag++;
        
        if(x==3)//if x=3, agi increases
            agi++;
    }
    tempHP = hp;
    }
    
    void revive(){
        int x = generator.nextInt(10)+1;
        System.out.println(name + " was revived with " + x + " hp");
        death=0;
        hp+=x;
    }
    
    void display(){
        int tempstr=str+weapon.power;
        int exptillLV=lv*30-exp;
        JOptionPane.showMessageDialog(null, "\nName " + name+"\nClass " + jobclass +
                "\nLevel " + lv + "\nExp Until Next Level: " + exptillLV + "\nWeapon " + weapon.name + " (+" + weapon.power + " STR)"+
                "\nHP " + tempHP + "/" + hp + "\nStrength " + tempstr+
                "\nMagic " + mag + "\nAgility " + agi,
                "Final Fantasy", JOptionPane.DEFAULT_OPTION, image);
    }
        
    void ability(){
  if(job==1){
        ability = "Cover";
    }
  if(job==2){
        ability = "Steal";
    }
  if(job==3){
        ability = "Focus";
    }
  if(job==4){
        ability = "Fire";
    }
  if(job==5){
        ability = "Cure";
    }
  if(job==6){
        ability = "Fire";
        ability2 = "Cure";
    }
  //These are the menu commands the character has in battle
if(job==6){
    commandsRed[1] = ability;
    commandsRed[0] = "Attack"; 
    commandsRed[2] = ability2; 
    commandsRed[3] = "Item";
    commandsRed[4] = "Check Status";

    for(int i=0;i<commands.length;i++)
        commands[i]="";
}
else{
    commands[1] = ability;
    commands[0] = "Attack"; 
    commands[2] = "Item";
    commands[3] = "Check Status";

    for(int i=0;i<commandsRed.length;i++)
        commandsRed[i]="";
}
    }
    
    int steal(int level){
        int steal=level*generator.nextInt(5);
        return steal;
    }
    
    Boolean exp(int ex){//gains exp, also level up
        exp += ex;
        
        if(exp>=30*lv){ //When a character's exp exceeds 30 times his level, he levels up
      
        lv++;
        
  if(job==1){
      for(int i=0; i<3; i++){
          levelUp=generator.nextInt(4);
      if(levelUp==0)
          hp+=15+generator.nextInt(6);
      else if(levelUp==1)
          str+=2+generator.nextInt(2);
      else if(levelUp==2)
          mag+=0+generator.nextInt(2);
      else if(levelUp==3)
          agi+=1+generator.nextInt(2);
    }}
  if(job==2){
      for(int i=0; i<3; i++){
          levelUp=generator.nextInt(4);
          if(levelUp==0)
              hp+=5+generator.nextInt(6);
          else if(levelUp==1)
              str+=1+generator.nextInt(2);
          else if(levelUp==2)
              mag+=0+generator.nextInt(2);
          else if(levelUp==3)
              agi+=2+generator.nextInt(2);
    }}
  if(job==3){
      for(int i=0; i<3; i++){
          levelUp=generator.nextInt(4);
          if(levelUp==0)
              hp+=10+generator.nextInt(6);
          else if(levelUp==1)
              str+=3+generator.nextInt(2);
          else if(levelUp==2)
              mag+=0+generator.nextInt(2);
          else if(levelUp==3)
              agi+=1+generator.nextInt(2);
    }}
  if(job==4 || job==5){
      for(int i=0; i<3; i++){
          levelUp=generator.nextInt(4);
          if(levelUp==0)
              hp+=5+generator.nextInt(6);
          else if(levelUp==1)
              str+=0+generator.nextInt(2);
          else if(levelUp==2)
              mag+=2+generator.nextInt(2);
          else if(levelUp==3)
              agi+=1+generator.nextInt(2);
    }}
  if(job==6){
      for(int i=0; i<2; i++){
          levelUp=generator.nextInt(4);
          if(levelUp==0)
              hp+=10+generator.nextInt(6);
          else if(levelUp==1)
              str+=1+generator.nextInt(2);
          else if(levelUp==2)
              mag+=1+generator.nextInt(2);
          else if(levelUp==3)
              agi+=1+generator.nextInt(2);
    }}
  
  //the following code makes sure the stats do not get too low or too high
    if(lv/2>agi)agi=lv/2;
    if(lv/2>str)str=lv/2;
    if(lv/2>mag)mag=lv/2;
    if(lv*5>hp)hp=lv/2;
    
    
        exp=0;//The exp value resets upon level-up
        return true;
        }
    else
        return false;
}
    
    
}
