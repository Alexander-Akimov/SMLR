package com.akimov.smlr.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/{key}")
class RedirectController {

    @RequestMapping()
    fun redirect(@PathVariable("key") key: String, response: HttpServletResponse) {
        if(key.equals("aAdBcCdD")) {
            response.setHeader(HEADER_NAME, "http://www.mail.com")
            response.status = 302
        } else
        {
            response.status = 404
        }
    }
    companion object {
        private val HEADER_NAME = "Location"
    }
}