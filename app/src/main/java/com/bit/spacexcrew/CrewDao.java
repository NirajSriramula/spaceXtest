package com.bit.spacexcrew;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;

import java.util.List;

@Dao
public interface CrewDao {
    @Insert
    void insert(Crew crew);

    @Query("delete from crew_table")
    void DeleteAll();

    @Query("SELECT * FROM crew_table")
    List<Crew> getAll();
}
