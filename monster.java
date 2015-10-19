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
    
        monster(String inputName){
            name = inputName;
            ability="";
            if(name.startsWith("Garland")){//garland
                //hp=150;
                hp=1;
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
                agi = 6;
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
        
        tempHP = hp;
        }
        
        void getHit(int hit, Boolean physical, String attacker){
        tempHP = tempHP - hit;//damages enemy's hp based on hit value
        
        if(tempHP<=0)//slays enemy if enemy is defeated
            death=1;
        
        if(hit>0&&physical){//output this if enemy is physically attacked
        if(tempHP<=0)
            JOptionPane.showMessageDialog(null,"Enemy " + name + " took " + hit + " damage! "+name+" was slain!",attacker+ " attacks!",JOptionPane.DEFAULT_OPTION,image);
        else
             JOptionPane.showMessageDialog(null,"Enemy " + name + " took " + hit + " damage!",attacker+ " attacks!",JOptionPane.DEFAULT_OPTION,image);
        }
        
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
                  if(ability.equals("Fire")){
                      int fire;//fire damage
                    JLabel[] message = new JLabel[4];
            
                    if(one.death==0){fire=mag+generator.nextInt(5);
                        one.takeDamage(fire);//reduce enemy hp based on fire value
                        if(one.tempHP<=0)
                            message[0]=new JLabel(one.name+" took "+fire+" damage. "+one.name+" was slain!",one.image,SwingConstants.LEFT);
                        else
                            message[0]=new JLabel(one.name+" took "+fire+" damage",one.image,SwingConstants.LEFT);}
                    if(two.death==0){fire=mag+generator.nextInt(5);
                        two.takeDamage(fire);//reduce enemy hp based on fire value
                        if(two.tempHP<=0)
                            message[1]=new JLabel(two.name+" took "+fire+" damage. "+two.name+" was slain!",two.image,SwingConstants.LEFT);
                        else
                            message[1]=new JLabel(two.name+" took "+fire+" damage",two.image,SwingConstants.LEFT);}
                    if(three.death==0){fire=mag+generator.nextInt(5);
                        three.takeDamage(fire);//reduce enemy hp based on fire value
                        if(three.tempHP<=0)
                            message[2]=new JLabel(three.name+" took "+fire+" damage. "+three.name+" was slain!",three.image,SwingConstants.LEFT);
                        else
                            message[2]=new JLabel(three.name+" took "+fire+" damage",three.image,SwingConstants.LEFT);}
                    if(four.death==0){fire=mag+generator.nextInt(5);
                        four.takeDamage(fire);//reduce enemy hp based on fire value
                        if(four.tempHP<=0)
                            message[3]=new JLabel(four.name+" took "+fire+" damage. "+four.name+" was slain!",four.image,SwingConstants.LEFT);
                        else
                            message[3]=new JLabel(four.name+" took "+fire+" damage",four.image,SwingConstants.LEFT);}
                
                        JOptionPane.showMessageDialog(null,message,name+" cast Fire!",JOptionPane.DEFAULT_OPTION);
                    }
                  
        }
        }
        
        void display(){
        System.out.println("Name " + name);
        System.out.println("HP " + hp);
        System.out.println("Strength " + str);
        System.out.println("Magic " + mag);
        System.out.println("Agility " + agi + "\n");}
        
        
        
    
}
    