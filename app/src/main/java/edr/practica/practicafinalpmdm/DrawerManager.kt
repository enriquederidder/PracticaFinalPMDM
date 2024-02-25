package edr.practica.practicafinalpmdm

import android.Manifest
import android.content.pm.PackageManager
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class DrawerManager(
    private val activity: AppCompatActivity
) {
    private val drawerLayout: DrawerLayout = activity.findViewById(R.id.drawer_layout)
    private val navigationView: NavigationView = activity.findViewById(R.id.navigation_view)
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var requestCamera: ActivityResultLauncher<Void?>
    private lateinit var imageViewPerfil: ImageView
    private val REQUEST_IMAGE_CAPTURE = 1001

    init {
        requestConfigs()
        setupDrawer()
    }

    private fun setupDrawer() {
        imageViewPerfil = navigationView.getHeaderView(0).findViewById(R.id.imageViewPerfil)

        val toggle = ActionBarDrawerToggle(
            activity,
            drawerLayout,
            activity.findViewById(R.id.toolbar),// opcional
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener { menuItem ->
            handleNavigationItem(menuItem.itemId)
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
            R.id.crearCliente -> {
                replaceFragment(RecogidaDatosFragment())
            }
            R.id.cambiarPerfilImagen -> {
                if (ContextCompat.checkSelfPermission(
                        activity,
                        android.Manifest.permission.CAMERA
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        activity,
                        arrayOf(android.Manifest.permission.CAMERA),
                        REQUEST_IMAGE_CAPTURE
                    )
                } else {
                    openCamera()
                }
                return true
            }
            else -> return false
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun replaceFragment(fragment: Fragment) {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .addToBackStack("replacement")
            .commit()
    }

    private fun requestConfigs() {
        requestPermissionLauncher =
            activity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { }

        requestCamera = activity.registerForActivityResult(
            ActivityResultContracts.TakePicturePreview()
        ) {
            it?.let { bitmap ->
                imageViewPerfil.setImageBitmap(bitmap)
            }
        }
    }

    private fun openCamera() {
        if (ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            requestCamera.launch(null)
        } else {
            requestPermissionLauncher.launch(arrayOf(Manifest.permission.CAMERA))
        }
    }

}
