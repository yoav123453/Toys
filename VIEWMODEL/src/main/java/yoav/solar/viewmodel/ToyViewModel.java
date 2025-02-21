package yoav.solar.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import yoav.solar.model.Toys;

public class ToyViewModel extends ViewModel {
    private Toys toys;
    private MutableLiveData<Toys> toysLiveData;

    public ToyViewModel() {
        toys = new Toys();
        toys.getAll();

        toysLiveData = new MutableLiveData<>();
        toysLiveData.setValue(toys);
    }

    public MutableLiveData<Toys> getToysLiveData() {
        return toysLiveData;
    }
}
