package dadm.ahararm.quotationshake.ui

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationBarView
import dadm.ahararm.quotationshake.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = binding.fcvNavHostFragment.getFragment<NavHostFragment>().navController
        val appBarConfig = AppBarConfiguration(navController.graph)
        (binding.bnvMain as NavigationBarView).setupWithNavController(navController)

        setSupportActionBar(binding.toolbar)

        binding.toolbar.setupWithNavController(navController, appBarConfig)

        val navigationBar = binding.bnvMain

        // Aplicar los insets a la barra de navegación
        ViewCompat.setOnApplyWindowInsetsListener(navigationBar) { view, insets ->
            val bars = insets.getInsets(
                WindowInsetsCompat.Type.displayCutout() or
                        WindowInsetsCompat.Type.systemBars()
            )
            view.updatePadding(
                left = bars.left,
                top = 0,
                right = 0,
                bottom = bars.bottom
            )
            WindowInsetsCompat.CONSUMED
        }

        val fragmentContainerView = binding.fcvNavHostFragment
        ViewCompat.setOnApplyWindowInsetsListener(fragmentContainerView) { view, insets ->
            val bars = insets.getInsets(
                WindowInsetsCompat.Type.displayCutout() or
                        WindowInsetsCompat.Type.systemBars()
            )
            view.updatePadding(
                left = 0,
                top = 0,
                right = bars.right,
                bottom = if (resources.configuration.orientation == ORIENTATION_LANDSCAPE) bars.bottom else 0
            )
            WindowInsetsCompat.CONSUMED
        }

        val appBarLayout = binding.ablMain

        ViewCompat.setOnApplyWindowInsetsListener(appBarLayout) { view, insets ->
            val bars = insets.getInsets(
                WindowInsetsCompat.Type.displayCutout() or
                        WindowInsetsCompat.Type.systemBars()
            )
            view.updatePadding(
                left = bars.left,
                top = bars.top,
                right = 0,
                bottom = 0
            )
            WindowInsetsCompat.CONSUMED
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
