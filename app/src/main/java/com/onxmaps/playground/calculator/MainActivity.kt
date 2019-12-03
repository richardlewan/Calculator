package com.onxmaps.playground.calculator

import android.content.Context
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.inputmethod.InputMethodManager

class MainActivity : AppCompatActivity() {

    enum class Operation(val resourceId: Int, val mathFunction: KFunction<Float>, val operatorString: String) {
        ADD(R.drawable.ic_add, ::add, "+"),
        SUBTRACT(R.drawable.ic_subtract, ::subtract, "-"),
        MULTIPLY(R.drawable.ic_multiply, ::multiply, "*"),
        DIVIDE(R.drawable.ic_divide, ::divide, "/"),
        MOD(R.drawable.ic_mod, ::mod, "%")
    }

    var selectedOperation = Operation.ADD

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    val historyList = ArrayList<String>()

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
        focusFirstInput()

        equalsButton.setOnClickListener {
            val textInput1 = findViewById<EditText>(R.id.decimalInput1).text
            val textInput2 = findViewById<EditText>(R.id.decimalInput2).text
            if (textInput1 != null && textInput2 != null && textInput1.isNotBlank() && textInput2.isNotBlank()) {
                val input1 = textInput1.toString().toFloat()
                val input2 = textInput2.toString().toFloat()
                val result =
                    selectedOperation.mathFunction.call(Calculator, input1, input2).toString()
                val fullCalculation =
                    "$input1 ${selectedOperation.operatorString} $input2 = $result"

                // Show the user result
                Snackbar.make(
                    findViewById<CoordinatorLayout>(R.id.coordinatorLayout),
                    fullCalculation,
                    Snackbar.LENGTH_LONG
                ).show()

                // Add to the history
                val indexLastItemAfterAdding = historyList.size
                historyList.add(fullCalculation)
                viewAdapter.notifyItemChanged(indexLastItemAfterAdding)
            }
            hideSoftKeyboard()
        }

        viewManager = LinearLayoutManager(this)
        viewAdapter = HistoryAdapter(historyList)

        recyclerView = findViewById<RecyclerView>(R.id.historyView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
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

    private fun swapImageToSelectedOperation() {
        val imageView = findViewById<ImageView>(R.id.operatorImage)
        imageView.setImageResource(selectedOperation.resourceId)
    }

    private fun focusFirstInput() {
        findViewById<EditText>(R.id.decimalInput1).requestFocus()
    }

    private fun hideSoftKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus!!.getWindowToken(), 0)
    }
}
