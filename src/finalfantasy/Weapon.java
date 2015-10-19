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
    String name, weaponType="", weaponElement="", bonusStat="";
    int power, bonus;
    boolean[] equipCheck = {false,false,false,false,false,false};//Warrior,Thief,Monk,Black,White,Red
    
    public Weapon(String x){
        name = x;
        
        if(name.equals("Rapier")){
            power = 4;
            weaponType="sword";}
        if(name.equals("Hammer")){
            power = 4;
            weaponType="hammer";}
        if(name.equals("Axe")){
            power = 8;
            weaponType="axe";
            bonusStat="agi";
            bonus=-1;}
        if(name.equals("Saber")){
            power = 8;
            weaponType="sword";}
        if(name.equals("Battle Axe")){
            power = 16;
            weaponType="axe";
            bonusStat="agi";
            bonus=-3;}
        if(name.equals("Dagger")){
            power = 3;
            weaponType="knife";
            bonusStat="agi";
            bonus=3;}
        if(name.equals("Coral Sword")){
            power = 9;
            weaponType="sword";
            weaponElement="Thunder";}
        if(name.equals("Mythril Hammer")){
            power = 8;
            weaponType="hammer";}
        if(name.equals("Defender")){
            power = 12;
            weaponType="broadsword";
            bonusStat="hp";
            bonus=100;}
        
        if(weaponType.equals("sword")){
            equipCheck[0]=true;equipCheck[1]=true;equipCheck[5]=true;}
        if(weaponType.equals("hammer")){
            equipCheck[0]=true;equipCheck[4]=true;}
        if(weaponType.equals("broadsword")||weaponType.equals("axe")){
            equipCheck[0]=true;}
        if(weaponType.equals("knife")){
            equipCheck[0]=true;equipCheck[1]=true;equipCheck[3]=true;equipCheck[5]=true;}
}

}