
package finalfantasy;

//Character Class
/*
 *	Title: Character
 *	Purpose: This class allows us to make Character objects that the player will control in battle throughout the course of the game.
 *	Author: Karan Shukla
 *	Class Variables:
 *		> ints: lv (character level), str (physical attack strength), mag (magical attack strength), agi (used to determine turn order)
 *				job (Character's job class variable), hit (calc. of how much damage character will do), hp (health points), exp (determines level up),
 *				death (determines if character is knocked out)
 *
 *		> Strings: name, jobClass (name of class), ability (job-specific ability), ability2 (job-specific ability), weapon (name of weapon)
 *
 *		> ImageIcon: image (character icon)
 *
 *		> boolean: focus (determines if character is using the "Focus" ability)
 *
 *		> Arrays: String[] commands (battle options)
 *
 *	Methods:
 *		> name: takes user input to name the characters
 *		> combatInput: mechanics of the combat system, including damage done, damage taken, and death
 *		>  cure: heals party if cure command is selected
 *


//Monster Class

 *	Title: Monster
 *	Purpose: This class allows us to make monster objects that the player will fight in battle throughout the course of the game.
 *	Author: Karan Shukla
 *	Class Variables:
 *		> ints: str (physical attack strength), mag (magical attack strength), agi (used to determine turn order)
 *				hit (calc. of how much damage character will do), hp (health points),
 *				death (determines if monster is knocked out)
 *
 *		> Strings: name (name of monster)
 *
 *		> ImageIcon: image (monster icon)
 *
 *	Methods:
 *		> attack: monster's attack during battle
 *		> getHit: monster taking damage during battle

//Victory Class

 *	Title: Victory
 *	Purpose: This class determines if the party won the battle and gives a cash reward
 *	Author: Karan Shukla
 *	Class Variables:
 *		> ints: gil (cash reward), exp (experience points)
 *
 *		> booleans: check1, check2, check3, check4 (check if the characters have leveled up)

 *
 *	Methods:
 *		> lose: determines if player lost, outputs message, exits battle
 *		> obtainVictory: gives party gold and exp

//Shop Class

 *	Title: Shop
 *	Purpose: This class creates Shops throughout the game that the player can buy things from
 *	Author: Karan Shukla
 *	Class Variables:
 *		> ints: cost ()
 *		> Strings: location (determines what town player is in and adjusts items availible)
 *		> Arrays: String[] display (shows items for sale), String[] inventory (player's inventory), Boolean[] type (determines if item is weapon or item)
 *
 *	Methods:
		> name: takes user input to name the characters
		> combatInput: mechanics of the combat system, including damage done, damage taken, and death
		>  cure: heals party if cure command is selected

//World Class

 *	Title:World Map
 *	Purpose: This class is basically an object that connect's all the world's locations, acting as the game's hub.
 *	Author: Karan Shukla
 *	Class Variables:
 *		> ints: difficulty (increments the enemies and their stats)
 *		> Strings: location (determines what town player is in and adjusts items availible)
 *
		> Arrays:locations[] (list of all the locations in the world that are currently accessible)
 *	Methods:
		> navigate = give players options
		> increaseDifficulty: increments difficulty of game
		>  dungeon = creates dungeons in the game


 *	Title: Party
 *	Purpose: This class holds the variables shared by the entire party of characters
 *	Author: Karan Shukla
 *	Class Variables:
 *		> ints: gil (money shared by the party)
 *
 *		> Strings: name, jobClass (name of class), ability (job-specific ability), ability2 (job-specific ability), weapon (name of weapon)

 *		> Arrays: String[] commands (battle options)

*		> Image: backdrop (battle backdrop)

 *
 *	Methods:
		> intro (starts the battle with a message)
		> battle (contains the battle functions that loop until all enemies have been slain)
		> paintComponent (paints the backdrop and the characters and monsters on it)



 *	Title: Town
 *	Purpose: This class contains the code that occurs when the player enters a town
 *	Author: Karan Shukla
 *	Class Variables:
 *		> ints: input (player input deciding what action to take)
 *
 *		> Strings: name (of town)

 *		> ArrayLists: choices (what actions the player can take)

*		> Image: backdrop (town backdrop)

 *
 *	Methods:
		> enterTown (consists of the town options and actions that occur as a result)


 *	Title: Combat
 *	Purpose: This class takes the character classes, as well as monster classes, and has them duke it out by using their stats and abilities to fight each other to the death!
 *	Author: Karan Shukla
 *	Class Variables:
 *		> ints: input (player input for battle options), deathCheck (checks how many enemies have been killed, cover (checks if, and who, is covering the player party)
 *
 *		> Strings: name, jobClass (name of class), ability (job-specific ability), ability2 (job-specific ability), weapon (name of weapon)

 *		> Array: itemList (all items held by party), weaponList (all weapons held by party)
 *
 *	Methods:
		> rest (heals the entire party to full HP)
		> useItem (uses an item and removes it)
		> equip (equips a weapon to a Character and removes the weapon)


 *	Title: FinalFantasy
 *	Purpose: This class is the runner class and constructs the character and party classes, and is not used after the creation of the World Map.
 *	Author: Karan Shukla
 *	Class Variables:
 *		> ints: input (player input for options)
 *
 *	Methods:
		> none


*/

