/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package finalfantasy;
import java.awt.Graphics;
import java.awt.Image;
import java.util.*;
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
    ArrayList<String> locations = new ArrayList<String>();
    Random generator = new Random();
    boolean fiendsSlain=false;//true when all fiends are slain, needed to enter final battle
    
        Town cornelia = new Town("Cornelia");//Cornelia is the only town the player begins with
        Town pravoka,elfheim,melmond,lufenia,crescent;//the other towns are declared, but only created once unlocked
    
        Image backdrop = ImageFetcher.fetchImage("map.jpg");
    
    public void paintComponent(Graphics g) {
        int w=(int)(backdrop.getWidth(this));
        int h=(int)(backdrop.getHeight(this));
        g.drawImage(backdrop,0,0,w,h,null);}
    
    WorldMap(){
        locations.add("Cornelia");
        
        //following are for testing purposes
//        addPravoka();
//        addElfheim();
//        addDungeon("Cavern of Earth");
//        addDungeon("Sunken Shrine");
//        addLufenia();
//        addCrescent();
//        addDungeon("Mount Gulug");
          //fiendsSlain=true;
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
    elfheim = new Town("Elfheim");
    }
    void addMelmond(){
    locations.add("Melmond");
    melmond = new Town("Melmond");
    }
    void addLufenia(){
    locations.add("Lufenia");
    lufenia = new Town("Lufenia");
    }
    void addCrescent(){
    locations.add("Crescent Lake");
    crescent = new Town("Crescent Lake");
    }
    
    void increaseDifficulty(){
    difficulty++;
    }
    
    void navigate(Party party, Character one, Character two, Character three, Character four){
    validChoice=0;
    
    Music.play("Terra.wav");
    
    JFrame mapScreen = new JFrame("World Map");
        mapScreen.setBounds(150, 25,backdrop.getWidth(this),backdrop.getHeight(this));
        mapScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        mapScreen.setResizable(false);
        mapScreen.add(new WorldMap());
        mapScreen.setIconImage(ImageFetcher.fetchImage("map.jpg"));
        mapScreen.setVisible(true);
        
        
        do{
            //Object[] locationArray = new Object[locations.size()+2];
            Object[] locationArray = new Object[locations.size()+3];
            
       for(int i=0;i<locations.size();i++)
        locationArray[i]=locations.get(i);//places the locations into the location array
       
            locationArray[locations.size()] = "Battle a mob";
            locationArray[locations.size()+1] = "Check Status";
            locationArray[locations.size()+2] = "LV Up";
            
    int input= JOptionPane.showOptionDialog(null, "What do you wish to do?", 
                "World Map", JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE, ImageFetcher.fetchIcon("mog.gif"), 
                locationArray, locationArray[0]);
    
        if(input==locations.size()){//random battle
            int battleType=generator.nextInt(difficulty);
            switch(battleType){
                case 1:
        monster d = new monster("Pirate A");
        monster e = new monster("Pirate B");
        monster f = new monster("Pirate C");
        Combat pirates = new Combat(one,two,three,four,d,e,f, ImageFetcher.fetchImage("Ship.png"));
        pirates.intro("pirate",d);
        kb.nextLine();
        pirates.battle(one,two,three,four,d,e,f,party,d);
        break;
                case 2://need to make these enemies in the monster class!
        monster g = new monster("Tarantula A");
        monster h = new monster("Tarantula B");
        monster i = new monster("Tarantula C");
        Combat tarantula = new Combat(one,two,three,four,g,h,i, ImageFetcher.fetchImage("Field.png"));
        tarantula.intro("tarantula",g);
        kb.nextLine();
        tarantula.battle(one,two,three,four,g,h,i,party,g);
        break;
                default:
        monster a = new monster("Goblin A");
        monster b = new monster("Goblin B");
        monster c = new monster("Goblin C");
        Combat goblins = new Combat(one,two,three,four,a,b,c, ImageFetcher.fetchImage("Field.png"));
        goblins.intro("goblin",a);
        goblins.battle(one,two,three,four,a,b,c,party,a);
        break;
            }
        }
        
        else if(input==locations.size()+1)//this checks status
            party.status(one, two, three, four);
        
        else if(input==locations.size()+2){//easy lv up
            one.exp(30*one.lv);two.exp(30*two.lv);three.exp(30*three.lv);four.exp(30*four.lv);party.rest(one, two, three, four);
        }
        
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
            pravoka.enterTown(this, party,one, two, three, four);
        }
        else if("Melmond".equals(locations.get(input))){
            melmond.enterTown(this, party, one, two, three, four);
        }
        else if("Cavern of Earth".equals(locations.get(input))){
            cavernOfEarth(one, two, three, four, party);
        }
        else if("Sunken Shrine".equals(locations.get(input))){
            sunkenShrine(one, two, three, four, party);
        }
        else if("Crescent Lake".equals(locations.get(input))){
            crescent.enterTown(this, party, one, two, three, four);
        }
        else if("Lufenia".equals(locations.get(input))){
            lufenia.enterTown(this, party, one, two, three, four);
        }
        else if("Mount Gulug".equals(locations.get(input))){
            mountGulug(one, two, three, four, party);
        }
        else if("Flying Fortress".equals(locations.get(input))){
            fortress(one, two, three, four, party);
        }
    }while(true);
        }
    
    void westernKeep(Character one, Character two, Character three, Character four, Party party){
        JOptionPane.showMessageDialog(null, "You see the dark elf, Astos", "Final Fantasy", JOptionPane.DEFAULT_OPTION, ImageFetcher.fetchIcon("Mog.gif"));
        monster g = new monster("Astos");
        monster b = new monster("");
        monster c = new monster("");
        Combat astos = new Combat(one,two,three,four,g,b,c, ImageFetcher.fetchImage("Western_Keep.png"));
        astos.intro("Astos",g);
        astos.battle(one,two,three,four,g,b,c,party,g);
        JOptionPane.showMessageDialog(null, "After defeating Astos, you return to Elfheim with the prince", "Final Fantasy", JOptionPane.DEFAULT_OPTION, ImageFetcher.fetchIcon("Mog.gif"));
        JOptionPane.showMessageDialog(null, "King: Thank you, Light Warriors! Quickly, sail to the town of Melmond, and save the earth crystal!\nThe Elf King gave you 200 gil!", "Final Fantasy", JOptionPane.DEFAULT_OPTION, ImageFetcher.fetchIcon("Elf_King.png"));
        party.addGil(200);
        difficulty++;
        addMelmond();
        locations.remove("Western Keep");
    }
    
    void templeOfChaos(Character one, Character two, Character three, Character four, Party party){
        if(!fiendsSlain){//first visit
            monster g = new monster("Garland");
            monster b = new monster("");
            monster c = new monster("");
            Combat garland = new Combat(one,two,three,four,g,b,c, ImageFetcher.fetchImage("Chaos_Shrine.png"));
            garland.intro("Garland",g);
            garland.battle(one,two,three,four,g,b,c,party,g);
            JOptionPane.showMessageDialog(null, "After defeating Garland, you return to Cornelia with the princess", "Final Fantasy", JOptionPane.DEFAULT_OPTION, ImageFetcher.fetchIcon("Mog.gif"));
            JOptionPane.showMessageDialog(null, "Thank you, Light Warriors! Go to the town of Pravoka, a port city where you will be able to set sail! \n *The King gave you 100 gil*", "Final Fantasy", JOptionPane.DEFAULT_OPTION, ImageFetcher.fetchIcon("King.png"));
            party.addGil(100);
            difficulty++;
            addPravoka();
            locations.remove("Temple of Chaos");}
        else{//second visit
            JOptionPane.showMessageDialog(null, "Ah... I remember you. \n\nThe last time we fought I was much weaker. You may have noticed the destruction I have wrought since then. Terrible, isn't it?\n\nReady to fight again? Not so fast! My pretties would like to kill you first!", "Garland...?", JOptionPane.DEFAULT_OPTION, ImageFetcher.fetchIcon("Garland.png"));
            monster g = new monster("Lich the Unholy");
            monster b = new monster("Kraken");
            monster c = new monster("");
            Combat garland = new Combat(one,two,three,four,g,b,c, ImageFetcher.fetchImage("Chaos_Shrine.png"));
            garland.battle(one,two,three,four,g,b,c,party,new monster("Garland"),false);//no rest after this battle!
            JOptionPane.showMessageDialog(null, "Wait! That's not all!", "Garland...?", JOptionPane.DEFAULT_OPTION, ImageFetcher.fetchIcon("Garland.png"));
            g = new monster("Marilith");
            b = new monster("Tiamat");
            garland = new Combat(one,two,three,four,g,b,c, ImageFetcher.fetchImage("Chaos_Shrine.png"));
            garland.battle(one,two,three,four,g,b,c,party,new monster("Garland"),false);
            JOptionPane.showMessageDialog(null, "Urgh!... So you've defeated all of my fiends...\n\nVery well, then. I suppose I must show you why this place is called the Temple of Chaos. \n\nThis was the home of the king of evil many centuries ago. He has died, but his soul lived on... in me! Chaos!", "Garland...?", JOptionPane.DEFAULT_OPTION, ImageFetcher.fetchIcon("Garland.png"));
            g = new monster("Chaos");
            b = new monster("");
            garland = new Combat(one,two,three,four,c,b,g, ImageFetcher.fetchImage("Final_Battle.png"));
            garland.intro("Chaos",g);
            garland.battle(one,two,three,four,g,b,c,party,g,false);
            JOptionPane.showMessageDialog(null, "What!?! No... how did you defeat me? \nI was so powerful... \nnooooooooooooooo!!!!!!!!!!!!!!!!", "Chaos, King of Evil", JOptionPane.DEFAULT_OPTION, ImageFetcher.fetchIcon("Chaos.png"));
            JOptionPane.showMessageDialog(null, "Congrats! You beat the game!", "Mog", JOptionPane.DEFAULT_OPTION, ImageFetcher.fetchIcon("Mog.gif"));
            
                }
    }
    void cavernOfEarth(Character one, Character two, Character three, Character four, Party party){
        monster b = new monster("Lich the Unholy");
        monster g = new monster("");
        monster c = new monster("");
        Combat lich = new Combat(one,two,three,four,b,c,g, ImageFetcher.fetchImage("Cavern_of_Earth.png"));
        lich.intro("Lich the Unholy",b);
        lich.battle(one,two,three,four,g,b,c,party,b);
        JOptionPane.showMessageDialog(null, "After defeating Lich, you return to Melmond.", "Final Fantasy", JOptionPane.DEFAULT_OPTION, ImageFetcher.fetchIcon("Mog.gif"));
        JOptionPane.showMessageDialog(null, "Thank you, Light Warriors! By the way, do you plan on visiting to the Sunken Shrine anytime soon?"
                + " \n I would love to see the fabled Rosetta Stone hidden away down there. I'll even fund your research! \n\t*The Good Doctor gave you 300 gil*", "Final Fantasy", JOptionPane.DEFAULT_OPTION, ImageFetcher.fetchIcon("dr_unne.png"));
        party.addGil(300);
        difficulty++;
        addDungeon("Sunken Shrine");
        locations.remove("Cavern of Earth");}
    
    void sunkenShrine(Character one, Character two, Character three, Character four, Party party){
        JOptionPane.showMessageDialog(null, "You explore the Sunken Shrine, searching for the\nRosetta Stone, when you come across a massive beast!", "Final Fantasy", JOptionPane.DEFAULT_OPTION, ImageFetcher.fetchIcon("Mog.gif"));
        monster g = new monster("Kraken");
        monster b = new monster("");
        monster c = new monster("");
        Combat kraken = new Combat(one,two,three,four,b,g,c, ImageFetcher.fetchImage("Sunken_Shrine.png"));
        kraken.intro("Kraken",g);
        kraken.battle(one,two,three,four,b,g,c,party,g);
        JOptionPane.showMessageDialog(null, "You return to Melmond with the Rosetta Stone.", "Final Fantasy", JOptionPane.DEFAULT_OPTION, ImageFetcher.fetchIcon("Mog.gif"));
        JOptionPane.showMessageDialog(null, "Ah the Rosetta Stone! It translates the strange language of Lufenian... here, you keep it!\nWith this, you can communicate with the Lufenian peoples!\n\n*unlocked Lufenia*", "Final Fantasy", JOptionPane.DEFAULT_OPTION, ImageFetcher.fetchIcon("dr_unne.png"));
        party.remove("Item","Rosetta Stone");
        difficulty++;
        addLufenia();
        locations.remove("Sunken Shrine");
    }
    void mountGulug(Character one, Character two, Character three, Character four, Party party){
        JOptionPane.showMessageDialog(null, "You enter the fiery mountain. The heat heats you.", "Final Fantasy", JOptionPane.DEFAULT_OPTION, ImageFetcher.fetchIcon("Mog.gif"));
        monster g = new monster("Marilith");
        monster b = new monster("");
        monster c = new monster("");
        Combat marilith = new Combat(one,two,three,four,b,g,c, ImageFetcher.fetchImage("Mount_Gulg.png"));
        marilith.intro("Marilith",g);
        marilith.battle(one,two,three,four,b,g,c,party,g);
        JOptionPane.showMessageDialog(null, "You return to Crescent Lake.", "Final Fantasy", JOptionPane.DEFAULT_OPTION, ImageFetcher.fetchIcon("Mog.gif"));
        JOptionPane.showMessageDialog(null, "You truly have defeated the fiend of fire? You have out trust. Tell the Lufenian Elder of our words.", "Lukahn", JOptionPane.DEFAULT_OPTION, ImageFetcher.fetchIcon("Lukahn.png"));
        difficulty++;
        locations.remove("Mount Gulug");
        lufenia.trust=true;
        lufenia.choices.add("Talk to the Lufenian Elder");
    }
    void fortress(Character one, Character two, Character three, Character four, Party party){
        JOptionPane.showMessageDialog(null, "You fly through the skies! \nAbove the clouds! \nOver the wind! \nAnd there you see...", "Final Fantasy", JOptionPane.DEFAULT_OPTION, ImageFetcher.fetchIcon("Mog.gif"));
        JOptionPane.showMessageDialog(null, "A monstrous castle...", "Final Fantasy", JOptionPane.DEFAULT_OPTION, ImageFetcher.fetchIcon("Mog.gif"));
        JOptionPane.showMessageDialog(null, "You enter it, and discover the final fiend: a gargantuan dragon!", "Final Fantasy", JOptionPane.DEFAULT_OPTION, ImageFetcher.fetchIcon("Mog.gif"));
        monster g = new monster("Tiamat");
        monster b = new monster("");
        monster c = new monster("");
        Combat tiamat = new Combat(one,two,three,four,b,g,c, ImageFetcher.fetchImage("Mount_Gulg.png"));
        tiamat.intro("Tiamat",g);
        tiamat.battle(one,two,three,four,b,g,c,party,g);
        JOptionPane.showMessageDialog(null, "The final fiend slain, your team returns to Cid to tell him of this news.", "Final Fantasy", JOptionPane.DEFAULT_OPTION, ImageFetcher.fetchIcon("Mog.gif"));
        JOptionPane.showMessageDialog(null, "Amazing... all four fiends slain... this is no fluke. YOU are the LIGHT WARRIORS!\n\n"
                + "Return to the Temple of Chaos, home of the evil knight Garland! Now that darkness has been banished from the land, you must destroy its source!", "Cid, the Lufenian Elder", JOptionPane.DEFAULT_OPTION, ImageFetcher.fetchIcon("Cid.png"));
        difficulty++;
        locations.remove("Flying Fortress");
        addDungeon("Temple of Chaos");
        fiendsSlain=true;
    }
}
