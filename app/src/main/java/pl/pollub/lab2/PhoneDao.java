package pl.pollub.lab2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

import androidx.room.Query;

import java.util.List;


@Dao
public interface PhoneDao {

   @Insert
    void insert(Phone phone);

   @Delete
   void delete(Phone phone);

    @Query("DELETE FROM phones")
    void deleteAllPhones();

    @Query("select * from phones")
   LiveData<List<Phone>> getAlphabetizedAllPhones();
@androidx.room.Update
   void update(Phone phone);
}
