package co.zsmb.builderdsl

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawer {
            drawerLayout = R.layout.material_drawer_fits_not
            translucentStatusBar = false

            primaryItem()
            primaryItem()
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

}

fun DrawerBuilderKt.primaryItem() {
    builder.addDrawerItems(PrimaryDrawerItem())
}
