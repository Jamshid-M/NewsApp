package uz.jamshid.newsapp

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import uz.jamshid.newsapp.core.extension.putExtra
import uz.jamshid.newsapp.core.extension.read
import uz.jamshid.newsapp.core.extension.write
import uz.jamshid.newsapp.ui.fragment.NewsListFragment


class MainActivity : AppCompatActivity() {

    lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpTabs()
        setUpFragment(NewsListFragment())

        prefs = PreferenceManager.getDefaultSharedPreferences(this)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            R.id.language -> showDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select News Language")
        var index = 0

        val langList = arrayOf("English", "Chinese", "Russian", "Korean", "French", "Italian")
        val countryList = arrayOf("us", "cn", "ru", "kr", "fr", "it")
        builder.setSingleChoiceItems(langList, langList.indexOf(prefs.read("lang", "us"))) { dialog, which ->
            index = which
        }

        builder.setPositiveButton("OK") { dialog, which ->
            prefs.write("lang", countryList[index])
            setUpFragment(NewsListFragment())
        }
        builder.setNegativeButton("Cancel", null)

        val dialog = builder.create()
        dialog.show()
    }

    private fun setUpFragment(fragment: Fragment){

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}
