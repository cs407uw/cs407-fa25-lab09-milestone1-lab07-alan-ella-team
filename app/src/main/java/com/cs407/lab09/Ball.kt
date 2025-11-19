package com.cs407.lab09

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        // TODO: Call reset()
        reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }

        //Equation 1: v1 = v(t1) = v0 + 0.5 (a1 + a0)(t1 − t0)
        // equation 1 for x-axis
        val newVelcityX = velocityX + 0.5f * (xAcc + accX) * dT

        //equation 1 for y-axis
        val newVelcityY = velocityY + 0.5f * (yAcc + accY) * dT

        //Equation 2: l = Z t1 t0 v(τ ) dτ = v0 · (t1 − t0) + 1 6 · (t1 − t0)2 · (3a0 + a1)
        //equation 2 for x-axis
        val distanceX = velocityX * dT + (1.0f / 6.0f) * (dT * dT) * (3.0f * accX * xAcc)

        //equation 2 for y-axis
        val distanceY = velocityY * dT + (1.0f / 6.0f) * (dT * dT) * (3.0f * accY * yAcc)

        //update position
        posX += distanceX
        posY += distanceY

        //update velocity
        velocityX = newVelcityX
        velocityY = newVelcityY

        //update acceleration
        accX = xAcc
        accY = yAcc
    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        // TODO: implement the checkBoundaries function
        // (Check all 4 walls: left, right, top, bottom)

        //Left Wall
        if(posX < 0 ) {
            posX = 0f
            velocityX = 0f
            //accX = 0f
        }

        //Right Wall
        if (posX + ballSize > backgroundWidth) {
            posX = backgroundWidth - ballSize
            velocityX = 0f
            //accX = 0f
        }

        //Top Wall
        if(posY < 0) {
            posY = 0f
            velocityY = 0f
            //accY = 0f
        }

        //Bottom Wall
        if (posY + ballSize > backgroundHeight) {
            posY = backgroundHeight - ballSize
            velocityY = 0f
            //accY = 0f
        }
    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        // TODO: implement the reset function
        // (Reset posX, posY, velocityX, velocityY, accX, accY, isFirstUpdate)

        //center the ball
        posX = (backgroundWidth - ballSize) / 2.0f
        posY = (backgroundHeight - ballSize) / 2.0f

        //rests all motion
        velocityY = 0f
        velocityX = 0f
        accX = 0f
        accY = 0f

        //resest the first update flag
        isFirstUpdate = true
    }
}