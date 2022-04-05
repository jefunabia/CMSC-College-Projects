package designpatterns;

public class CharacterTest {
	public static void main(String[] args){
		Character c1 = new King();
		c1.setWeapon(new KnifeBehavior());
		c1.fight();
		
		Character c2 = new Knight();
		c2.setWeapon(new AxeBehavior());
		c2.fight();
		
		Character c3 = new Queen();
		c3.setWeapon(new BowAndArrowBehavior());
		c3.fight();
		
		Character c4 = new Knight();
		c4.setWeapon(new SwordBehavior());
		c4.fight();
		
		Character c5 = new Troll();
		c5.setWeapon(new AxeBehavior());
		c5.fight();
		
	}
}
