package yoav.solar.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import yoav.solar.model.Categories;

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
}
