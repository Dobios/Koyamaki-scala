package koyamaki

import koyamaki.core.{Camera, Ray}
import koyamaki.utils.Vec3f

import scala.annotation.tailrec

object Raytracer {
    type Color = Int

    /**
     * Main raytracing loop
     * @param args an xml input file containing the description of the scene
     */
    def main(args: Array[String]): Unit = {
        val width: Int = 480
        val height: Int = 480

        //Create the camera
        val eye: Vec3f = Vec3f(0, 1, 0)
        val camera: Camera = Camera(eye, Vec3f(0, 1, 1), Vec3f(30, 4, 4), 90.0f, width, height)

        //Raytracing loop
        @tailrec
        def raytracingLoop(x: Int, colorLines: List[List[Color]]): List[List[Color]] = {
            if(x == width) colorLines
            else {
                //Inner loop to generate the columns
                @tailrec
                def innerLoop(y: Int, colorCol: List[Color]): List[Color] = {
                    if(y == height) colorCol
                    else {
                        //Generate primary ray
                        val ray: Ray = camera.primaryRay(x, y)

                        //Check for potential intersections
                        //TODO: Check for intersections with objects in the scene

                        //Shade intersection point
                        val color: Color = 0 //TODO: Shade the intersection point

                        //Update the current color accumulator
                        innerLoop(y + 1, colorCol :+ color)
                    }
                }

                //Run the inner loop
                raytracingLoop(x + 1, colorLines :+ innerLoop(0, Nil))
            }
        }

        //Compute the final image's color
        val colors: List[List[Color]] = raytracingLoop(-1, Nil)

        //TODO: Output colors to an image
    }
}

