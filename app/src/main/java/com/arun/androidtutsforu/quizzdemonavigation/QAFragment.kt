package com.arun.androidtutsforu.quizzdemonavigation

import android.app.ActionBar
import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions


class QAFragment : Fragment() {
    data class QA(
        val question : String,
        val answers : List<String>,
       val crctAnswer : String
    )
    val questions: MutableList<QA> = mutableListOf(
        QA(question = "Android Based on which kernal?",
            answers = listOf("Windows", "Mac", "Linux"),
            crctAnswer ="Linux"),
        QA(question = "Which is not a Layout in Android?",
            answers = listOf("Linear Layout", "Frame layout", "Layer layout" ),
            crctAnswer="Layer layout"),
        QA(question = "Layout for complex Screens?",
            answers = listOf("Frame layout","LinearLayout", "ConstraintLayout"),
                    crctAnswer="ConstraintLayout"),
        QA(question = "Android Apps Can Not be written Using?",
            answers = listOf("Kotlin", "Php", "Java"),
            crctAnswer="Php"),
        QA(question = "Layout for Data Binding?",
            answers = listOf("<layout>", "<data>", "<binding>"),
            crctAnswer="<layout>"),
        QA(question = "which is not a state of an Activity?",
            answers = listOf("Pused", "Stored", "Stopped"),
            crctAnswer="Stored"),
        QA(question = "Build system for Android?",
            answers = listOf("Gradle", "Griddle", "bradle"),
            crctAnswer="Gradle"),
        QA(question = "Database in Android ?",
            answers = listOf("Room", "Ram", "Rom"),
            crctAnswer="Room"),
        QA(question = "Fragment is part of?",
            answers = listOf("Activity", "Time", "State"),
            crctAnswer="Activity"),
        QA(question = "Broadcast Receiver Declared in Manifest using?",
            answers = listOf("<broadcast>", "<receiver>", "<broacaster>"),
            crctAnswer="<receiver>")
    )


    var i: Int =0
   lateinit var tvQuestion : TextView
    lateinit var tvScore : TextView
    lateinit var tvTime : TextView
    lateinit var btnOption1 : Button
    lateinit var btnOption2:Button
    lateinit var btnOption3 :Button
    lateinit var btnCrct :Button
     var numQuestions : Int=1
     var numCrctAnswer : Int=0
    lateinit var couuntDown :CountDownTimer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var root: View =inflater.inflate(R.layout.qa_fragment,container,false)
        tvQuestion  = root.findViewById<TextView>(R.id.tv_qa)
        tvScore  = root.findViewById<TextView>(R.id.tv_qa_score)
        tvTime  = root.findViewById<TextView>(R.id.tv_time)
        btnOption1 = root.findViewById<Button>(R.id.btn_option1)
        btnOption2 = root.findViewById<Button>(R.id.btn_option2)
        btnOption3 = root.findViewById<Button>(R.id.btn_option3)
        btnCrct = root.findViewById<Button>(R.id.btn_crct)
        tvQuestion.text = questions[i].question
        btnOption1.text = questions[i].answers[0]
        btnOption2.text = questions[i].answers[1]
        btnOption3.text = questions[i].answers[2]
        btnOption1.setOnClickListener{
            answerClicked(it)
        }
        btnOption2.setOnClickListener{
            answerClicked(it)
        }
        btnOption3.setOnClickListener{
            answerClicked(it)
        }
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {




            if(((findNavController().currentDestination?.id)==R.id.qaFragment)){

               val builder = AlertDialog.Builder(context)
               builder.setMessage("do you want to exit ?")
                   .setPositiveButton("yes",
                       DialogInterface.OnClickListener { dialog, which ->
                           couuntDown.cancel()
                           findNavController().navigateUp()
                       })
                   .setNegativeButton("no", null)
               builder.show()

           }
            else{

               findNavController().navigateUp()
           }
        }



        startCountDown()
        return root



    }



    fun setnextQuestion() {

        btnOption1.setClickable(true)
        btnOption2.setClickable(true)
        btnOption3.setClickable(true)
        btnCrct.visibility=View.GONE
        btnOption1.setBackgroundColor(Color.parseColor("#FFFFFF"))
        btnOption2.setBackgroundColor(Color.parseColor("#FFFFFF"))
        btnOption3.setBackgroundColor(Color.parseColor("#FFFFFF"))
        tvQuestion.text = questions[i].question
        btnOption1.text = questions[i].answers[0]
        btnOption2.text = questions[i].answers[1]
        btnOption3.text = questions[i].answers[2]
        numQuestions++
        startCountDown()

    }
    fun answerClicked(view : View){
        var clickedBtn : Button = view as Button
        var ansSelected : String = clickedBtn.text.toString()
        if(ansSelected.equals(questions[i].crctAnswer)){

            btnCrct.visibility=View.VISIBLE
            clickedBtn.setBackgroundColor(Color.GREEN)
            btnCrct.setBackgroundColor(Color.GREEN)
            btnCrct.setText("Correct Answer")
            btnOption1.setClickable(false)
            btnOption2.setClickable(false)
            btnOption3.setClickable(false)
            numCrctAnswer++
            tvScore.setText("Score : ${numCrctAnswer*100}")


        }else{

            clickedBtn.setBackgroundColor(Color.RED)//#3700B3
            btnCrct.visibility=View.VISIBLE
            btnCrct.setBackgroundColor(Color.RED)
            btnCrct.setText("Wrong Answer")
            btnOption1.setClickable(false)
            btnOption2.setClickable(false)
            btnOption3.setClickable(false)

        }



    }
    fun startCountDown(){

        couuntDown  =  object : CountDownTimer(10000,1000){
               override fun onTick(millisUntilFinished: Long) {


               tvTime.text =""+millisUntilFinished/1000+"s"

           }

           override fun onFinish() {
               Log.d("time>>>>>>>","finished")
               if(i<questions.size-1){
                   i++

                   setnextQuestion()
               }else{

                   findNavController().navigate(QAFragmentDirections.actionQaFragmentToResultFragment(numQuestions,numCrctAnswer))
               }

           }
       }.start()

    }

}
