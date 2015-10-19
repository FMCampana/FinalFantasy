/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package finalfantasy;

/**
 *
 * @author 151091
 */
public class Weapon {
    String name;
    int power;
    boolean[] equip = {false,false,false,false,false,false};//Warrior,Thief,Monk,Black,White,Red like Krishna sexy sh**
    
    public Weapon(String name){
        name = name;
        
        if(name.equals("Rapier")){
            power = 4;
            equip[0]=true;equip[1]=true;equip[5]=true;}
        
}

}