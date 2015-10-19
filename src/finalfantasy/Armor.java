/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package finalfantasy;

/**
 *
 * @author 151091
 */
public class Armor {
    String name, armorType="", armorElement="", bonusStat="";
    int power, bonus;
    boolean[] equipCheck = {false,false,false,false,false,false};//Warrior,Thief,Monk,Black,White,Red
    
    public Armor(String x){
        name = x;
        
        if(name.equals("Leather Armor")){
            power = 1;
            armorType="basic";}
        
        if(armorType.equals("basic")){
            equipCheck[0]=true;equipCheck[1]=true;equipCheck[2]=true;equipCheck[3]=true;equipCheck[4]=true;equipCheck[5]=true;}
}

}