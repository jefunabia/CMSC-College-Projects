package designpatterns;

public abstract class Character {
	private WeaponBehavior weapon;
	
	public void fight(){
		weapon.useWeapon();
	}
	
	public void setWeapon(WeaponBehavior w){
		this.weapon = w;
	}
	
	public abstract void display();
	
}
