package com.onxmaps.playground.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    enum class Operation(val resourceId: Int) {
        ADD(R.drawable.ic_add),
        SUBTRACT(R.drawable.ic_subtract),
        MULTIPLY(R.drawable.ic_multiply),
        DIVIDE(R.drawable.ic_divide),
        MOD(R.drawable.ic_mod)
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

        equalsButton.setOnClickListener { _ ->
            // TODO perform the selected calculation type
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
