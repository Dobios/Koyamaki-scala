package koyamaki.geometry

import koyamaki.core.Data.{Intersection, Material}
import koyamaki.core.Ray
import koyamaki.utils.Vec3f
import koyamaki.utils.Solver

/**
 * Internal representation of a sphere primitive
 * @param center the center point of the sphere
 * @param radius the radius of the sphere
 * @param material the material of the given sphere
 */
class Sphere(val center: Vec3f, val radius: Float, val material: Material) extends Shape {

    /**
     * Computes the intersection between the given ray and the sphere
     * @param ray the ray with which we will be intersecting
     * @return the intersection data from the intersection between the ray and the sphere
     */
    override def intersect(ray: Ray): Option[Intersection] = {
        val oc: Vec3f = ray.origin - center

        //Three coefficients of the sphere's implicit equation: ||o + td - c|| - r = 0
        val a: Float = ray.direction dot ray.direction
        val b: Float = 2 * (ray.direction dot oc)
        val c: Float = (oc dot oc) - (radius * radius)

        //Solve the equation
        Solver solveQuadratic(a, b, c) match {
            case (Some(t), None) =>
                //Check that t isn't behind the camera
                if(t > 0) {
                    val point = ray(t)
                    Some(Intersection(point, (point - center) / radius, point - ray.origin, material, t))
                } else {
                    None
                }
            case (Some(t1), Some(t2)) =>
                //Check to find the closest intersection
                if(t1 < 0 && t2 < 0) {
                    None
                } else {
                    val t = Math.min(t1, t2)
                    val point = ray(t)
                    Some(Intersection(point, (point - center) / radius, point - ray.origin, material, t))
                }
            case _ => None
        }
    }
}

/**
 * Object used for some additional static constructors
 */
object Sphere {
    //Shorthand for the sphere's constructor
    def apply(center: Vec3f, radius: Float, material: Material): Sphere = new Sphere(center, radius, material)
}
