package qrcodeapi.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class QRCodeRestController {

    @GetMapping(path = ["/"])
    fun hello() = ResponseEntity("Hello World", HttpStatus.OK);

    @GetMapping(path = ["/api/health"])
    fun health() = ResponseEntity("", HttpStatus.OK)

    @GetMapping(path = ["/api/qrcode"])
    fun qrcode() = ResponseEntity("", HttpStatus.NOT_IMPLEMENTED)
}
