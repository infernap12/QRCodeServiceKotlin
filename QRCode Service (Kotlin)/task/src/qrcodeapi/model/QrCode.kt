package qrcodeapi.model

import org.springframework.context.annotation.Bean
import org.springframework.http.converter.BufferedImageHttpMessageConverter
import org.springframework.http.converter.HttpMessageConverter
import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage


class QrCode {


    fun testSquare(): BufferedImage {
        val width = 250
        val height = 250
        val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        val g: Graphics2D = image.createGraphics()

        g.color = Color.WHITE
        g.fillRect(0, 0, width, height)

        return image
    }

    companion object {
        fun testSquare(): BufferedImage {
            return QrCode().testSquare()
        }
    }
}
