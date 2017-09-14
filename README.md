# RealmUtil
This library using for saving local data for Android App into Realm data

## Installing

We can install this library by using gradle

Step 1

```
allprojects {
	repositories {
			maven { url 'https://jitpack.io' }
		}
	}
```

Step 2

```
dependencies {
	        compile 'com.github.BondolTuon:RealmUtil:1.0.1'
	}
```

## Sample Using

```
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
    }
}

```

```
 <application
        android:name=".BaseApplication"
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

```


```
open class UserData(
        @PrimaryKey public open var userId: Int = 0,
        var userEmail:String? = "",
        var username:String? = ""
) : RealmObject()

```

```
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

```

## Min SDK Version

```
Support Min Sdk version >= 21

```

## Authors

* **Bondol Tuon** - [Bondol Tuon](https://github.com/BondolTuon)

See also the list of [contributors](https://github.com/BondolTuon/RealmUtil/graphs/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md(https://github.com/BondolTuon/RealmUtil/blob/master/README.md) file for details
