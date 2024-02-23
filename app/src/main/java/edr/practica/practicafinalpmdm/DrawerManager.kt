package edr.practica.practicafinalpmdm

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView


class DrawerManager(
    private val activity: MainActivity,
): AppCompatActivity() {
    private val drawerLayout: DrawerLayout = activity.findViewById(R.id.drawer_layout)
    private val navigationView: NavigationView = activity.findViewById(R.id.navigation_view)

    init {
        setupDrawer()
    }

    private fun setupDrawer() {
        val toggle = ActionBarDrawerToggle(
            activity,
            drawerLayout,
            activity.findViewById(R.id.toolbar),// opcional
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener {
            menuItem -> handleNavigationItem(menuItem.itemId)
        }


    }


    private fun handleNavigationItem(itemId: Int): Boolean {
        when (itemId) {
            R.id.itemAyuda -> {
                replaceFragment(AyudaFragment())
            }
            R.id.itemConsultar -> {
                replaceFragment(ConsultarReservaFragment())
            }
            R.id.itemReserva -> {
                replaceFragment(ReservaFragment())
            }
            else -> return false
        }
        // Actualiza la lista de cartas seleccionadas y resetea el juego
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    private fun replaceFragment(fragment: Fragment) {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .addToBackStack("replacement")
            .commit()
    }

}
