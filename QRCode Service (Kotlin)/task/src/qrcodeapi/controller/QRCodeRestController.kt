package qrcodeapi.controller

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

    @GetMapping(path = ["/"])
    fun hello() = ResponseEntity("Hello World", HttpStatus.OK);

    @GetMapping(path = ["/api/health"])
    fun health() = ResponseEntity("", HttpStatus.OK)

    @GetMapping(path = ["/api/qrcode"])
    fun getImage(@RequestParam size: Int, @RequestParam type: String): ResponseEntity<Any> {
        return try {
            val bufferedImage: BufferedImage = QrCode.testSquare(size)
            val mt: MediaType = MediaType.valueOf("image/${type.lowercase()}")
            if (!supportedTypes.contains(mt)) throw Exception("Only png, jpeg and gif image types are supported")

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
