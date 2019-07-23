package ir.nilgroup.mountain44.base.chacklistData

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TitrData::class,SubtitrData::class],version = 1)
abstract class CheckDatabase: RoomDatabase() {

    abstract fun titrDao():TitrDao
    abstract fun subtitrDao():SubtitrDao
}