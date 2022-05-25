package org.d3if0064.assessment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val fragHome : Fragment = HomeFragment()
    val fragData : Fragment = DataFragment()
    val fragCopyright : Fragment = CopyrightFragment()
    val fm : FragmentManager = supportFragmentManager
    var active : Fragment = fragHome

    private lateinit var menu : Menu
    private lateinit var menuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpNaviBottom()
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