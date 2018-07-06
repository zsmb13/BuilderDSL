package co.zsmb.builderdsl

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mikepenz.materialdrawer.DrawerBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawer {

        }
    }

}

fun Activity.drawer(dummy: () -> Unit) {
    DrawerBuilder()
            .withActivity(this)
            .build()
}
