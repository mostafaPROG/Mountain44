package ir.nilgroup.mountain44.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.hardware.SensorManager.SENSOR_DELAY_GAME
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.room.Room
import com.bumptech.glide.Glide
import com.kwabenaberko.openweathermaplib.constants.Lang
import com.kwabenaberko.openweathermaplib.constants.Units
import com.kwabenaberko.openweathermaplib.implementation.OpenWeatherMapHelper
import com.kwabenaberko.openweathermaplib.implementation.callbacks.CurrentWeatherCallback
import com.kwabenaberko.openweathermaplib.models.currentweather.CurrentWeather
import github.nisrulz.lantern.Lantern
import ir.nilgroup.mountain44.Base.Utilities
import ir.nilgroup.mountain44.Base.weatherData.WeatherDatabase
import ir.nilgroup.mountain44.Base.weatherData.WeatherResponse
import ir.nilgroup.mountain44.R
import ir.nilgroup.mountain44.activities.ChecklistActivity
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.Logger

class AppFragment : AppCompatActivity()
    , SensorEventListener {

    val URL = "http://api.openweathermap.org/"
    val app_id = "098135d42c758c6286908437eac2471b"
    private lateinit var listener: LocationListener
    lateinit var locationManager: LocationManager
    private lateinit var location: Location
    private lateinit var lantern: Lantern

    private val REQUEST_CODE = 100
    var flag = false
    lateinit var btnFlashlight: ImageView
    lateinit var btnSos: ImageView

    private lateinit var height: TextView
    lateinit var temprateDegree: TextView
    lateinit var currentCity: TextView
    lateinit var weatherState: TextView
    lateinit var windSpeed: TextView
    lateinit var humidity: TextView
    lateinit var pressure: TextView
    lateinit var cloudiness: TextView
    lateinit var lastUpdating: TextView
    val RC_ENABLE_LOCATION = 1
    val RC_LOCATION_PERMISSION = 2
    lateinit var getLocation: Button
    lateinit var iconWeather: ImageView
    lateinit var refreshWeather: Button

    lateinit var sensorManager: SensorManager
    lateinit var image: ImageView
    lateinit var accelerometer: Sensor
    lateinit var magnetometer: Sensor
    lateinit var textDegree: TextView
    var currentDegree = 0.0f
    var lastAccelerometer = FloatArray(3)
    var lastMagnetometer = FloatArray(3)
    var lastAccelerometerSet = false
    var lastMagnetometerSet = false

    lateinit var helper: OpenWeatherMapHelper
    private lateinit var db: WeatherDatabase
    lateinit var prgBarWeath: ProgressBar

    @SuppressLint("SimpleDateFormat", "MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tools_layout2)

        helper = OpenWeatherMapHelper(getString(R.string.weather_api))

        db = Room.databaseBuilder(this@AppFragment, WeatherDatabase::class.java, "weatherDatabase")
            .allowMainThreadQueries()
            .build()

        findViewById<CardView>(R.id.checkListId).setOnClickListener {
            startActivity(Intent(this, ChecklistActivity::class.java))
        }
        //    getHeight
        heightGet()

        //    toolbar
        toolbarInit()

        //    flashLight
        flashlightInit()

        //      calendar
        calendarInit()

        //      Weather
        weatherInit()

        //      Compass
        compassInit()

        //      pressure init
        pressureInit()

    }

    private fun weatherInit() {
        iconWeather = findViewById(R.id.iconWeatherL)
        getLocation = findViewById(R.id.getGpsWeatherL)
        temprateDegree = findViewById(R.id.degreeTempL)
        currentCity = findViewById(R.id.currentCityL)
        weatherState = findViewById(R.id.weatherStateL)
        windSpeed = findViewById(R.id.windSpeedL)
        humidity = findViewById(R.id.humidityL)
        pressure = findViewById(R.id.pressureL)
        cloudiness = findViewById(R.id.cloudinessL)
        lastUpdating = findViewById(R.id.lastUpdatingL)
        prgBarWeath = findViewById(R.id.progressBarWeather)
        refreshWeather = findViewById(R.id.fabL)

        helper.setLang(Lang.PERSIAN)
        helper.setUnits(Units.METRIC)

        if (db.weatherDao().getAll().isEmpty() || isNetworkAvailable()) {
            getWeatherByCity()
        } else {
            getWeatherFromDb()
        }

        refreshWeather.setOnClickListener {
            getWeatherByCity()
        }
    }

    private fun getInternetPermission() {
        if (!isNetworkAvailable()) {
            Toast.makeText(this, "لطفا اتصال اینترنت دستگاه خود را بررسی کنید!", Toast.LENGTH_LONG).show()
        }
    }

    private fun getWeatherFromDb() {
        val response = db.weatherDao().getAll()[0]
        temprateDegree.text = response.temp.toString()
        currentCity.text = response.name.toString()
        weatherState.text = response.main
        windSpeed.text = response.speed.toString()
        humidity.text = response.humidity.toString()
        pressure.text = response.pressure.toInt().toString()
        cloudiness.text = response.all.toString()
        lastUpdating.text = convertUnixToDate(response.dt)
        height.text = response.seaLevel.toInt().toString()

        val stringBuilder = "pic${response.icon}"

        Logger.getLogger("Tag").warning("string path :  $stringBuilder")
        val id = resources.getIdentifier(stringBuilder, "drawable", this@AppFragment.packageName)
        Logger.getLogger("Tag").warning("string drawable :  ${this@AppFragment.packageName}")
        Glide.with(this@AppFragment).load(id).into(iconWeather)

        prgBarWeath.visibility = View.INVISIBLE
    }

    private fun getWeatherByCity() {
        helper.getCurrentWeatherByCityName("تربت حیدریه", object : CurrentWeatherCallback {
            override fun onSuccess(currentWeather: CurrentWeather?) {
                initialUi(currentWeather!!)
                prgBarWeath.visibility = View.INVISIBLE
            }

            override fun onFailure(throwable: Throwable?) {

            }
        })
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager: ConnectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        return (activeNetworkInfo != null && activeNetworkInfo.isConnected)
    }

    @Throws(Exception::class)
    private fun setMobileDataEnabled(context: Context, enabled: Boolean) {
        val conman = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var conmanClass: Class<*>? = null
        try {
            conmanClass = Class.forName(conman.javaClass.getName())
        } catch (e: ClassNotFoundException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
        val iConnectivityManagerField = conmanClass!!.getDeclaredField("mService")
        iConnectivityManagerField.isAccessible = true
        val iConnectivityManager = iConnectivityManagerField.get(conman)
        val iConnectivityManagerClass = Class.forName(iConnectivityManager.javaClass.name)
        val setMobileDataEnabledMethod =
            iConnectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", java.lang.Boolean.TYPE)
        setMobileDataEnabledMethod.isAccessible = true
        setMobileDataEnabledMethod.invoke(iConnectivityManager, enabled)
    }

    private fun pressureInit() {
        val stringBuilder1 = "vector_press_back_hand"
        val stringBuilder2 = "vector_press_back"
        var id3 = resources.getIdentifier(stringBuilder1, "drawable", this@AppFragment.packageName)
        var id2 = resources.getIdentifier(stringBuilder2, "drawable", this@AppFragment.packageName)
        Glide.with(this@AppFragment).load(id3).into(findViewById(R.id.imageView12))
        Glide.with(this@AppFragment).load(id2).into(findViewById(R.id.imageView9))
    }

    @SuppressLint("MissingPermission")
    private fun heightGet() {
        height = findViewById(R.id.heightL)
//        val ref: = findViewById(R.id.refHeight)
    }


    private fun compassInit() {
        image = findViewById(R.id.compassBackL)
        textDegree = findViewById(R.id.text_compassL)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

        val stringBuilder1 = "vector_compass_back"
        val stringBuilder2 = "vector_compass_hand"
        val id3 = resources.getIdentifier(stringBuilder1, "drawable", this@AppFragment.packageName)
        val id2 = resources.getIdentifier(stringBuilder2, "drawable", this@AppFragment.packageName)
        Glide.with(this@AppFragment).load(id3).into(findViewById(R.id.compassBackL))
        Glide.with(this@AppFragment).load(id2).into(findViewById(R.id.imageViewCompassL))
    }


    private fun toolbarInit() {
        val toolbar: Toolbar = findViewById(R.id.toolsToolbarL)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    @SuppressLint("SimpleDateFormat")
    private fun calendarInit() {
        val timeT: TextView = findViewById(R.id.timeCardL)
        val dateT: TextView = findViewById(R.id.dateCardL)
        val utilities = Utilities.getCurrentShamsidate()
        val tf = SimpleDateFormat("HH:mm")
        timeT.text = tf.format(Calendar.getInstance().time)
        dateT.text = utilities
    }

    private fun flashlightInit() {
        btnFlashlight = findViewById(R.id.btnFlashlightL)
        btnSos = findViewById(R.id.btnSosL)
        lantern = Lantern(this)
            .checkAndRequestSystemPermission()
            .observeLifecycle(this)

        val list = arrayOf(Manifest.permission.CAMERA)
        if (!lantern.initTorch()) {
            ActivityCompat.requestPermissions(this, list, REQUEST_CODE);
        }

        btnFlashlight.setOnClickListener {
            when (flag) {
                true -> {
                    turnOffFlash()
                }
                false -> {
                    turnOnFlash()
                }
            }
        }

    }

    // flashLight
    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE) {
            if (!lantern.initTorch()) {
                Toast.makeText(this, "Camera Permission Denied!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun turnOnFlash() {
        btnFlashlight.setImageResource(R.drawable.on_button)
        lantern
            .enableTorchMode(true)
            .pulse(false)
        flag = true
    }

    private fun turnOffFlash() {
        btnFlashlight.setImageResource(R.drawable.off_button)
        lantern
            .enableTorchMode(false)
            .pulse(false)
        flag = false
    }


    fun hasPermissions(context: Context, vararg permissions: String): Boolean = permissions.all {
        ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationUpdates() {
        val provider = LocationManager.NETWORK_PROVIDER
        do {
            //Add the location listener and request updated
            locationManager.requestLocationUpdates(provider, 0, 0.0f, listener)
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0.0f, listener)

            val location = locationManager.getLastKnownLocation(provider)
            listener.onLocationChanged(location)
        } while (location == null)
    }


    @SuppressLint("SetTextI18n")
    fun initialUi(response: CurrentWeather) {
        temprateDegree.text = "${response.main!!.temp.toInt()}"
        currentCity.text = response.name.toString()
        weatherState.text = response.weather[0].main
        windSpeed.text = response.wind!!.speed.toString()
        humidity.text = response.main!!.humidity.toString()
        pressure.text = response.main!!.pressure.toString()
        cloudiness.text = response.clouds!!.all.toString()
        lastUpdating.text = convertUnixToDate(response.dt)
        height.text = response.main.seaLevel.toInt().toString()


        val stringBuilder = "pic${response.weather[0].icon}"

        Logger.getLogger("Tag").warning("string path :  $stringBuilder")
        val id = resources.getIdentifier(stringBuilder, "drawable", this@AppFragment.packageName)
        Logger.getLogger("Tag").warning("string drawable :  ${this@AppFragment.packageName}")
        Glide.with(this@AppFragment).load(id).into(iconWeather)

        if (db.weatherDao().getAll().isEmpty()) {
            db.weatherDao().insertAll(WeatherResponse().ConvResponse(response))
        } else {
            db.weatherDao().update(WeatherResponse().ConvResponse(response))
        }

    }

    @SuppressLint("SimpleDateFormat")
    private fun convertUnixToDate(dt: Long): String? {
        val date = Date(dt * 1000L)
        val simpleDateFormat = SimpleDateFormat("HH:mm EEE MM yyyy")
        return simpleDateFormat.format(date)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}


    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor === accelerometer) {
            lowPass(event.values, lastAccelerometer)
            lastAccelerometerSet = true
        } else if (event.sensor === magnetometer) {
            lowPass(event.values, lastMagnetometer)
            lastMagnetometerSet = true
        }

        if (lastAccelerometerSet && lastMagnetometerSet) {
            val r = FloatArray(9)
            if (SensorManager.getRotationMatrix(r, null, lastAccelerometer, lastMagnetometer)) {
                val orientation = FloatArray(3)
                SensorManager.getOrientation(r, orientation)
                val degree = (Math.toDegrees(orientation[0].toDouble()) + 360).toFloat() % 360
                textDegree.text = degree.toInt().toString()
                var x = degree.toInt()
                when (x) {
                    in 337..360 -> {
                        textDegree.text = " ${degree.toInt()}° ${this.getString(R.string.sotwFa_north)}"
                    }
                    in 0..23 -> {
                        textDegree.text = " ${degree.toInt()}° ${this.getString(R.string.sotwFa_north)}"
                    }
                    in 23..67 -> {
                        textDegree.text = " ${degree.toInt()}° ${this.getString(R.string.sotwFa_northeast)}"
                    }
                    in 67..112 -> {
                        textDegree.text = " ${degree.toInt()}° ${this.getString(R.string.sotwFa_east)}"
                    }
                    in 112..157 -> {
                        textDegree.text = " ${degree.toInt()}° ${this.getString(R.string.sotwFa_southeast)}"
                    }
                    in 157..202 -> {
                        textDegree.text = " ${degree.toInt()}° ${this.getString(R.string.sotwFa_south)}"
                    }
                    in 202..247 -> {
                        textDegree.text = " ${degree.toInt()}° ${this.getString(R.string.sotwFa_southwest)}"
                    }
                    in 247..292 -> {
                        textDegree.text = " ${degree.toInt()}° ${this.getString(R.string.sotwFa_west)}"
                    }
                    in 292..337 -> {
                        textDegree.text = " ${degree.toInt()}° ${this.getString(R.string.sotwFa_northwest)}"
                    }
                    else -> {
                    }
                }

                val rotateAnimation = RotateAnimation(
                    currentDegree,
                    -degree,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f
                )
                rotateAnimation.duration = 1000
                rotateAnimation.fillAfter = true

                image.startAnimation(rotateAnimation)
                currentDegree = -degree
            }
        }
    }

    fun lowPass(input: FloatArray, output: FloatArray) {
        val alpha = 0.05f

        for (i in input.indices) {
            output[i] = output[i] + alpha * (input[i] - output[i])
        }
    }

    override fun onResume() {
        sensorManager.registerListener(this, accelerometer, SENSOR_DELAY_GAME)
        sensorManager.registerListener(this, magnetometer, SENSOR_DELAY_GAME)
        super.onResume()
    }

    override fun onPause() {
        sensorManager.unregisterListener(this, accelerometer)
        sensorManager.unregisterListener(this, magnetometer)
        super.onPause()
    }

    override fun onDestroy() {
        lantern.cleanup()
        super.onDestroy()
    }


}