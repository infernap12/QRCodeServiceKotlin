package qrcodeapi.controller

import org.springframework.http.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import qrcodeapi.model.QrCode
import java.awt.image.*

@RestController
class QRCodeRestController {

    @GetMapping(path = ["/"])
    fun hello() = ResponseEntity("Hello World", HttpStatus.OK);

    @GetMapping(path = ["/api/health"])
    fun health() = ResponseEntity("", HttpStatus.OK)

    @GetMapping(path = ["/api/qrcode"])
    fun getImage(): ResponseEntity<BufferedImage> {
        val bufferedImage: BufferedImage = QrCode.testSquare()
        return ResponseEntity
            .ok()
            .contentType(MediaType.IMAGE_PNG)
            .body(bufferedImage)
    }
}
