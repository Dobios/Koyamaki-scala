package koyamaki.utils

object Solver {

    /**
     * Solves the given quadratic equation of the form:
     * a(x * x) + bx + c = 0
     * @param a the quadratic coefficient
     * @param b the linear coefficient
     * @param c the constant
     * @return two optional solutions for the equation
     */
    def solveQuadratic(a: Float, b: Float, c: Float): (Option[Float], Option[Float]) = {
        val delta = (b * b) - (4 * a * c)

        if(delta < 0) {
            (None, None)
        } else if (delta == 0) {
            (Some(-b / (2 * a)), None)
        } else {
            val deltaSqrt = Math.sqrt(delta)
            (Some(((-b + deltaSqrt) / (2 * a)).toFloat), Some(((-b - deltaSqrt) / (2 * a)).toFloat))
        }

    }
}
