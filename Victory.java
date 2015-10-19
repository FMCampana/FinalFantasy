/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package finalfantasy;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;
/**
 *
 * @author karanshukla
 */
public class Victory {
    
    Random generator = new Random();
    int gil, exp;
    Boolean check1=false,check2=false,check3=false,check4=false;
    
    Victory(){
    }
    
    void lose(ImageIcon murderer){
        JOptionPane.showMessageDialog(null, "The party has fallen.....................", "GAME OVER...", JOptionPane.DEFAULT_OPTION, murderer);
      System.exit(0);
    }
    void obtainVictory(int worth, Character one, Character two, Character three, Character four, Party party, JFrame battle){
        battle.dispose();
        exp=worth;
        gil=worth-15+generator.nextInt(30);
        party.addGil(gil);
        
    JLabel[] labels = new JLabel[10];
    labels[0]=new JLabel("Victory!");
    labels[1]=new JLabel("Obtained "+exp+" exp!");
    labels[2]=new JLabel("Obtained "+gil+" gil!"+" (Current gil: " + party.gil + ")");
    
         if(one.death==0)check1=one.exp(exp);
         if(two.death==0)check2=two.exp(exp);
         if(three.death==0)check3=three.exp(exp);
         if(four.death==0)check4=four.exp(exp);
         
    labels[3]=new JLabel(" ");

         if(check1)
    labels[4]=new JLabel(one.name+" leveled up!",one.image,JLabel.LEFT);
         else{
             int need=one.lv*30-one.exp;
            labels[4]=new JLabel("EXP until "+one.name+" levels up: "+need,one.image,JLabel.LEFT);}
         if(check2)
    labels[5]=new JLabel(two.name+" leveled up!",two.image,JLabel.LEFT);
         else{
             int need=two.lv*30-two.exp;
            labels[5]=new JLabel("EXP until "+two.name+" levels up: "+need,two.image,JLabel.LEFT);}
         if(check3)
    labels[6]=new JLabel(three.name+" leveled up!",three.image,JLabel.LEFT);
         else{
             int need=three.lv*30-three.exp;
            labels[6]=new JLabel("EXP until "+three.name+" levels up: "+need,three.image,JLabel.LEFT);}
         if(check4)
    labels[7]=new JLabel(four.name+" leveled up!",four.image,JLabel.LEFT);
         else{
             int need=four.lv*30-four.exp;
            labels[7]=new JLabel("EXP until "+four.name+" levels up: "+need,four.image,JLabel.LEFT);}
         
            labels[8]=new JLabel(" ");
            
        int loot = generator.nextInt(100);
           String item="";
           if(loot>=50)item="Potion";
           else if(loot>=90)item="Phoenix Down";
           if(loot>=50){party.addItem(item);//only adds item if item was obtained
            labels[9]=new JLabel("Obtained " + item + "!");
           }
           else
            labels[9]=new JLabel();
    for(int i=0;i<labels.length;i++)
           labels[i].setFont(new Font("serif", Font.BOLD, 18));
    
       JOptionPane.showMessageDialog(null, labels, "Final Fantasy", JOptionPane.DEFAULT_OPTION, new ImageIcon("Mog.gif"));
       
        party.rest(one, two, three, four);
    }
  }

