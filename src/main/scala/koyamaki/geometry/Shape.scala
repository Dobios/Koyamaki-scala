package koyamaki.geometry

import koyamaki.core.Data.Intersection
import koyamaki.core.Ray

abstract class Shape {
    def intersect(ray: Ray): Option[Intersection]
}
