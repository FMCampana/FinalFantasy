/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package finalfantasy;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
/**
 *
 * @author karanshukla
 */
public class Combat extends JPanel{
    
    Random generator = new Random();
    int number, speed, input, x, attack, fire, cure, target, deathCheck;
    int cover = 0;
    int monsterDeathCheck=0, partyDeathCheck=0;
    String[] commands;
    Scanner kb = new Scanner(System.in);
    ImageIcon i1,i2,i3,i4,ia,ib,ic;
    Image backdrop;
    monster aa,bb,cc;
    Boolean adeath=false;
    Boolean bdeath=false;
    Boolean cdeath=false;
    
    boolean rest=true;
    
    Character ek,dough,theen,chaar,ah,beh,se;
        
    void intro (String name, monster b) {
        if(name.equals("Garland"))
            JOptionPane.showMessageDialog(null, "Garland: You came here to save the princess? \nI, Garland, shall knock you all down!", "Final Fantasy", JOptionPane.WARNING_MESSAGE, b.image);
        else if(name.equals("Bikke"))
            JOptionPane.showMessageDialog(null, "Bikke: Ye want to take back this port? Ye'll have to get through me first!", "Final Fantasy", JOptionPane.WARNING_MESSAGE, b.image);
        else if(name.equals("Astos"))
            JOptionPane.showMessageDialog(null, "Astos: You want to rescueThranduil Tasartir? You'll have to pay a ransom... a ransom of blood!", "Final Fantasy", JOptionPane.WARNING_MESSAGE, b.image);
        else if(name.equals("Lich the Unholy Troll"))
            JOptionPane.showMessageDialog(null, "Lich the Unholy Troll: @!#%#$^@*#$%!#$", "Final Fantasy", JOptionPane.WARNING_MESSAGE, b.image);
        else if(name.equals("Kraken"))
            JOptionPane.showMessageDialog(null, "Kraken: Hohoho! Exploring the depths, I see.\nCan't let you roam in my waters!", "Final Fantasy", JOptionPane.WARNING_MESSAGE, b.image);
        else if(name.equals("Marilith"))
            JOptionPane.showMessageDialog(null, "Marilith: You may have collected the other crystals...but that is of no consequence. I shall devour your soul!", "Final Fantasy", JOptionPane.WARNING_MESSAGE, b.image);
        else if(name.equals("Tiamat"))
            JOptionPane.showMessageDialog(null, "Tiamat: RAWWWR!!! BEGONE LIGHT WARRIORS!!! THE FLYING FORTRESS IS HOME TO ME AND ME ALONE!!!", "Final Fantasy", JOptionPane.WARNING_MESSAGE, b.image);
        else if(name.equals("Chaos"))
            JOptionPane.showMessageDialog(null, "Garland transforms into Chaos!", "Final Fantasy", JOptionPane.WARNING_MESSAGE, b.image);
        else
            JOptionPane.showMessageDialog(null, "A group of "+name+"s appeared!", "Final Fantasy", JOptionPane.WARNING_MESSAGE, b.image);
       cover = 0;
    }
    public Combat(Character one, Character two, Character three, Character four, monster a, monster b, monster c,
            Image bd) {
        
        backdrop=bd;
        i1=one.image;
        i2=two.image;
        i3=three.image;
        i4=four.image;
        ia=a.image;
        ib=b.image;
        ic=c.image;
    
        Character ek=one;
        Character dough=two;
        Character theen=three;
        Character chaar=four;
        monster ah=a;
        monster beh=b;
        monster se=c;
        }
        
        
    public void paintComponent(Graphics g) {
super.paintComponent(g);

int w=(int)(3*backdrop.getWidth(this));
int h=(int)(3*backdrop.getHeight(this));

g.drawImage(backdrop,-10,-10,w,h,null);
g.drawImage(i1.getImage(),(int)w*5/8,(int)h*1/6,null); //Draws a character at the specified location
g.drawImage(i2.getImage(),(int)w*5/8,(int)h*2/6,null); //Draws a character at the specified location
g.drawImage(i3.getImage(),(int)w*5/8,(int)h*3/6,null); //Draws a character at the specified location
g.drawImage(i4.getImage(),(int)w*5/8,(int)h*4/6,null); //Draws a character at the specified location
if(adeath==false&&ia!=null)g.drawImage(ia.getImage(),(int)w*5/16,(int)h*5/12,null); //Draws a character at the specified location
if(bdeath==false&&ib!=null)g.drawImage(ib.getImage(),(int)w*1/5,(int)h*3/12,null); //Draws a character at the specified location
if(cdeath==false&&ic!=null)g.drawImage(ic.getImage(),(int)w*1/5,(int)h*7/12,null); //Draws a character at the specified location

  }
    
