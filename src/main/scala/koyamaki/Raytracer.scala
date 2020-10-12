package koyamaki

import koyamaki.core.Data.{Intersection, Light, Material}
import koyamaki.core.{Camera, Ray, Shader}
import koyamaki.geometry.Sphere
import koyamaki.utils.{Image, UI, Vec3f}

import scala.annotation.tailrec

object Raytracer {
    type Color = Vec3f

    /**
     * Main raytracing loop
     * @param args an xml input file containing the description of the scene
     */
    def main(args: Array[String]): Unit = {
        val width: Int = 480
        val height: Int = 480

        //Initialize UI
        /*val ui = new UI(width, height)
        ui.visible = true*/

        //Create the camera
        val eye: Vec3f = Vec3f(0, 1, 0)
        val camera: Camera = Camera(eye, Vec3f(0, 1, 1), Vec3f(30, 4, 4), 90.0f, width, height)

        //Define geometry & lights
        val sphere: Sphere = Sphere(Vec3f(5, 1, 0), 2.0f, Material(Vec3f(1.0f, 0, 0), Vec3f(1.0f, 0, 0), Vec3f(1.0f, 0, 0), 1.0f))
        val light: Light = Light(Vec3f(-1, 2, 0), Vec3f(1))
        val ambientLight : Vec3f = Vec3f(0.2f)

        println("Rendering...")

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
                        val sphereInt: Option[Intersection] = sphere intersect ray

                        //Shade intersection point
                        val color: Color = Shader.shadePoint(sphereInt, light, ambientLight)

                        //Update the current color accumulator
                        innerLoop(y + 1, colorCol :+ color)
                    }
                }

                //Run the inner loop
                raytracingLoop(x + 1, colorLines :+ innerLoop(0, Nil))
            }
        }

        println("Finished!")

        //Compute the final image's color
        val colors: List[List[Color]] = raytracingLoop(-1, Nil)

        //Write the image to a file
        Image.write(width, height, colors)
    }
}

