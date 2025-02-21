package yoav.solar.model;

public class Categories extends BaseList<Category,Categories> {
    public void getAll(){
        add(new Category("Vehicles"));
        add(new Category("Dolls"));
        add(new Category("Board Games"));
        add(new Category("Educational"));
    }
}