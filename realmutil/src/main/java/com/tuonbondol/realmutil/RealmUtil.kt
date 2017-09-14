package com.tuonbondol.realmutil

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject
import io.realm.RealmResults

/***
 *
 * @author TUON BONDOL
 *
 */

fun createRealmInstance(): Realm = Realm.getDefaultInstance()

fun connectToRealmDb(realmDbName: String? = Realm.DEFAULT_REALM_NAME) {
    val realmConfiguration = RealmConfiguration.Builder()
            .name(realmDbName)
            .schemaVersion(0)
            //.deleteRealmIfMigrationNeeded()
            .build()
    Realm.setDefaultConfiguration(realmConfiguration)
}

fun saveDataToRealmDb(clearDara:Boolean, realm: Realm, mDataRealm: RealmObject) {
    if(clearDara){
        deleteAllDataInRealmDb(realm)
    }
    realm.executeTransaction {
        realm.copyToRealm(mDataRealm)
    }
}

fun getDataFromRealmDb(mRealm: Realm, mRealmObject: RealmObject): ArrayList<RealmObject> {
    val mRealmData = ArrayList<RealmObject>()
    val resultData = mRealm.where(mRealmObject::class.java).findAll()
    if (resultData.isLoaded)
        mRealmData.addAll(resultData)
    return mRealmData
}

fun updateDataInRealmDb(mRealm: Realm, mRealmObject: RealmObject, keyword: String, id: Int): RealmResults<out RealmObject>? = mRealm.where(mRealmObject::class.java).equalTo(keyword, id).findAll()

fun deleteAllDataInRealmDb(realm: Realm): Boolean {
    realm.executeTransaction {
        realm.deleteAll()
    }
    return true
}