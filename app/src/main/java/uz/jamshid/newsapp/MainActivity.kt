package uz.jamshid.newsapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import uz.jamshid.newsapp.core.extension.putExtra
import uz.jamshid.newsapp.ui.fragment.NewsListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpTabs()
        setUpFragment(NewsListFragment())

    }

    private fun setUpTabs(){
        tabs.addTab(tabs.newTab().setText(getString(R.string.technology)))
        tabs.addTab(tabs.newTab().setText(getString(R.string.sport)))
        tabs.addTab(tabs.newTab().setText(getString(R.string.business)))
        tabs.addTab(tabs.newTab().setText(getString(R.string.politics)))

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                setUpFragment(NewsListFragment().putExtra("key", p0?.text.toString()))
            }

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        val manager = supportFragmentManager
        val trans = manager.beginTransaction()
        val fr = manager.findFragmentByTag("INFO_FRAGMENT")
        if(fr != null) {
            trans.remove(fr)
            trans.commit()
            manager.popBackStack()
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(false)
        }else
            super.onBackPressed()
    }

    private fun setUpFragment(fragment: Fragment){

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}
