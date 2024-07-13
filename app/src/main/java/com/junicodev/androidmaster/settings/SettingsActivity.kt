package com.junicodev.androidmaster.settings

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.junicodev.androidmaster.databinding.ActivitySettingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsActivity : AppCompatActivity() {

    companion object {
        const val VOLUME = "volume"
        const val BLUETOOTH = "bluetooth"
        const val VIBRATION = "vibration"
        const val DARK_MODE = "dark_mode"
    }

    private lateinit var binding: ActivitySettingsBinding
    private var firstTime: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        CoroutineScope(Dispatchers.IO).launch {
            getSettings().filter { firstTime }.collect {
                if (it != null) {
                    runOnUiThread {
                        binding.swVibration.isChecked = it.vibration
                        binding.swBluetooth.isChecked = it.bluetooth
                        binding.swDarkMode.isChecked = it.darkMode
                        binding.rgVolume.setValues(it.volume.toFloat())
                        firstTime != firstTime
                    }
                }
            }
        }
        initUI()
    }

    private fun initUI() {
        binding.rgVolume.addOnChangeListener { _, value, _ ->
            CoroutineScope(Dispatchers.IO).launch {
                saveVolume(value.toInt())
            }
        }

        binding.swBluetooth.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(BLUETOOTH, value)
            }
        }

        binding.swDarkMode.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(DARK_MODE, value)
            }
        }

        binding.swVibration.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(VIBRATION, value)
            }
        }
    }

    private suspend fun saveVolume(value: Int) {
        dataStore.edit {
            it[intPreferencesKey(VOLUME)] = value
        }
    }

    private suspend fun saveOptions(key: String, value: Boolean) {
        dataStore.edit {
            it[booleanPreferencesKey(key)] = value
        }
    }

    private fun getSettings(): Flow<SettingsModel?> {
        return dataStore.data.map {
            SettingsModel(
                volume = it[intPreferencesKey(VOLUME)] ?: 50,
                bluetooth = it[booleanPreferencesKey(BLUETOOTH)] ?: false,
                vibration = it[booleanPreferencesKey(VIBRATION)] ?: true,
                darkMode = it[booleanPreferencesKey(DARK_MODE)] ?: false
            )
        }
    }
}