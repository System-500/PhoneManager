package pl.pollub.lab2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PhoneViewModel extends AndroidViewModel {
    private final PhoneRepository repository;
    private final LiveData<List<Phone>> phoneList;

    public PhoneViewModel(@NonNull Application application) {
        super(application);
        this.repository = new PhoneRepository(application);
        this.phoneList = this.repository.getPhoneList();

    }

    LiveData<List<Phone>> getPhoneList() {
        return phoneList;
    }

    public void deletePhone(Phone phone) {
        this.repository.delete(phone);
    }

    public void insertPhone(Phone phone) {
        this.repository.insert(phone);
    }

    public void updatePhone(Phone newPhone) {
        this.repository.update(newPhone);
    }


    public void deleteAllPhones() {
        this.repository.deleteAllPhones();

    }

}