    void cover(int covername){
        cover = covername;
    }
    
    void targets(monster a){
        target = 1;
    }
    void targets(monster a, monster b, monster c){
        target = 3;}
    
    void checkStatus(Character ek, Character dough, Character theen, Character chaar, monster ah, monster beh, monster se){
        String message="";
        if(ek.death==0)message=message+ek.name+" HP:"+ek.tempHP+"/"+ek.hp+"\n\n";
        if(dough.death==0)message=message+dough.name+" HP:"+dough.tempHP+"/"+dough.hp+"\n\n";
        if(theen.death==0)message=message+theen.name+" HP:"+theen.tempHP+"/"+theen.hp+"\n\n";
        if(chaar.death==0)message=message+chaar.name+" HP:"+chaar.tempHP+"/"+chaar.hp+"\n\n";
        if(ah.death==0)message=message+ah.name+" HP:"+ah.tempHP+"/"+ah.hp+"\n\n";
        if(beh.death==0)message=message+beh.name+" HP:"+beh.tempHP+"/"+beh.hp+"\n\n";
        if(se.death==0)message=message+se.name+" HP:"+se.tempHP+"/"+se.hp+"\n\n";
        JOptionPane.showMessageDialog(null,message,"Battle Status",JOptionPane.INFORMATION_MESSAGE);
    }
    
    //input is essentially the interface of combat
    int input (Character self, monster a, monster b, monster c) {
        int input;//This is simply the player's input
        ArrayList<String> status = new ArrayList<String>();//status effects
        if(self.blind)status.add("Blinded");
        if(self.tempHP<self.hp/3)status.add("Critical");
        if(status.size()==0)status.add("Healty");
        
        if(self.job==6)//Red Mages have 2 job abilities instead of one, and thus need a special interface
            return 1+JOptionPane.showOptionDialog(null, "Input commands for " + self.name + " (HP: " + self.tempHP + "/" + self.hp + ") (Status: "+status+")", 
                "Battle!", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, self.image, self.commandsRed, self.commandsRed[0]);
        else
            return 1+JOptionPane.showOptionDialog(null, "Input commands for " + self.name + " (HP: " + self.tempHP + "/" + self.hp + ") (Status: "+status+")", 
                "Battle!", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, self.image, self.commands, self.commands[0]);
}
    
    
    void battle(Character one, Character two, 
            Character three, Character four, monster a, monster b, monster c, Party party, monster dispMon, boolean x){
        rest=x;
        battle(one,two,three,four,a,b,c,party,dispMon);
    }
    
