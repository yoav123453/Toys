package yoav.solar.model;

import java.util.Collections;
public class Categories extends BaseList<Category,Categories> {
    public void getAll(){
        add(new Category("Vehicles"));
        add(new Category("Dolls"));
        add(new Category("Board Games"));
        add(new Category("Educational"));
        sort();
    }
    public void addCategory(Category category) {
        if (!exist(category)) {
            add(category);
            sort();
        }
    }

    public boolean exist(Category category) {
        for (Category c : this) {
            if (c.getName().equalsIgnoreCase(category.getName())) {
                return true;
            }
        }
        return false;
    }
    public void delete(Category category) {
        for (int i = 0; i < size(); i++) {
            if (get(i).equals(category)) {
                remove(i);
                return;
            }
        }
    }
    public void sort() {
        Collections.sort(this, (c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
    }
}