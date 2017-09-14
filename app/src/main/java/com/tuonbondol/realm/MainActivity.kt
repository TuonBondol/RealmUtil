package com.tuonbondol.realm

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.tuonbondol.realmutil.createRealmInstance
import com.tuonbondol.realmutil.deleteAllDataInRealmDb
import com.tuonbondol.realmutil.getDataFromRealmDb
import com.tuonbondol.realmutil.saveDataToRealmDb
import kotlinx.android.synthetic.main.activity_main.*

/***
 *
 * @author TUON BONDOL
 *
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGetUserData.setOnClickListener {
            val userData = UserData()

            val result = getDataFromRealmDb(createRealmInstance(), userData)
            var str: String? = ""
            when (result.size) {
                0 -> {
                    Toast.makeText(this, "Realm Data is Empty", Toast.LENGTH_LONG).show()
                    tvUserData.text = ""
                }
                else -> {
                    for (data in result) {
                        data as UserData
                        str = str.plus("UserId: ${data.userId}\n Email: ${data.userEmail}\n Username: ${data.username}")
                    }
                    tvUserData.text = str
                }
            }
        }

        btnSaveA.setOnClickListener {
            saveData()
        }

        btnDelete.setOnClickListener {
            if (deleteAllDataInRealmDb(createRealmInstance())) {
                tvUserData.text = ""
            }
        }
    }

    fun saveData() {
        saveDataToRealmDb(true, createRealmInstance(), UserData(1, "chandara@gmail.com", "Chan Dara"))
    }
}