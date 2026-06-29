package pl.pollub.lab2;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PhoneRepository {
private final PhoneDao phoneDao;
private final LiveData<List<Phone>> phoneList;

    public PhoneRepository(Context context) {
        this.phoneDao = PhoneRoomDatabase.getDataBase(context).phoneDao();

        this.phoneList = this.phoneDao.getAlphabetizedAllPhones();
    }

    public  void insert(Phone phone){

        PhoneRoomDatabase.databaseWriteExecutor.execute(()->this.phoneDao.insert(phone));
    }
    public void delete(Phone phone){
        PhoneRoomDatabase.databaseWriteExecutor.execute(() -> this.phoneDao.delete(phone));
    }

    public void deleteAllPhones(){
        PhoneRoomDatabase.databaseWriteExecutor.execute(this.phoneDao::deleteAllPhones);
    }

    public LiveData<List<Phone>> getPhoneList(){
        return phoneList;
    }

    public void update(Phone phone){
        PhoneRoomDatabase.databaseWriteExecutor.execute(() -> this.phoneDao.update(phone));
    }

}
