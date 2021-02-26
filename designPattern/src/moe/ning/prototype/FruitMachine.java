package moe.ning.prototype;

import java.util.Random;

/**
 * FruitMachine
 *
 * @author telnetning
 * @since 2021-02-26
 */

class Fruit implements Cloneable {
    @Override protected Object clone() throws CloneNotSupportedException
    {
        return (Fruit) super.clone();
    }

    protected void descript()
    {
        System.out.println(this.getClass().getName());
    }
}

class Apple extends Fruit {}
class Orange extends Fruit {}
class Banana extends Fruit {}

public class FruitMachine
{
    private Fruit fruit;

    public FruitMachine(Fruit fruit)
    {
        this.fruit = fruit;
    }

    public Fruit makeFruit()
    {
        try {
            return (Fruit) this.fruit.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args)
    {
        System.out.println("Machine start:");
        FruitMachine appleMachine = new FruitMachine(new Apple());
        FruitMachine orangeMachine = new FruitMachine(new Orange());
        FruitMachine bananaMachine = new FruitMachine(new Banana());

        int rand = 0;
        Random random = new Random();
        for(int i = 0; i < 20; i++)
        {
            rand = random.nextInt((100 - 1)) + 1;
            if (rand % 3 == 0) {
                appleMachine.makeFruit().descript();
            } else if(rand % 3 == 1) {
                orangeMachine.makeFruit().descript();
            } else {
                bananaMachine.makeFruit().descript();
            }
        }
    }
}
