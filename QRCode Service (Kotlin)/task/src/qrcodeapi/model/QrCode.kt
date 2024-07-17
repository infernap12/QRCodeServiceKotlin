package qrcodeapi.model

import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage


class QrCode {


    fun testSquare(size: Int): BufferedImage {


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

        fun qrCode(size: Int = 250, contents: String = "TEST"): BufferedImage {
            val writer = QRCodeWriter()
            val bitMatrix = writer.encode(contents, BarcodeFormat.QR_CODE, size, size)

            return MatrixToImageWriter.toBufferedImage(bitMatrix)
        }

    }
}
