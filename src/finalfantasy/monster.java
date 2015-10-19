package finalfantasy;

import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author karanshukla
 */
public class monster extends finalfantasy.Character{
    
    Random generator = new Random();
    
    String name = "",ability;
    int lv = 1, hp = 0, str = 0, mag = 0, agi = 0, speed = 0,worth=0;
    ImageIcon image;
    String weakness=" ";
    
        monster(String inputName){
            name = inputName;
            ability="";
            if(name.startsWith("Garland")){//garland
                hp=120;
                str=18;
                mag=18;
                agi=7;
                worth=80;
                image=new ImageIcon("Garland.png");
            }
            if(name.startsWith("Pirate")){//pirate
                hp=40;
                str=12;
                mag=2;
                agi=7;
                worth=15;
                image=new ImageIcon("Pirate.png");
            }
            if(name.startsWith("Bikke")){//bikke
                hp=140;
                str=18;
                mag=2;
                agi=7;
                worth=60;
                image=new ImageIcon("Bikke.png");
            }
            if(name.startsWith("Tarantula")){//tarantula
                hp=60;
                str=10;
                mag=2;
                agi=8;
                worth=20;
                image=new ImageIcon("Tarantula.png");
            }
            if(name.equals("")){//null
                hp=0;//automatically dead
                death=1;
            }
            if(name.startsWith("Goblin")){//goblin
                hp = 20;
                str = 9;
                mag = 4;
                agi = 5;
                worth=10;
                image=new ImageIcon("goblin.png");}
            if(name.startsWith("Astos")){//astos
                hp = 160;
                str = 14;
                mag = 10;
                agi = 8;
                worth=100;
                image=new ImageIcon("Astos.png");
                ability="Fire";}
            if(name.startsWith("Lich")){
                hp = 210;
                str = 16;
                mag = 17;
                agi = 10;
                worth=150;
                image=new ImageIcon("Lich.png");
                ability="Blizzara";
                weakness="Fire";}
            if(name.endsWith("Kraken")){
                hp = 215;
                str = 16;
                mag = 19;
                agi = 11;
                worth=155;
                image=new ImageIcon("Kraken.png");
                ability="Ink";
                weakness="Thunder";}
            if(name.endsWith("Marilith")){
                hp = 240;
                str = 20;
                mag = 24;
                agi = 12;
                worth=160;
                image=new ImageIcon("Marilith.png");
                ability="Fira";
                weakness="Ice";}
            if(name.endsWith("Tiamat")){
                hp = 260;
                str = 24;
                mag = 28;
                agi = 15;
                worth=165;
                image=new ImageIcon("Tiamat.png");}
            if(name.endsWith("Chaos")){
                hp = 300;
                str = 40;
                mag = 35;
                agi = 15;
                worth=1000;
                image=new ImageIcon("Chaos.png");}
        
        tempHP = hp;
        }
        
        void getHit(int hit, boolean physical, String attacker, String element, boolean miss){
            
            if(element.equals(weakness))//super effective hits do 2x damage
                    hit*=2;
            if(!miss)//hp is only reduced if attack is not a miss
                    tempHP = tempHP - hit;//damages enemy's hp based on hit value
        
        if(tempHP<=0)//slays enemy if enemy is defeated
            death=1;
        
        String output;
        if(!miss){ 
            output="Enemy " + name + " took " + hit + " damage!";
            if(element.equalsIgnoreCase(weakness)) output = output + " Weak to " + weakness;
            if(tempHP<=0) output = output + " "+name+" was slain!";}
        else
            output=name+" dodged the attack!";
        
        if(hit>0&&physical)//output this if enemy is physically attacked
        JOptionPane.showMessageDialog(null,output,attacker+ " attacks!",JOptionPane.DEFAULT_OPTION,image);
        
        if(hit<0){//output this if enemy is healed
            hit*=-1;
            JOptionPane.showMessageDialog(null,"Enemy " + name + " was healed for " + hit + " points!","Battle!",JOptionPane.DEFAULT_OPTION,image);
        }
    }
    
