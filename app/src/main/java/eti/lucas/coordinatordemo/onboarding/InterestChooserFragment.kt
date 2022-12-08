package eti.lucas.coordinatordemo.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import eti.lucas.coordinatordemo.databinding.FragmentInterestChooserBinding

@AndroidEntryPoint
class InterestChooserFragment : Fragment() {

    private var _binding: FragmentInterestChooserBinding? = null
    private val binding get() = _binding!!

    private val viewModel: InterestChooserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInterestChooserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnNext.setOnClickListener { viewModel.onNextClicked() }
        }
    }
}