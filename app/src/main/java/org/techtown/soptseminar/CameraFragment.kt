package org.techtown.soptseminar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.techtown.soptseminar.databinding.FragmentCameraBinding

class CameraFragment : Fragment() {

    class CameraFragment : Fragment() {
        private var _binding: FragmentCameraBinding? = null
        private val binding get() = _binding ?: error("바인딩에 NULL 값이 들어갔어요!!")
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = FragmentCameraBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }
}