    //This is the meat of the battle system
    void battle(Character one, Character two, 
            Character three, Character four, monster a, monster b, monster c, Party party, monster dispMon){
        
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int counta = 0;
        int countb = 0;
        int countc = 0;
        
        Character ek=one;
        Character dough=two;
        Character theen=three;
        Character chaar=four;
        monster ah=a;
        monster beh=b;
        monster se=c;
        
        int worth = a.worth+b.worth+c.worth;
        
            //The following creates teh battle screen, painted in teh paintcomponent method
        JFrame battleScreen = new JFrame("Battle Screen");
        battleScreen.setBounds(100, 100, 720, 393);
        battleScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        battleScreen.setResizable(false);
        battleScreen.add(new Combat(one,two,three,four,a,b,c,backdrop));
        battleScreen.setIconImage(dispMon.image.getImage());
        battleScreen.setVisible(true);
        
        //This should run forever, without breaking, until every enemy is slain
        while(monsterDeathCheck!=3 && partyDeathCheck!=4){
            
            if(a.death==1)adeath=true;
            if(b.death==1)bdeath=true;
            if(c.death==1)cdeath=true;
            
            count1+=one.agi;
            count2+=two.agi;
            count3+=three.agi;
            count4+=four.agi;
            
            counta+=a.agi;
            countb+=b.agi;
            countc+=c.agi;
            
            //We have to death check after every action
            
            if(count1>=100 && one.death==0 && monsterDeathCheck!=3){
            one.combatInput(this,a,b,c,one,two,three,four, party);
            count1=battleCheck(a,b,c,one,two,three,four,count1,battleScreen);
            }
            
            if(count2>=100 && two.death==0 && monsterDeathCheck!=3){
            two.combatInput(this,a,b,c,one,two,three,four, party);
            count2=battleCheck(a,b,c,one,two,three,four,count2,battleScreen);
            }
            
            if(count3>=100 && three.death==0 && monsterDeathCheck!=3){
            three.combatInput(this,a,b,c,one,two,three,four, party);
            count3=battleCheck(a,b,c,one,two,three,four,count3,battleScreen);
            }
            
            if(count4>=100 && four.death==0 && monsterDeathCheck!=3){
            four.combatInput(this,a,b,c,one,two,three,four, party);
            count4=battleCheck(a,b,c,one,two,three,four,count4,battleScreen);
            }
            
            if(counta>=100 && a.death==0 && monsterDeathCheck!=3){
            a.enemyAttack(this, one, two, three, four);
            counta=battleCheck(a,b,c,one,two,three,four,counta,battleScreen);
            }
            
            if(countb>=100 && b.death==0 && monsterDeathCheck!=3){
            b.enemyAttack(this, one, two, three, four);
            countb=battleCheck(a,b,c,one,two,three,four,countb,battleScreen);
            }
            
            if(countc>=100 && c.death==0 && monsterDeathCheck!=3){
            c.enemyAttack(this, one, two, three, four);
            countc=battleCheck(a,b,c,one,two,three,four,countc,battleScreen);
            }
            
        }
            Victory victory = new Victory();
        if(monsterDeathCheck==3)
            victory.obtainVictory(worth, one, two, three, four, party,battleScreen,rest);
        else if(partyDeathCheck==4)
            victory.lose(b.image);
    }
    
    int battleCheck(monster a, monster b, monster c, Character one, Character two, Character three, Character four,
            int count,JFrame battleScreen){
            count-=100;
            monsterDeathCheck = a.death+b.death+c.death;
            partyDeathCheck = one.death+two.death+three.death+four.death;
            if(a.death==1)
                adeath=true;
            if(b.death==1)
                bdeath=true;
            if(c.death==1)
                cdeath=true;
            
            //battleScreen.repaint();
            
            return count;
    }
    
    int attack(int str, int atk){//used for characters, since characters have weapons
        attack=str+atk;
        int x = generator.nextInt(34)+100;
        double dattack=attack*x/100;
        attack=(int)dattack;
        return attack; //The power of a char's attack is equal to the char's STR times a random value between 1 and 1.33
    }
    
    int attack(int str){//used for monsters, since monsters do not have weapons
        int x = generator.nextInt(51)+100;
        attack=(int)(str*x/100);
        return attack; //The power of a monster's attack is equal to the monster's STR times a random value between 1 and 1.5
    }
    
    
    
}
