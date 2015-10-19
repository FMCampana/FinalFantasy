/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package finalfantasy;

import java.awt.Graphics;
import java.util.*;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Karan y Krishna
 */
public class Town extends JPanel{
   int input, response;//input is for menus, response is for npcs
   String name;
   Scanner kb = new Scanner(System.in);
   ArrayList choices = new ArrayList();
   Image backdrop;
   
    public void paintComponent(Graphics g) {
        g.drawImage(backdrop,0,0,null);}
    
   Town(String x){
        name=x;
        if(name.equalsIgnoreCase("Cornelia")){
            choices.add("Talk to the King");
            //choices.add("Play music");
            backdrop=new ImageIcon("cornelia.png").getImage();}
        if(name.equalsIgnoreCase("Pravoka")){
            choices.add("Go to the port");
            backdrop=new ImageIcon("pravoka.png").getImage();}
        if(name.equalsIgnoreCase("Elfheim")){
            choices.add("Talk to Elf King");
            backdrop=new ImageIcon("elfheim.png").getImage();}
   }
   
  void enterTown(WorldMap world, Party party, Character one, Character two, Character three, Character four)
  {
            Music song=new Music();
        JFrame townScreen = new JFrame("Town");
        townScreen.setBounds(200, 100,backdrop.getWidth(townScreen),backdrop.getHeight(townScreen));
        townScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        townScreen.setResizable(false);
        townScreen.add(new Town(name));
        townScreen.setIconImage(backdrop);
        townScreen.setVisible(true);
        
       //The following code is used for all towns
       Shop shop = new Shop(name);//generates a shop based on the town
       boolean leave=false;//indicates if the player wants to leave
       
       Object[] choicesArray = new Object[choices.size()+3];
       
       for(int i=0;i<choices.size();i++)
        choicesArray[i]=choices.get(i);//places the town's exclusive choices in the choices array
       
            choicesArray[choices.size()] = "Check status";
            choicesArray[choices.size()+1] = "Enter the shop";
            choicesArray[choices.size()+2] = "Leave";//standard choices for all towns
        
            do{
            
            int input=JOptionPane.showOptionDialog(null, "You enter the town of " + name + ". What do you do?", 
                "Final Fantasy", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("mog.gif"), choicesArray,choicesArray[0]);
 
       //if(input==choices.size()+1)
       if(choicesArray[input].equals("Enter the shop"))    
                shop.shopping(party);
        
       else if(choicesArray[input].equals("Check status"))  
                checkStatus(party,one,two,three,four);
       
       // else if(choices.get(input).equals("Play music")){
       //     song.music();}
        
        else if(choicesArray[input].equals("Talk to the Elf King"))
                do{
                ImageIcon kingIcon=new ImageIcon("Elf_King.png");
                response=JOptionPane.showConfirmDialog(null, "My son, Thranduil Tasartir, has been put to sleep by the dark elf Astos!\nWill you save him?",
                        "The King",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, kingIcon);
        
                switch(response){
                    case 0:
                        JOptionPane.showMessageDialog(null, "I knew I could count on you, humans!\n\n *Unlock Western Keep*", 
                                "The Elf King", JOptionPane.DEFAULT_OPTION, kingIcon);
                        choices.remove("Talk to Elf King");
                        input=choices.size()+2;
                        world.addDungeon("Western Keep");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "What was that? I couldn't understand your human tongue.", "The Elf King (doesn't understand English)", JOptionPane.DEFAULT_OPTION, kingIcon);
                        }
                
                }while(response!=0);
        
        else if(choicesArray[input].equals("Talk to the King"))
            
                
                do{
                ImageIcon kingIcon=new ImageIcon("King.png");
                response=JOptionPane.showConfirmDialog(null, "My daughter, Sarah, has been kidnapped by the knight Garland!\nWill you save her?",
                        "The King",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, kingIcon);
        
                switch(response){
                    case 0:
                        JOptionPane.showMessageDialog(null, "I knew I could count on you, Light Warriors!\n\n *Unlock Temple of Chaos*", 
                                "The King", JOptionPane.DEFAULT_OPTION, kingIcon);
                        choices.remove("Talk to the King");
                        input=choices.size()+2;
                        world.addDungeon("Temple of Chaos");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "What was that? I couldn't hear you very well.", "The King", JOptionPane.DEFAULT_OPTION, kingIcon);
                        }
                
                }while(response!=0);
        
        
        else if(choicesArray[input].equals("Go to the port")){
            
        System.out.println("You try to go to the port, but the ships are being blocked by a pirate gang led by buccanner named Bikke.");
        monster a = new monster("Bikke");
        monster b = new monster("Pirate A");
        monster c = new monster("Pirate B");
        Combat bikke = new Combat(one,two,three,four,a,b,c,new ImageIcon("Ship.png").getImage());
        bikke.intro("Bikke",a);
        bikke.battle(one,two,three,four,a,b,c,party);
        System.out.println("Bikke: You've beaten me. Take yer darn ship! Ye can reach the elf kingdom across the sea!");
        System.out.println("Unlocked new location, Elfheim!");
        world.addElfheim();
        world.increaseDifficulty();
        choices.remove("Go to the port");
        input=choices.size()+2;}
        
        if(choicesArray[input].equals("Leave")){
                    townScreen.dispose();
                   leave=true;}
        
        }while(!leave);
   }
  public void checkStatus(Party party, Character one, Character two, Character three, Character four){
                party.status(one, two, three, four);
                int choice=JOptionPane.showConfirmDialog(null, "Would you like to equip your weapons?", 
                        "Final Fantasy", JOptionPane.YES_NO_OPTION);
                    if(choice==0)
                        party.equip(one, two, three, four);
  }
}
   

