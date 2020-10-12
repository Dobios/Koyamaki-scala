package koyamaki.core

import koyamaki.utils.Vec3f

object Data {

    /**
     * Internal representation of an ray-shape intersection
     * @param point the point at which the intersection happened
     * @param normal the normal vector of the intersection
     * @param dirToEye the normalized direction to the eye from the intersection point
     * @param material the material at the intersection point
     * @param intersection_t at which point along the ray the intersection happen
     */
    case class Intersection(point: Vec3f,
                               normal: Vec3f,
                               dirToEye: Vec3f,
                               material: Material,
                               intersection_t: Float)

    /**
     * Internal representation of an object's material
     * @param ambient the ambient RGB color vector of the object
     * @param diffuse the diffuse RGB color vector of the object
     * @param specular the specular RGB color vector of the object
     * @param shininess the shininess exponent of the object
     */
    case class Material(ambient: Vec3f,
                           diffuse: Vec3f,
                           specular: Vec3f,
                           shininess: Float)

    /**
     * Internal representation of a point-light source
     * @param position the position of the point light
     * @param intensity RGB color vector defining the intensity of the light source
     */
    case class Light(position: Vec3f, intensity: Vec3f)
}
