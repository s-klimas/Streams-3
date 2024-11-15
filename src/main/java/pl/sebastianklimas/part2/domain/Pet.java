package pl.sebastianklimas.part2.domain;

public abstract interface Pet {
	abstract void setName(String name);

	public String getName();

	abstract public void play();

	static void playIfPet(Animal animal) {
		if (animal instanceof Pet)
			((Pet) animal).play();
	}
}
