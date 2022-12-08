package eti.lucas.coordinatordemo.core

import android.os.Bundle
import androidx.fragment.app.Fragment

interface FragmentWithParams {
    fun newInstance(params: Bundle): Fragment
}