package eti.lucas.coordinatordemo.core

import androidx.fragment.app.Fragment

interface FragmentWithParams {
    fun newInstance(vararg params: Any): Fragment
}