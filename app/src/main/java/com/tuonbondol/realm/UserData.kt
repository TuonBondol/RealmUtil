package com.tuonbondol.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/***
 *
 * @author TUON BONDOL
 *
 */

open class UserData(
        @PrimaryKey public open var userId: Int = 0,
        var userEmail:String? = "",
        var username:String? = ""
) : RealmObject()