/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package finalfantasy;
import java.awt.Graphics;
import java.awt.Image;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author Karan
 */
public class WorldMap extends JPanel{
    int difficulty=1, validChoice;
    Scanner kb = new Scanner(System.in);
    ArrayList locations = new ArrayList();
    Random generator = new Random();
        Town cornelia = new Town("Cornelia");
        Town pravoka, elfheim, melmond;
    
        Image backdrop = new ImageIcon("map.jpg").getImage();
    
    public void paintComponent(Graphics g) {
        int w=(int)(backdrop.getWidth(this));
        int h=(int)(backdrop.getHeight(this));
        g.drawImage(backdrop,0,0,w,h,null);}
    
    WorldMap(){
        locations.add("Cornelia");
        locations.add("Western Keep");
    }
    
    void addDungeon(String dungeon){
    locations.add(dungeon);
    }
    
    void addPravoka(){
    locations.add("Pravoka");
    pravoka = new Town("Pravoka");
    }
    
    void addElfheim(){
    locations.add("Elfheim");
    elfheim = new Town("Elfhiem");
    }
    
    void addMelmond(){
    locations.add("Melmond");
    melmond = new Town("Melmond");
    }
    
    void increaseDifficulty(){
    difficulty++;
    }
    
    void navigate(Party party, Character one, Character two, Character three, Character four){
    validChoice=0;
    
    JFrame mapScreen = new JFrame("World Map");
        mapScreen.setBounds(150, 25,backdrop.getWidth(this),backdrop.getHeight(this));
        mapScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        mapScreen.setResizable(false);
        mapScreen.add(new WorldMap());
        mapScreen.setIconImage(new ImageIcon("map.jpg").getImage());
        mapScreen.setVisible(true);
        
        do{
            
            Object[] locationArray = new Object[locations.size()+2];
            
       for(int i=0;i<locations.size();i++)
        locationArray[i]=locations.get(i);//places the weapons into the weapon array
       
            locationArray[locations.size()] = "Battle a mob";
            locationArray[locations.size()+1] = "Check Status";
            
    int input= JOptionPane.showOptionDialog(null, "What do you wish to do?", 
                "World Map", JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE, new ImageIcon("mog.gif"), 
                locationArray, locationArray[0]);
    
        if(input==locations.size()){//holy macheroli it's a random battle!
            int battleType=generator.nextInt(difficulty);
            switch(battleType){
                case 0:
        monster a = new monster("Goblin A");
        monster b = new monster("Goblin B");
        monster c = new monster("Goblin C");
        Combat goblins = new Combat(one,two,three,four,a,b,c,new ImageIcon("Field.png").getImage());
        goblins.intro("goblin",a);
        goblins.battle(one,two,three,four,a,b,c,party);
        break;
                case 1:
        monster d = new monster("Pirate A");
        monster e = new monster("Pirate B");
        monster f = new monster("Pirate C");
        Combat pirates = new Combat(one,two,three,four,d,e,f,new ImageIcon("Ship.png").getImage());
        pirates.intro("pirate",d);
        kb.nextLine();
        pirates.battle(one,two,three,four,d,e,f,party);
        break;
                case 2://need to make these enemies in the monster class!
        monster g = new monster("Tarantula A");
        monster h = new monster("Tarantula B");
        monster i = new monster("Tarantula C");
        Combat tarantula = new Combat(one,two,three,four,g,h,i,new ImageIcon("Field.png").getImage());
        tarantula.intro("tarantula",g);
        kb.nextLine();
        tarantula.battle(one,two,three,four,g,h,i,party);
        break;
            }}
        
        else if(input==locations.size()+1){//this checks status
            party.status(one, two, three, four);
            System.out.println("Would you like to equip your weapons? (Press 1 to say yes, or 2 to say no)");
            input=kb.nextInt();
            if(input==1)
               party.equip(one, two, three, four);}
        else if("Cornelia".equals(locations.get(input))){
            cornelia.enterTown(this, party, one, two, three, four);
        }
        else if("Temple of Chaos".equals(locations.get(input))){
            templeOfChaos(one, two, three, four, party);
        }
        else if("Elfheim".equals(locations.get(input))){
            elfheim.enterTown(this, party, one, two, three, four);
        }
        else if("Western Keep".equals(locations.get(input))){
            westernKeep(one, two, three, four, party);
        }
        else if("Pravoka".equals(locations.get(input))){
        pravoka.enterTown(
                this, 
                party, 
                one, 
                two, 
                three, 
                four);
        }
    }while(true);
        }
    
    void westernKeep(Character one, Character two, Character three, Character four, Party party){
        System.out.println("You see the dark elf, Astos.");
        monster g = new monster("Astos");
        monster b = new monster("");
        monster c = new monster("");
        Combat astos = new Combat(one,two,three,four,g,b,c,new ImageIcon("Western_Keep.png").getImage());
        astos.intro("Astos",g);
        astos.battle(one,two,three,four,g,b,c,party);
        System.out.println("After defeating Astos, you return to Elfheim with the prince");
        System.out.println("King: Thank you, Light Warriors! Quickly, sail to the town of Melmond, and save the earth crystal!");
        System.out.println("The King gave you 200 gil!");
        party.addGil(200);
        difficulty++;
        System.out.println("Unlocked new location, Melmond!");
        addMelmond();
        locations.remove("Western Keep");
    }
    void templeOfChaos(Character one, Character two, Character three, Character four, Party party){
        monster g = new monster("Garland");
        monster b = new monster("");
        monster c = new monster("");
        Combat garland = new Combat(one,two,three,four,g,b,c,new ImageIcon("Chaos_Shrine.png").getImage());
        garland.intro("Garland",g);
        garland.battle(one,two,three,four,g,b,c,party);
        JOptionPane.showMessageDialog(null, "After defeating Garland, you return to Cornelia with the princess", "Final Fantasy", JOptionPane.DEFAULT_OPTION,new ImageIcon("mog.png"));
        JOptionPane.showMessageDialog(null, "Thank you, Light Warriors! Go to the town of Pravoka, a port city where you will be able to set sail! \n *The King gave you 100 gil*", "Final Fantasy", JOptionPane.DEFAULT_OPTION,new ImageIcon("King.png"));
        party.addGil(100);
        difficulty++;
        addPravoka();
        locations.remove("Temple of Chaos");
    }
}
