package org.d3if0064.assessment2

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*
import org.d3if0064.assessment2.fragment.CopyrightFragment
import org.d3if0064.assessment2.fragment.DataFragment
import org.d3if0064.assessment2.fragment.HomeFragment

class MainActivity : AppCompatActivity() {
    val fragHome : Fragment = HomeFragment()
    val fragData : Fragment = DataFragment()
    val fragCopyright : Fragment = CopyrightFragment()
    val fm : FragmentManager = supportFragmentManager
    var active : Fragment = fragHome

    private lateinit var menu : Menu
    private lateinit var menuItem: MenuItem

    companion object {
        const val CHANNEL_ID = "updater"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpNaviBottom()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = getString(R.string.channel_desc)
            val manager = getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private fun setUpNaviBottom(){
        fm.beginTransaction().add(R.id.navi_content,fragHome).show(fragHome).commit()
        fm.beginTransaction().add(R.id.navi_content,fragData).show(fragData).commit()
        fm.beginTransaction().add(R.id.navi_content,fragCopyright).show(fragCopyright).commit()

        menu = btn_navi_view.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        btn_navi_view.setOnNavigationItemSelectedListener {
            item ->
            when (item.itemId){
                R.id.navi_home -> {
                    callfrag(0, fragHome)
                }
                R.id.navi_Data -> {
                    callfrag(1, fragData)
                }
                R.id.navi_copyright -> {
                    callfrag(2, fragCopyright)
                }
            }
            false
        }
    }

    private fun callfrag(i:Int, fragment: Fragment) {
        menuItem = menu.getItem(i)
        menuItem.isChecked = true

        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }


}