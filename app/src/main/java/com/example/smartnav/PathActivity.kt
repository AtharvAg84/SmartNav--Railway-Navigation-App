package com.example.smartnav

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast // Make sure this import is included
import androidx.appcompat.app.AppCompatActivity

class PathActivity : AppCompatActivity() {

    private lateinit var imageViewOriginal: ImageView
    private lateinit var imageViewBinary: ImageView
    private lateinit var startX: EditText
    private lateinit var startY: EditText
    private lateinit var endX: EditText
    private lateinit var endY: EditText
    private lateinit var btnFindPath: Button
    private lateinit var originalBitmap: Bitmap
    private lateinit var binaryBitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_path)

        imageViewOriginal = findViewById(R.id.imageViewOriginal)
        imageViewBinary = findViewById(R.id.imageViewBinary)
        startX = findViewById(R.id.startX)
        startY = findViewById(R.id.startY)
        endX = findViewById(R.id.endX)
        endY = findViewById(R.id.endY)
        btnFindPath = findViewById(R.id.btnFindPath)

        originalBitmap = BitmapFactory.decodeResource(resources, R.drawable.map1agra)
        binaryBitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true)

        imageViewOriginal.setImageBitmap(originalBitmap)
        imageViewBinary.setImageBitmap(binaryBitmap)

        btnFindPath.setOnClickListener {
            findPath()
        }
    }

    private fun findPath() {
        val startX = startX.text.toString().toIntOrNull() ?: run {
            Toast.makeText(this, "Invalid Start X", Toast.LENGTH_SHORT).show()
            return
        }
        val startY = startY.text.toString().toIntOrNull() ?: run {
            Toast.makeText(this, "Invalid Start Y", Toast.LENGTH_SHORT).show()
            return
        }
        val endX = endX.text.toString().toIntOrNull() ?: run {
            Toast.makeText(this, "Invalid End X", Toast.LENGTH_SHORT).show()
            return
        }
        val endY = endY.text.toString().toIntOrNull() ?: run {
            Toast.makeText(this, "Invalid End Y", Toast.LENGTH_SHORT).show()
            return
        }

        val binaryGrid = imageToBinaryGrid(binaryBitmap)
        val path = aStarPathfinding(binaryGrid, startX, startY, endX, endY)

        if (path.isNotEmpty()) {
            drawPathOnBitmap(binaryBitmap, path)
            imageViewBinary.setImageBitmap(binaryBitmap) // Update ImageView with new bitmap
            imageViewBinary.visibility = ImageView.VISIBLE // Show the updated image
        } else {
            Toast.makeText(this, "No path found", Toast.LENGTH_SHORT).show()
        }
    }


    private fun imageToBinaryGrid(bitmap: Bitmap): Array<Array<Int>> {
        val width = bitmap.width
        val height = bitmap.height
        val grid = Array(height) { Array(width) { 0 } }

        for (y in 0 until height) {
            for (x in 0 until width) {
                val pixel = bitmap.getPixel(x, y)
                grid[y][x] = if (pixel == Color.BLACK) 1 else 0
            }
        }

        return grid
    }


    private fun aStarPathfinding(grid: Array<Array<Int>>, startX: Int, startY: Int, endX: Int, endY: Int): List<Pair<Int, Int>> {
        // Simple placeholder pathfinding: straight line between start and end
        // You should replace this with a proper A* implementation
        return listOf(Pair(startX, startY), Pair(endX, endY))
    }


    private fun drawPathOnBitmap(bitmap: Bitmap, path: List<Pair<Int, Int>>) {
        val paint = Paint().apply {
            color = Color.RED
            strokeWidth = 5f
            isAntiAlias = true
        }
        val canvas = Canvas(bitmap)
        for (i in 0 until path.size - 1) {
            val (startX, startY) = path[i]
            val (endX, endY) = path[i + 1]
            canvas.drawLine(startX.toFloat(), startY.toFloat(), endX.toFloat(), endY.toFloat(), paint)
        }
    }

}
