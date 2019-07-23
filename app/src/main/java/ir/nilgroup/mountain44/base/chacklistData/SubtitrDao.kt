package ir.nilgroup.mountain44.base.chacklistData

import androidx.room.*

@Dao
interface SubtitrDao {

    @Insert
    fun insert(subtitrData: SubtitrData)

    @Delete
    fun delete(vararg subtitrData: SubtitrData)

    @Query("select * from SubtitrData")
    fun getAllTitr():List<SubtitrData>

    @Query("select * from SubtitrData where subId==:mTitrId")
    fun getSubTitrById(mTitrId:Int):List<SubtitrData>

    @Query("select * from subtitrdata where parentId==:id")
    fun getSubtitrByParentId(id:Int):List<SubtitrData>

    @Query("update subtitrdata set checked=:check where parentId==:pId")
    fun updateSubtitr(check:Boolean,pId:Int)
}