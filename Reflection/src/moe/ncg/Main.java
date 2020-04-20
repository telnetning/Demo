package moe.ncg;

class Airplan {
    @Override
    public String toString() {
        return "In Airplan";
    }
}

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class cls = Class.forName("Airplan");
        Object obj = cls.newInstance();
        System.out.println(obj.toString());
    }

}
