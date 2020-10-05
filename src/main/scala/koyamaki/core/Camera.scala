package koyamaki.core

import koyamaki.utils.Vec3f

class Camera(
        val eye: Vec3f,
        val center: Vec3f,
        val up: Vec3f,
        val fovy: Float,
        val width: Int,
        val height: Int
) {
    private var xDir: Vec3f = Vec3f()
    private var yDir: Vec3f = Vec3f()
    private var lowerLeft: Vec3f = Vec3f()

    /**
     * Initializes the internal components of the camera
     */
    def init(): Unit = {
        //Compute viewing direction and distance of eye to scene center
        val view: Vec3f = (center - eye).normalized
        val dist: Float = center.distanceTo(eye)

        // compute width & height of the image plane
        // based on the opening angle of the camera (fovy) and the distance
        // of the eye to the near plane (dist)
        val imageH: Float = (2.0f * dist) * Math.tan(0.5 * fovy / 180.0 * Math.PI).toFloat
        val imageW: Float = width / height * imageH

        // compute right and up vectors on the image plane
        xDir = (view cross up).normalized * imageW / width
        yDir = (xDir cross view).normalized * imageH / height

        // compute lower left corner on the image plane
        lowerLeft = center - Vec3f(0.5f * width) * xDir - Vec3f(0.5f * height) * yDir
    }

    /**
     * Create a ray for a pixel in the image
     * @param x pixel location in image
     * @param y pixel location in image
     * @return A primary ray
     */
    def primaryRay(x: Int, y: Int): Ray = Ray(eye, lowerLeft + Vec3f(x.toFloat) * xDir + Vec3f(y.toFloat) * yDir - eye)
}

object Camera {
    /**
     * Constructor for the Camera
     * @param eye Camera center
     * @param center Center of the scene, which the camera is looking at.
     * @param up Vector specifying the up direction.
     * @param fovy Opening angle (field of view) in y-direction
     * @param width Width of the image (in pixels)
     * @param height Height of the image (in pixels)
     * @return A Camera that's ready to be used
     */
    def apply(eye: Vec3f, center: Vec3f, up: Vec3f, fovy: Float, width: Int, height: Int): Camera = {
        val camera = new Camera(eye, center, up, fovy, width, height)
        camera.init()
        camera
    }
}