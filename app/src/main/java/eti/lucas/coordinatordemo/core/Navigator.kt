package eti.lucas.coordinatordemo.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import eti.lucas.coordinatordemo.R

class Navigator {

    var activity: FragmentActivity? = null

    private fun replace(fragment: Fragment, addToBackStack: Boolean) {
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.appContainer, fragment)
            .apply { if (addToBackStack) addToBackStack(fragment::class.java.name) }
            .commit()
    }

    infix fun <F : Fragment> replaceWith(fragment: () -> F) = replace(fragment(), false)

    infix fun <F : Fragment> push(fragment: () -> F) = replace(fragment(), true)

    fun <F : FragmentWithParams> push(vararg params: Any, fragment: () -> F) = replace(fragment().newInstance(params), true)
}