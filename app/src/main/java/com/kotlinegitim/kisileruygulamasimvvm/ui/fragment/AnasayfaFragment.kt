package com.kotlinegitim.kisileruygulamasimvvm.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import com.kotlinegitim.kisileruygulamasimvvm.R
import com.kotlinegitim.kisileruygulamasimvvm.databinding.FragmentAnasayfaBinding

class AnasayfaFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var tasarim: FragmentAnasayfaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tasarim = FragmentAnasayfaBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        tasarim.toolbarAnasayfa.title = "Kişiler"
        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarAnasayfa)

        tasarim.fab.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.KisiKayitGecis)
        }

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu, menu)

                val item = menu.findItem(R.id.action_ara)
                val searchView = item.actionView as SearchView

                searchView.setOnQueryTextListener(this@AnasayfaFragment)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        },viewLifecycleOwner,Lifecycle.State.RESUMED)

        return tasarim.root
    }

    //Arama tuşuna bastığımızda oluşan metod
    override fun onQueryTextSubmit(query: String): Boolean {
        ara(query)
        return true
    }

    //her karakter girdiğimizde çalışan metod
    override fun onQueryTextChange(newText: String): Boolean {
        ara(newText)
        return true
    }

    fun ara(aramaKelimesi: String){
        Log.e("Kişi Ara",aramaKelimesi)
    }
}