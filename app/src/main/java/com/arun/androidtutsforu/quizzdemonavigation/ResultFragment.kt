package com.arun.androidtutsforu.quizzdemonavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavArgs
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

/**
 * A simple [Fragment] subclass.
 */
class ResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root : View = inflater.inflate(R.layout.result_fragment,container,false);
        root.findViewById<Button>(R.id.btn_finish).setOnClickListener{
            it.findNavController().navigate(ResultFragmentDirections.actionResultFragmentToQaFragment())
        }
      val args = arguments?.let { ResultFragmentArgs.fromBundle(it) }
        val tvScore = root.findViewById<TextView>(R.id.tv_score)
        tvScore.setText("Number Of Question is : ${args?.numQuestions}  correct answer: ${args?.numCrctAnswer} your score ${args?.numCrctAnswer?.times(
            100
        )}")


        return root;
    }

}
