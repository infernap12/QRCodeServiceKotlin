package qrcodeapi.model

import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import java.awt.image.BufferedImage


class QrCode {

    companion object {

        fun qrCode(size: Int, contents: String, correctionLevel: ErrorCorrectionLevel): BufferedImage {
            val writer = QRCodeWriter()
            val hints = mapOf(EncodeHintType.ERROR_CORRECTION to correctionLevel)
            val bitMatrix = writer.encode(contents, BarcodeFormat.QR_CODE, size, size, hints)

            return MatrixToImageWriter.toBufferedImage(bitMatrix)
        }

    }
}
