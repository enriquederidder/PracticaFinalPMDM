package edr.practica.practicafinalpmdm

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import edr.practica.practicafinalpmdm.databinding.ActivityMainBinding
import edr.practica.practicafinalpmdm.models.RecogidaDatosViewModel
import edr.practica.practicafinalpmdm.models.ReservaViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerManager: DrawerManager
    private val recogidaDatosFragment: RecogidaDatosViewModel by viewModels()
    private val reservaDatosFragment: ReservaViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        drawerManager = DrawerManager(this)

        this.recogidaDatosFragment.initialize(this)
        this.reservaDatosFragment.initialize(this)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_open_drawer) {
            onBackPressedDispatcher.onBackPressed()
        }
        when (item.itemId) {
            R.id.irAyuda -> {
                replaceFragment(AyudaFragment())
                return true
            }

            R.id.irConsultarViaje -> {
                replaceFragment(ConsultarReservaFragment())
                return true
            }

            R.id.irReservar -> {
                replaceFragment(ReservaFragment())
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_items, menu)
        return true
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .addToBackStack("replacement")
            .commit()
    }
}