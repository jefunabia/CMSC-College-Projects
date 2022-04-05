package designpatterns;

public class BowAndArrowBehavior implements WeaponBehavior{

	@Override
	public void useWeapon() {
		System.out.println("Thwaack… Thud");
	}

}
