/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package finalfantasy;

import java.awt.Color;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import sun.audio.*;
/**
 *
 * @author karanshukla
 */
public class FinalFantasy extends JPanel{

    /**
     * @param args the command line arguments
     */
          
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner kb = new Scanner(System.in);
        int input, choice;
        
       //Most of the following is character generation
        Character one = new Character();
        Character two = new Character();
        Character three = new Character();
        Character four = new Character();
        
        Party party = new Party();
        
        ImageIcon warrior = new ImageIcon("Warrior.png","Warrior");
        ImageIcon thief = new ImageIcon("Thief.png","Thief");
        ImageIcon monk = new ImageIcon("Monk.png","Monk");
        ImageIcon blackMage = new ImageIcon("BlackMage.png","Black Mage");
        ImageIcon whiteMage = new ImageIcon("WhiteMage.png","White Mage");
        ImageIcon redMage = new ImageIcon("RedMage.png","Red Mage");
        ImageIcon mog = new ImageIcon("Mog.gif","Moogle");
        
        //The game starts here

        JOptionPane.showMessageDialog(null, "Welcome to Final Fantasy!", "Final Fantasy", JOptionPane.DEFAULT_OPTION, mog);
    
        ImageIcon[] jobz = {warrior,//lists all jobs
                    thief,
                    monk,
                    blackMage,
                    whiteMage,
                    redMage};
        
        do{ //Character generation will loop until the player is satisfied
        
        String name1 = JOptionPane.showInputDialog("Name character 1!", "Character 1");
        one.name(name1, 1);
        int job1 = 1+JOptionPane.showOptionDialog(null, "Now choose his/her job!", one.name+"'s job", JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,
                null, jobz, jobz[0]);
        one.job(job1);
        
        String name2 = JOptionPane.showInputDialog("Name character 2!", "Character 2");
        two.name(name2, 2);
        int job2 = 1+JOptionPane.showOptionDialog(null, "Now choose his/her job!", two.name+"'s job", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,
                null, jobz, jobz[1]);
        two.job(job2);
        
        String name3 = JOptionPane.showInputDialog("Name character 3!", "Character 3");
        three.name(name3, 3);
        int job3 = 1+JOptionPane.showOptionDialog(null, "Now choose his/her job!", three.name+"'s job", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,
                null, jobz, jobz[3]);
        three.job(job3);
        
        String name4 = JOptionPane.showInputDialog("Name character 4!", "Character 4");
        four.name(name4, 4);
        int job4 = 1+JOptionPane.showOptionDialog(null, "Now choose his/her job!", four.name+"'s job", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,
                null, jobz, jobz[4]);
        four.job(job4);
        
        JOptionPane.showMessageDialog(null, "Now time to start your adventure! This is your party:", "Final Fantasy", JOptionPane.DEFAULT_OPTION, mog);
        one.calculate();
        two.calculate();
        three.calculate();
        four.calculate();
        party.status(one, two, three, four);
        
        choice=JOptionPane.showConfirmDialog(null, "Do you like your party?", "Final Fantasy", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, mog);
        }while(choice==1);
        
        //"ability" merely assigns each character his abilities and does not display anything
        one.ability();
        two.ability();
        three.ability();
        four.ability();
        
        JOptionPane.showMessageDialog(null, "Great! Then it's time to embark on your adventure, Warriors of Light...", "Final Fantasy", JOptionPane.QUESTION_MESSAGE, mog);
        
        //This part is an intro battle; maybe added to the actual game
        
        monster a = new monster("Goblin A");
        monster b = new monster("Goblin B");
        monster c = new monster("Goblin C");
        
        JOptionPane.showMessageDialog(null, "Watch out! They're monsters nearby!", "Final Fantasy", JOptionPane.DEFAULT_OPTION, mog);
        
        //creates a combat scene named "goblins"
        Combat goblins = new Combat(one,two,three,four,a,b,c,new ImageIcon("Field.png").getImage());
        goblins.intro("goblin",a);//add "this" to intro to allow editing of the jframe?
        goblins.targets(a, b, c);
        
        goblins.battle(one,two,three,four,a,b,c,party,a);
        
        WorldMap world = new WorldMap();
        world.navigate(party, one, two, three, four);
    }
}
