package com.hoboss.findme

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.floor
import kotlin.random.Random

class SpotLightImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var paint = Paint()
    private var shouldDrawSpotLight = false
    private var gameOver = false

    private lateinit var winnerRect: RectF
    private var androidBitmapX = 0f
    private var androidBitmapY = 0f

    private val bitmapAndroid = BitmapFactory.decodeResource(resources, R.drawable.android)
    private val spotlight = BitmapFactory.decodeResource(resources, R.drawable.mask)

    private var shader: Shader

    private val shaderMatrix = Matrix()

    init {
        // The third argument is the bitmap color configuration. Bitmap.Config.ARGB_8888 stores
        // each color in 4 bytes, offers the best quality, and is recommended.
        val bitmap = Bitmap.createBitmap(
            spotlight.width, spotlight.height, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        val shaderPaint = Paint(Paint.ANTI_ALIAS_FLAG)

        // Draw a black rectangle.
        shaderPaint.color = Color.BLACK
        canvas.drawRect(
            0.0f, 0.0f, spotlight.width.toFloat(), spotlight.height.toFloat(), shaderPaint
        )

        // Use the DST_OUT compositing mode to mask out the spotlight from the black rectangle.
        shaderPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        canvas.drawBitmap(spotlight, 0.0f, 0.0f, shaderPaint)

        shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.shader = shader
    }

    private fun setupWinnerRect() {
        androidBitmapX = floor(Random.nextFloat() * (width - bitmapAndroid.width))
        androidBitmapY = floor(Random.nextFloat() * (height - bitmapAndroid.height))

        winnerRect = RectF(
            (androidBitmapX),
            (androidBitmapY),
            (androidBitmapX + bitmapAndroid.width),
            (androidBitmapY + bitmapAndroid.height)
        )
    }

    override fun onSizeChanged(
        newWidth: Int,
        newHeight: Int,
        oldWidth: Int,
        oldHeight: Int
    ) {
        super.onSizeChanged(newWidth, newHeight, oldWidth, oldHeight)
        setupWinnerRect()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawColor(Color.WHITE)
        canvas?.drawBitmap(bitmapAndroid, androidBitmapX, androidBitmapY, paint)

        if (!gameOver) {
            if (shouldDrawSpotLight) {
                canvas?.drawRect(0.0f, 0.0f, width.toFloat(), height.toFloat(), paint)
            } else {
                canvas?.drawColor(Color.BLACK)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //return super.onTouchEvent(event)
        event?.let {
            val motionEventX = event.x
            val motionEventY = event.y

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    shouldDrawSpotLight = true
                    if (gameOver) {
                        gameOver = false
                        setupWinnerRect()
                    }
                }
                MotionEvent.ACTION_UP -> {
                    shouldDrawSpotLight = false
                    gameOver = winnerRect.contains(motionEventX, motionEventY)
                }
            }

            shaderMatrix.setTranslate(
                motionEventX - spotlight.width / 2.0f,
                motionEventY - spotlight.height / 2.0f
            )
            shader.setLocalMatrix(shaderMatrix)
            invalidate()
        }
        return true
    }
}