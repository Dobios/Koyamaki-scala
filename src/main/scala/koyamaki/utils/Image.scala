package koyamaki.utils

import java.io.{BufferedWriter, DataOutputStream, File, FileOutputStream, FileWriter}

import koyamaki.Raytracer.Color

object Image {
    def write(width: Int, height: Int, pixels: List[List[Color]]): Unit = {
        //Sanity check
        if(pixels.tail.length != width || pixels.head.length != height) {
            throw new IllegalArgumentException(s"Pixels Array should match the given height and width!\nEXPECTED: width = $width, height = $height\nACTUAL: width = ${pixels.length}, height = ${pixels.head.length}")
        }
        val lines = pixels.tail.map(_.foldLeft("")((acc: String, v2: Color) => acc ++ v2.toRGBHex))

        //Write header output << "P6\n" << width << " " << height << "\n255\n";
        val out = new DataOutputStream(new FileOutputStream("raytrace.ppm"))

        out.writeBytes("P6\u000a%d %d\u000a%d\u000a".format(width, height, 255))

        //Write all pixel lines
        for(line <- lines) {
            out.writeBytes(line)
        }

        out.close()
    }
}
