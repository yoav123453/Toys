package yoav.solar.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import yoav.solar.model.Categories;
import yoav.solar.model.Category;

public class ToyCategoryViewModel extends ViewModel {
    private Categories toyCategories;
    private MutableLiveData<Categories> toyCategoriesLiveData;

    public ToyCategoryViewModel() {
        toyCategories = new Categories();
        toyCategories.getAll();
        toyCategoriesLiveData = new MutableLiveData<>();
        toyCategoriesLiveData.setValue(toyCategories);
    }

    public MutableLiveData<Categories> getToyCategoriesLiveData() {
        return toyCategoriesLiveData;
    }
    public void add(Category category) {
        toyCategories.addCategory(category);
        toyCategoriesLiveData.setValue(toyCategories);
    }
    public void update(Category oldCategory, Category newCategory) {
        for (int i = 0; i < toyCategories.size(); i++) {
            if (toyCategories.get(i).equals(oldCategory)) {
                toyCategories.set(i, newCategory);
                break;
            }
        }
        toyCategories.sort();
        toyCategoriesLiveData.setValue(toyCategories);
    }
    public void delete(Category category) {
        toyCategories.delete(category);
        toyCategoriesLiveData.setValue(toyCategories);
    }
}
