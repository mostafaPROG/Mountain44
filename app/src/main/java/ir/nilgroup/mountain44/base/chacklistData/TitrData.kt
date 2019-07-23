package ir.nilgroup.mountain44.base.chacklistData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TitrData(
    @PrimaryKey(autoGenerate = true)
    val titrId:Int,
    val title:String,
    val color:Int,
    val viewId:Int
)