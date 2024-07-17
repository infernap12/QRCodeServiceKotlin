package qrcodeapi.model

import org.springframework.context.annotation.Bean
import org.springframework.http.converter.BufferedImageHttpMessageConverter
import org.springframework.http.converter.HttpMessageConverter
import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage


class QrCode {


    fun testSquare(size: Int): BufferedImage {
        if (size !in 150..350) throw Exception("Image size must be between 150 and 350 pixels")

        val image = BufferedImage(size, size, BufferedImage.TYPE_INT_RGB)
        val g: Graphics2D = image.createGraphics()

        g.color = Color.WHITE
        g.fillRect(0, 0, size, size)

        return image
    }

    companion object {
        fun testSquare(size: Int = 250): BufferedImage {
            return QrCode().testSquare(size)
        }
    }
}
