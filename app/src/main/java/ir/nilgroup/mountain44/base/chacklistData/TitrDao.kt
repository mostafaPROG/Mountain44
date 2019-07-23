package ir.nilgroup.mountain44.base.chacklistData

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TitrDao {

    @Insert
    fun insert(titrData: TitrData)

    @Delete
    fun delete(vararg titrData: TitrData)

    @Query("select * from TitrData")
    fun getAllTitr():List<TitrData>

    @Query("select titrId from TitrData where viewId==:mId")
    fun getIdByViewId(mId:Int): Int

    @Query("delete from TitrData")
    fun deleteAll()

    @Query("delete from TitrData where viewId==:vId")
    fun deleteById(vId:Int)
}