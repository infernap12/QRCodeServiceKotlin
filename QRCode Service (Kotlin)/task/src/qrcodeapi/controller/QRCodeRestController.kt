package qrcodeapi.controller

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import org.springframework.http.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import qrcodeapi.model.QrCode
import java.awt.image.*

val supportedTypes: List<MediaType> = listOf(
    MediaType.IMAGE_PNG,
    MediaType.IMAGE_GIF,
    MediaType.IMAGE_JPEG
)

@RestController
class QRCodeRestController {

    @GetMapping(path = ["/api/health"])
    fun health() = ResponseEntity("", HttpStatus.OK)

    @GetMapping(path = ["/api/qrcode"])
    fun getImage(
        @RequestParam(defaultValue = "250") size: Int,
        @RequestParam(defaultValue = "png") type: String,
        @RequestParam contents: String,
        @RequestParam(defaultValue = "L") correction: String
    ): ResponseEntity<Any> {
        return try {
            if (contents.isBlank()) throw Exception("Contents cannot be null or blank")
            if (size !in 150..350) throw Exception("Image size must be between 150 and 350 pixels")

            val correctionLevel: ErrorCorrectionLevel
            try {
                correctionLevel = ErrorCorrectionLevel.valueOf(correction.uppercase())
            } catch (e: Exception) {
                throw Exception("Permitted error correction levels are L, M, Q, H")
            }


            val mt: MediaType = MediaType.valueOf("image/${type.lowercase()}")
            if (!supportedTypes.contains(mt)) throw Exception("Only png, jpeg and gif image types are supported")

            val bufferedImage: BufferedImage = QrCode.qrCode(size, contents, correctionLevel)

            ResponseEntity
                .ok()
                .contentType(mt)
                .body(bufferedImage)

        } catch (e: Exception) {
            val msg = mapOf("error" to e.message)
            ResponseEntity
                .badRequest()
                .body(msg)
        }

        /*
        return ResponseEntity
            .ok()
            .contentType(MediaType.IMAGE_PNG)
            .body(bufferedImage).
            */
    }
}