        void enemyAttack(Combat combat, Character one, Character two, Character three, Character four){
          int action = generator.nextInt(2);
          if(ability.equals(""))
              action=0;//if the enemy has no ability, he will always attack
              
          switch(action){
              case 0://case 0 is the default enemy physical attack
                  hit = combat.attack(str);
            
          //The following code assumes cover is in effect; if it is not,
          //Then the damage reduction effect is canceled out later
            
          hit = (int)(hit * 2 / 3); //cover reduces damage
              
          if(combat.cover == 1 && one.death==0){
              one.getHit(hit,this);
          }
          else if(combat.cover == 2 && two.death==0){
              two.getHit(hit,this);
          }
          else if(combat.cover == 3 && three.death==0){
              three.getHit(hit,this);
          }
          else if(combat.cover == 4 && four.death==0){
              four.getHit(hit,this);
          }
          
          else{//This code occurs when cover is not in effect
            hit = (int)(hit * 3 / 2);//cancels out cover's damage reduction
            Boolean check=false;
            int target = generator.nextInt(4);
            do{
                if(target == 0 && one.death==0)
                    {one.getHit(hit,this);check=true;}
                if(target == 1 && two.death==0)
                    {two.getHit(hit,this);check=true;}
                if(target == 2 && three.death==0)
                    {three.getHit(hit,this);check=true;}
                if(target == 3 && four.death==0)
                    {four.getHit(hit,this);check=true;}
            }
            while(!check);
            }
            
            break;
                  
              case 1://special ability
                  if(ability.equals("Fire") || ability.equals("Blizzara") || ability.equals("Ink") || ability.equals("Fira") || name.endsWith("Tiamat") || name.endsWith("Chaos")){//offensive spells
                      
                      if(name.endsWith("Tiamat")||name.endsWith("Chaos"))
                          ability=randomSpell(name);
                      
                      int spell;//spell damage
                      boolean blind=false;//does the spell blind?
                      
                      if(ability.equals("Ink"))blind=true;
                      
                    JLabel[] message = new JLabel[4];
                    
                    Character[] characters = {one,two,three,four};
            
                    int i=0;
                    
                    for(Character targeted:characters){//multitarget attack
                        if(targeted.death==0){spell=mag+generator.nextInt(5);
                            targeted.takeDamage(spell);//reduce enemy hp based on spell value
                            if(targeted.tempHP<=0)
                                message[i]=new JLabel(targeted.name+" took "+spell+" damage. "+targeted.name+" was knocked out!",targeted.image,SwingConstants.LEFT);
                            else if(blind && generator.nextInt(4)==0 && targeted.blind==false){//25% chance of blind if not already blinded
                                targeted.inflictStatus("Blind");
                                message[i]=new JLabel(targeted.name+" took "+spell+" damage. "+targeted.name+" was blinded!",targeted.image,SwingConstants.LEFT);}
                            else
                                message[i]=new JLabel(targeted.name+" took "+spell+" damage",targeted.image,SwingConstants.LEFT);}
                        i++;}
                
                        JOptionPane.showMessageDialog(null,message,name+" cast " + ability + "!",JOptionPane.DEFAULT_OPTION);
                    }
                  
        }
        }
        
        void display(){
        System.out.println("Name " + name);
        System.out.println("HP " + hp);
        System.out.println("Strength " + str);
        System.out.println("Magic " + mag);
        System.out.println("Agility " + agi + "\n");}
    
        String randomSpell(String name){//return a random spell for enemies with more than one
            int rand=generator.nextInt(3);
            String spell="";
            if(name.equalsIgnoreCase("Tiamat"))
                switch(rand){
                case 0: spell="Thunderstorm";break;
                case 1: spell="Blaze";break;
                case 2: spell="IceStorm";break;
            }
            if(name.equalsIgnoreCase("Chaos"))
                switch(rand){
                    case 0: spell="Thundaja";break;
                    case 1: spell="Firaja";break;
                    case 2: spell="Blizzaja";break;
                }
            return spell;
        }
        
        
        
    
}
    