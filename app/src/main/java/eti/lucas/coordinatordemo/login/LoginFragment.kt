package eti.lucas.coordinatordemo.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import eti.lucas.coordinatordemo.databinding.FragmentLoginBinding
import eti.lucas.coordinatordemo.getViewModel
import eti.lucas.coordinatordemo.subscribe

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: LoginViewModel = getViewModel()

        with(binding) {
            btnNext.setOnClickListener {
                viewModel.login(
                    LoginStateMachine.LoginCredentials(
                        username = edtLogin.text.toString(),
                        password = edtPassword.text.toString()
                    )
                )
            }

            btnRegister.setOnClickListener { viewModel.signUp() }

            btnRecoverPassword.setOnClickListener { viewModel.forgotPassword() }
        }

        viewModel.state.subscribe(viewLifecycleOwner, this::render)
    }

    private fun render(state: LoginViewModel.LoginViewState) {
        when(state) {
            LoginViewModel.LoginViewState.LoadingState -> {
                Toast.makeText(activity, "loading", Toast.LENGTH_SHORT).show()
            }
            LoginViewModel.LoginViewState.ShowLoginForm -> {
                Toast.makeText(activity, "show login form", Toast.LENGTH_SHORT).show()
            }
            LoginViewModel.LoginViewState.ShowLoginFormWithErrorState -> {
                Toast.makeText(activity, "show login form with error", Toast.LENGTH_SHORT).show()
            }
        }

    }
}