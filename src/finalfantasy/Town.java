/*
 * To c2ange thi  Cw�B-         choose Tools | Templates
 * and open the template in the editor.
 */
package finalfantasy;

import java.awt.Graphics;
import java.util.*;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Karan
 */
public class Town extends JPanel{
   int input, response;//input is for menus, response is for npcs
   String name;
   Scanner kb = new Scanner(System.in);
   ArrayList<String> choices = new ArrayList<String>();
   Image backdrop;
   boolean trust=true;
   boolean talked=false;//sees if the player has talked with the locales yet
   
    Random generator = new Random();
   
    public void paintComponent(Graphics g) {
        g.drawImage(backdrop,0,0,null);}
    
   Town(String x){
        name=x;
        if(name.equalsIgnoreCase("Cornelia")){
            choices.add("Talk to the King");
            backdrop=ImageFetcher.fetchImage("cornelia.png");}
        if(name.equalsIgnoreCase("Pravoka")){
            choices.add("Go to the port");
            backdrop=ImageFetcher.fetchImage("pravoka.png");}
        if(name.equalsIgnoreCase("Elfheim")){
            choices.add("Talk to Elf King");
            backdrop=ImageFetcher.fetchImage("elfheim.png");}
        if(name.equalsIgnoreCase("Melmond")){
            choices.add("Talk to Dr. Unne");
            backdrop=ImageFetcher.fetchImage("melmond.png");}
        if(name.equalsIgnoreCase("Lufenia")){
            choices.add("Talk to the Lufenian Elder");
            backdrop=ImageFetcher.fetchImage("lufenia.png");}
        if(name.equalsIgnoreCase("Crescent Lake")){
            choices.add("Talk to the Circle of Sages");
            backdrop=ImageFetcher.fetchImage("crescent-lake.png");
            trust=false;}
        choices.add("Talk to a locale");
   }
   
  void enterTown(WorldMap world, Party party, Character one, Character two, Character three, Character four)
  {
        JFrame townScreen = new JFrame(name);
        
        townScreen.setBounds(200, 100,backdrop.getWidth(townScreen),backdrop.getHeight(townScreen));
        townScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        townScreen.setResizable(false);
        townScreen.add(new Town(name));
        townScreen.setIconImage(backdrop);
        townScreen.setVisible(true);
        
        Music.play("town.mid");
        
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
                "Final Fantasy", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, ImageFetcher.fetchIcon("mog.gif"), choicesArray,choicesArray[0]);
 
       if(choicesArray[input].equals("Enter the shop"))
                shop.shopping(party);
        
       else if(choicesArray[input].equals("Check status"))  
                party.status(one,two,three,four);


        else if(choicesArray[input].equals("Talk to a locale")){
                talk(party);
        }
        
