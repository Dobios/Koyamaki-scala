package koyamaki.core

import koyamaki.Raytracer.Color
import koyamaki.core.Data.{Intersection, Light}
import koyamaki.utils.Vec3f

object Shader {
    def shadePoint(intData: Option[Intersection], light: Light, ambient: Color): Color = intData match {
        case None => Vec3f(0.1f)
        case Some(intersection) => {
            //Compute the direction to the light
            val l : Vec3f = (light.position - intersection.point).normalized
            val n : Vec3f = intersection.normal.normalized

            //Used for specular reflection
            val v : Vec3f = intersection.dirToEye.normalized
            val r : Vec3f = (Vec3f(2 * (n dot l)) * (n - l)).normalized

            //Compute the ambient color of the point
            val ambientColor : Color = intersection.material.ambient * ambient

            //Compute the diffuse and specular colors of the point
            val cosTheta : Float = Math.max(n dot l, 0.0f)
            val cosAlpha : Float = Math.max(Math.pow(r dot v, intersection.material.shininess).toFloat, 0.0f)

            val diffuseColor : Color = light.intensity * intersection.material.diffuse * cosTheta
            val specularColor : Color = light.intensity * intersection.material.specular * cosAlpha

            //Compute the final color
            Vec3f min(ambientColor + (diffuseColor + specularColor), Vec3f(1.0f))
        }
    }
}
