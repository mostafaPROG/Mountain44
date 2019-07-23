package ir.nilgroup.mountain44.base.chacklistData

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = [ForeignKey(
        entity = TitrData::class,
        parentColumns = arrayOf("titrId"),
        childColumns = arrayOf("parentId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class SubtitrData(
    @PrimaryKey(autoGenerate = true)
    val subId: Int,
    val parentId: Int,
    val subtitle: String,
    val checked: Boolean
)