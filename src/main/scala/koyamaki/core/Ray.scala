package koyamaki.core

import koyamaki.utils.Vec3f

/**
 * Represents a ray in 3D space
 * @param origin the point at which the ray was generated
 * @param direction the direction in which the ray is going
 * @param shadowRay whether or not the ray is used to define a shadow
 */
class Ray(val origin: Vec3f, val direction: Vec3f, val shadowRay: Boolean) {
    /**
     * Computes the position along the ray given a t value
     * @param t the position along the ray
     * @return the position in 3D space defined by o + dt
     */
    def apply(t: Float): Vec3f = origin + direction * t
}

/**
 * Object defined for additional constructors
 */
object Ray {
    def apply(origin: Vec3f, direction: Vec3f, shadowRay: Boolean = false): Ray = new Ray(origin, direction, shadowRay)

    /**
     * Default constructor for the ray class
     * @return A basic ray shot from the origin along the x axis
     */
    def apply(): Ray = new Ray(Vec3f(0), Vec3f(1, 0, 0), false)
}
