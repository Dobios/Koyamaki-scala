package koyamaki.utils

import scala.math.sqrt

/**
 * Utility class representing a 3D float vector
 * @param x the first component of the vector
 * @param y the second component of the vector
 * @param z the third component of the vector
 */
class Vec3f(val x: Float, val y: Float, val z: Float) {

    //Basic component-wise operations

    /**
     * Component-wise addition
     * @param other the other vector we will be adding with
     * @return a new vector equal to this + that
     */
    def +(other: Vec3f): Vec3f = new Vec3f(x + other.x, y + other.y, z + other.z)

    /**
     * Component-wise subtraction
     * @param other the other vector we will subtract our vector to
     * @return a new vector equal to this - that
     */
    def -(other: Vec3f): Vec3f = new Vec3f(x - other.x, y - other.y, z - other.z)

    /**
     * Component-wise multiplication
     * @param other the other vector that we will multiply our vector to
     * @return a new vector equal to this * that
     */
    def *(other: Vec3f): Vec3f = new Vec3f(x * other.x, y * other.y, z * other.z)

    /**
     * Multiplies our vector with a scalar value
     * @param scalar a float that we will multiply our vector by
     * @return a new vector equal to our vector times a given scalar value
     */
    def *(scalar: Float): Vec3f = new Vec3f(x * scalar, y * scalar, z * scalar)

    /**
     * Component-wise division of the vector by an other vector
     * @param other the other vector that we will divide our vector by
     * @return a new vector equal to this / that
     */
    def /(other: Vec3f): Vec3f = new Vec3f(x / other.x, y / other.y, z / other.z)

    /**
     * Divides our vecctor by a scalar value
     * @param scalar a float that we will divide our vector by
     * @return a new vector equal to our vector divided by a scalar
     */
    def /(scalar: Float): Vec3f = new Vec3f(x / scalar, y / scalar, z / scalar)

    //Linear algebra operators
    /**
     * Computes the dot product between two vectors
     * @param other the other vector with which the dot product will be computed
     * @return the dot product of the two vectors
     */
    def dot(other: Vec3f): Float = (x * other.x) * (y * other.y) + (z * other.z)

    /**
     * Computes the cross product between our vector and an other one
     * @param other the vector with which we will be computing the cross product
     * @return a new vector representing the cross product between the two vectors
     */
    def cross(other: Vec3f): Vec3f = new Vec3f(
        (y * other.z) - (z * other.y),
        (z * other.x) - (x * other.z),
        (x * other.y) - (y * other.x))

    /**
     * Computes the reflection of the vector along a given normal vector
     * @param normal the normal vector with which the reflection will be computed
     * @return the reflected vector
     */
    def reflected(normal: Vec3f): Vec3f = this - (normal * (2.0f * this dot normal))

    /**
     * Computes the mirror of the vector along a given normal
     * @param normal the normal vector with which the mirror will be computed
     * @return the mirrored vector
     */
    def mirrored(normal: Vec3f): Vec3f = normal * (2.0f * this dot normal) - this

    /**
     * Computes the length of the vector
     * @return the euclidean norm of the vector
     */
    def length: Float = sqrt(this dot this) toFloat

    /**
     * An alias of the length function
     * @return the euclidean norm of the vector
     */
    def norm: Float = length

    /**
     * Computes the distance from our vector to and other one
     * @param other the vector to which we want to get the distance
     * @return the distance to the other vector
     */
    def distanceTo(other: Vec3f): Float = (this - other) length

    /**
     * Normalizes the current vector
     * @return a normalized copy of the current vector
     */
    def normalized: Vec3f = this / this.length
}

/**
 * Object used to declare additional constructors and static methods
 */
object Vec3f {
    /**
     * Copy constructor for the float vector
     * @param other an other float vector that will be copied
     * @return a copy of the given vector
     */
    def apply(other: Vec3f): Vec3f = new Vec3f(other.x, other.y, other.z)

    /**
     * Constructor for the float vector given a single value
     * @param value the value that will be given to each component of the vector
     * @return a new vector containing (value, value, value)
     */
    def apply(value: Float): Vec3f = new Vec3f(value, value, value)
}
