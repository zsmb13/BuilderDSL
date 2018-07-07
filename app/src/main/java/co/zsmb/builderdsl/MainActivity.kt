package co.zsmb.builderdsl

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawer {
            drawerLayout = R.layout.material_drawer_fits_not
            translucentStatusBar = false

            primaryItem(name = "Home", description = "Get started here!")
            primaryItem(name = "Settings", description = "Tinker around")

            onItemClick { position ->
                when (position) {
                    0 -> toast("Home clicked")
                    1 -> toast("Settings clicked")
                }
                true
            }
            onClosed {
                toast("Drawer closed")
            }
            onOpened {
                toast("Drawer opened")
            }
        }
    }
}

fun Activity.drawer(setup: DrawerBuilderKt.() -> Unit) {
    val builder = DrawerBuilderKt(this)
    builder.setup()
    builder.build()
}

class DrawerBuilderKt(activity: Activity) {

    val builder = DrawerBuilder().withActivity(activity)

    internal fun build() {
        builder.withOnDrawerListener(onDrawerListener)
        builder.build()
    }

    var drawerLayout: Int
        @Deprecated(message = "Non readable property.", level = DeprecationLevel.ERROR)
        get() = throw UnsupportedOperationException("")
        set(value) {
            builder.withDrawerLayout(value)
        }

    var translucentStatusBar: Boolean
        @Deprecated(message = "Non readable property.", level = DeprecationLevel.ERROR)
        get() = throw UnsupportedOperationException("")
        set(value) {
            builder.withTranslucentStatusBar(value)
        }

    fun onItemClick(handler: (position: Int) -> Boolean) {
        builder.withOnDrawerItemClickListener { _, position, _ -> handler(position) }
    }

    fun onOpened(handler: (drawerView: View) -> Unit) {
        onDrawerListener.onOpened = handler
    }

    fun onClosed(handler: (drawerView: View) -> Unit) {
        onDrawerListener.onClosed = handler
    }

    fun onSlide(handler: (drawerView: View, slideOffset: Float) -> Unit) {
        onDrawerListener.onSlide = handler
    }

    private val onDrawerListener = object : Drawer.OnDrawerListener {
        var onSlide: ((View, Float) -> Unit)? = null

        override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
            onSlide?.invoke(drawerView, slideOffset)
        }

        var onClosed: ((View) -> Unit)? = null

        override fun onDrawerClosed(drawerView: View) {
            onClosed?.invoke(drawerView)
        }

        var onOpened: ((View) -> Unit)? = null

        override fun onDrawerOpened(drawerView: View) {
            onOpened?.invoke(drawerView)
        }
    }

}

fun DrawerBuilderKt.primaryItem(name: String = "", description: String = "") {
    val item = PrimaryDrawerItem()
            .withName(name)
            .withDescription(description)
    builder.addDrawerItems(item)
}
