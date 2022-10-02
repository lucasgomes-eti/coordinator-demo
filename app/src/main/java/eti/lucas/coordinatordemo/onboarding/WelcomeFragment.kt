package eti.lucas.coordinatordemo.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eti.lucas.coordinatordemo.databinding.FragmentWelcomeBinding
import eti.lucas.coordinatordemo.getViewModel
import eti.lucas.coordinatordemo.subscribe

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: WelcomeViewModel = getViewModel()

        with(binding) {
            viewModel.username.subscribe(viewLifecycleOwner) {
                tvWelcome.text = "Welcome $it"
            }
            btnNext.setOnClickListener { viewModel.onNextClicked() }
        }
    }


}