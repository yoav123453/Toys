package yoav.solar.model;

public class Toys extends BaseList<Toy,Toys> {
    public void getAll(){
        add(new Toy("car",50));
        add(new Toy("Doll",20));
        add(new Toy("Robot",100));
        add(new Toy("Taki",75));
        add(new Toy("Chess",100));
    }
}
