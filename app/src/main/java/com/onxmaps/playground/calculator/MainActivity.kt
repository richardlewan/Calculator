package com.onxmaps.playground.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import com.onxmaps.playground.calculator.util.Calculator
import com.onxmaps.playground.calculator.util.Calculator.add
import com.onxmaps.playground.calculator.util.Calculator.divide
import com.onxmaps.playground.calculator.util.Calculator.mod
import com.onxmaps.playground.calculator.util.Calculator.multiply
import com.onxmaps.playground.calculator.util.Calculator.subtract

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import timber.log.Timber
import kotlin.reflect.KFunction

class MainActivity : AppCompatActivity() {

    enum class Operation(val resourceId: Int, val mathFunction: KFunction<Float>) {
        ADD(R.drawable.ic_add, ::add),
        SUBTRACT(R.drawable.ic_subtract, ::subtract),
        MULTIPLY(R.drawable.ic_multiply, ::multiply),
        DIVIDE(R.drawable.ic_divide, ::divide),
        MOD(R.drawable.ic_mod, ::mod)
    }

    var selectedOperation = Operation.ADD

    /**
     * Instructions: Create a calculator that can perform 5 operations:
     * addition, subtraction, multiplication, division, and find the
     * remainder (modulus). The calculator should only be able to
     * work with 2 real numbers. You will need to display the operation
     * that is currently selected, as well as display a solution and
     * find a way to clear your result and start over. If you have
     * extra time, keep and display a history of all operations done.
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        equalsButton.setOnClickListener {
            val input1 = findViewById<EditText>(R.id.decimalInput1).text.toString().toFloat()
            val input2 = findViewById<EditText>(R.id.decimalInput2).text.toString().toFloat()
            val result = selectedOperation.mathFunction.call(Calculator, input1, input2).toString()

            Snackbar.make(
                findViewById<CoordinatorLayout>(R.id.coordinatorLayout),
                result,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // TODO: Handle action bar item clicks here. Change the
        //  symbol of the operator to match the operation
        //  that was selected. Pressing the "equals"
        //  button should also perform that operation

        when (item.itemId) {
            R.id.add -> selectedOperation = Operation.ADD
            R.id.subtract -> selectedOperation = Operation.SUBTRACT
            R.id.multiply -> selectedOperation = Operation.MULTIPLY
            R.id.divide -> selectedOperation = Operation.DIVIDE
            R.id.mod -> selectedOperation = Operation.MOD
        }
        swapImageToSelectedOperation()

        return super.onOptionsItemSelected(item)
    }

    fun swapImageToSelectedOperation() {
        val imageView = findViewById<ImageView>(R.id.operatorImage)
        imageView.setImageResource(selectedOperation.resourceId)
    }
}
