package com.arun.androidtutsforu.quizzdemonavigation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI

/**
 * A simple [Fragment] subclass.
 */
class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root : View = inflater.inflate(R.layout.main_fragment,container,false);
      root.findViewById<Button>(R.id.btn_start).setOnClickListener{
           // findNavController().navigate(R.id.action_welcomeFragment_to_qaFragment);
          it.findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToQaFragment())


        }
        setHasOptionsMenu(true);
        return root;

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,findNavController())
                ||return super.onOptionsItemSelected(item)
    }

}