        else if(choicesArray[input].equals("Talk to Elf King"))
                do{
                ImageIcon kingIcon=ImageFetcher.fetchIcon("Elf_King.png");
                response=JOptionPane.showConfirmDialog(null, "My son, Thranduil Tasartir, has been put to sleep by the dark elf Astos!\nWill you save him?",
                        "The King",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, kingIcon);
        
                switch(response){
                    case 0:
                        JOptionPane.showMessageDialog(null, "I knew I could count on you, humans!\n\n *Unlock Western Keep*", 
                                "The Elf King", JOptionPane.DEFAULT_OPTION, kingIcon);
                        choices.remove("Talk to Elf King");
                        input=choicesArray.length-1;
                        world.addDungeon("Western Keep");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "What was that? I couldn't understand your human tongue.", "The Elf King (doesn't understand English)", JOptionPane.DEFAULT_OPTION, kingIcon);
                        }
                }while(response!=0);
        
        else if(choicesArray[input].equals("Talk to the Lufenian Elder"))
            if(!trust){
                ImageIcon elderIcon=ImageFetcher.fetchIcon("Cid.png");
                response=JOptionPane.showConfirmDialog(null, "You speak our language!?!? And you seek to slay the fiends? Your words are honorable but your intent is still dubious.\nGo to Crescent Lake and listen to the Circle of Sages.\nWin their trust, then we will talk.",
                        "Cid, The Lufenian Elder",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, elderIcon);
                world.addCrescent();
                choices.remove("Talk to the Lufenian Elder");
                input=choicesArray.length-1;}
            else{
                ImageIcon elderIcon=ImageFetcher.fetchIcon("Cid.png");
                response=JOptionPane.showConfirmDialog(null, "You've won the trust of the Circle of Sages? Well done. \n\nTo reward your efforts, Light Warriors, I shall bless you with the Airship! With this you can go to the Flying Fortress... where the final fiend, Tiamat, resides!"
                        + "\n\n*Unlock Flying Fortress*",
                        "Cid, The Lufenian Elder",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, elderIcon);
                world.addDungeon("Flying Fortress");
                choices.remove("Talk to the Lufenian Elder");
                input=choicesArray.length-1;
            }
            
        else if(choicesArray[input].equals("Talk to the Circle of Sages")){
                ImageIcon elderIcon=ImageFetcher.fetchIcon("Lukahn.png");
                JOptionPane.showMessageDialog(null, "I see... a cycle of wrath... a dim light... guided by destiny... that about which our entire future revolves...\n\n"
                        + "The Lufenian Elder has sent you to win our trust? You can do better than that... slay the third fiend, Marilith of Fire, to earn our blessing.",
                        "Lukahn, Sage of the Circle",JOptionPane.QUESTION_MESSAGE, elderIcon);
                world.addDungeon("Mount Gulug");
                choices.remove("Talk to the Circle of Sages");
                input=choicesArray.length-1;
            }
        
        else if(choicesArray[input].equals("Talk to Dr. Unne"))
                do{
                ImageIcon doctorIcon=ImageFetcher.fetchIcon("dr_unne.png");
                response=JOptionPane.showConfirmDialog(null, "This agrarian settlement has been depurified by the unholy monster, Lich! Will you slay him?",
                        "The Good Doctor",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, doctorIcon);
        
                switch(response){
                    case 0:
                        JOptionPane.showMessageDialog(null, "I thank you for your most generous killings!\n\n *Unlock Cavern of Earth*", 
                                "The Good Doctor", JOptionPane.DEFAULT_OPTION, doctorIcon);
                        choices.remove("Talk to Dr. Unne");
                        input=choicesArray.length-1;
                        world.addDungeon("Cavern of Earth");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "What was that? I do not have the capacity to comprehend your uneducated diction.", "The Good Doctor", JOptionPane.DEFAULT_OPTION, doctorIcon);
                        }
                
                }while(response!=0);
        
        else if(choicesArray[input].equals("Talk to the King"))
                
                do{
                ImageIcon kingIcon=ImageFetcher.fetchIcon("King.png");
                response=JOptionPane.showConfirmDialog(null, "My daughter, Sarah, has been kidnapped by the knight Garland!\nWill you save her?",
                        "The King",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, kingIcon);
        
                switch(response){
                    case 0:
                        JOptionPane.showMessageDialog(null, "I knew I could count on you, Light Warriors!\n\n *Unlock Temple of Chaos*", 
                                "The King", JOptionPane.DEFAULT_OPTION, kingIcon);
                        choices.remove("Talk to the King");
                        input=choicesArray.length-1;
                        world.addDungeon("Temple of Chaos");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "What was that? I couldn't hear you very well.", "The King", JOptionPane.DEFAULT_OPTION, kingIcon);
                        }
                
                }while(response!=0);
        
        
        else if(choicesArray[input].equals("Go to the port")){
            
        JOptionPane.showMessageDialog(null, "You try to go to the port, but the ships are being blocked by a pirate gang led by buccanner named Bikke.", 
                                "Final Fantasy", JOptionPane.DEFAULT_OPTION, ImageFetcher.fetchIcon("mog.gif"));
        monster a = new monster("Bikke");
        monster b = new monster("Pirate A");
        monster c = new monster("Pirate B");
        Combat bikke = new Combat(one,two,three,four,a,b,c,ImageFetcher.fetchImage("Ship.png"));
        bikke.intro("Bikke",a);
        bikke.battle(one,two,three,four,a,b,c,party,a);
        JOptionPane.showMessageDialog(null, "You've beaten me. Take yer darn ship! Ye can reach the elf kingdom across the sea! \n *Unlocked Elfheim*", 
                                "Bikke", JOptionPane.DEFAULT_OPTION, a.image);
        world.addElfheim();
        world.increaseDifficulty();
        choices.remove("Go to the port");
        input=choicesArray.length-1;
        }
        
        if(choicesArray[input].equals("Leave")){
          townScreen.dispose();
          leave=true;
          Music.play("Terra.wav");
        }
        
        }while(!leave);
   }

        void talk(Party party){
            String citizen="A citizen of "+name;
            String message="";
            if(name.equals("Cornelia"))
            message="Welcome to the fine town of Cornelia! Allow me to educate you on the job system!\n\n\n\n"
                    + "Warriors can use a wide variety of weapons and protect allies with Cover. Use them to defend weaker teammates!\n\n"
                    + "Thieves can Steal items and gil, and have amazing Agility. They can also wield most swords.\n\n"
                    + "Monks cannot use weapons, but their Focus allows their attacks to hit a lot harder. Focus is great for boss battles!\n\n"
                    + "Black Mages have few HP and are fragile, but their Fire can do damage to multiple enemies. Make sure to protect these guys!\n\n"
                    + "White Mages are fragile like Black Mages, but their can heal the party with Cure instead. They can also wield hammers.\n\n"
                    + "Red Mages can use both Cure and Fire, but their stats are weaker than that of other jobs. They also have decent attack power.";
            if(name.equals("Pravoka")){
            message="Hail, Light Warriors! Do you know of weapons?\n\n"
                    + "Weapons can be equipped to your characters through the status screen to increase their Strength!\n\n"
                    + "Not all jobs can use all weapons.\n"
                    + "Thieves and Red Mages use swords, while White Mages use hammers.\n"
                    + "Warriors use both, as well as broadswords and axes.\n\n";
                if(!talked){message=message+"Here, have my axe!";
                            party.addWeapon("Axe");}
            }
            if(name.equals("Elfheim")){
            message="Oh! Light Warriors from across the sea!\nSome monsters like to use magic spells that damage your entire party!\n\n"
                    + "The Warrior's Cover will not protect you from this, but a Cure might heal your wounds.\n\n";
                if(!talked){message=message+"Here, have a Cura Scroll. It imitates the Cure spell!\n"
                    + "*Recieved Cura Scroll*";
                    party.addItem("Cura Scroll");}
            }
            if(name.equals("Melmond")){
            message="Come to save our wretched town, Light Warriors? About time you came!\n\n"
                    + "Some monsters are weak against certain elemental attacks. For example, the villain Lich hates Fire, while watery foes hate the Coral Sword.\n\n"
                    + "What, were you expecting me to give you an item? I'm poor! Tough luck!";
            }
            if(name.equals("Lufenia")){
            message="You can speak our language? Perhaps you really are the warriors of prophecy...\n\n"
                    + "The Defender is not only a powerful weapon, but it also massively increases the user's HP!\n"
                    + "Unfortunatly, it's extremely expensive, and it can only be used by Warriors...\n\n";
            }
            if(name.equals("Crescent Lake")){
            message="Our mayor, Lukhan, is the leader of the Circle of Sages.\n"
                    + "He is also, incidentally, the brother of Cid, the Lufenian Elder.\n\n";
                if(!talked){message=message+"Lukhan told me to give this to you. It's a Blizzard Fang, an item that inflicts ice damage.\n"
                        + "If I had to guess, I'd say that fiery fiends would be weak to this.\n\n"
                    + "*Recieved Blizzard Fang*";
                    party.addItem("Blizzard Fang");}
            }
            JOptionPane.showMessageDialog(null, message,citizen, JOptionPane.DEFAULT_OPTION, new ImageIcon());
            talked=true;
}


}
   

