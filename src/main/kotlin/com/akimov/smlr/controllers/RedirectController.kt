package com.akimov.smlr.controllers

import com.akimov.smlr.services.KeyMapperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/{key}")
class RedirectController {

    @Autowired
    lateinit var service : KeyMapperService

    @RequestMapping()
    fun redirect(@PathVariable("key") key: String, response: HttpServletResponse) {

        val result = service.getLink(key)
        when(result)
        {
            is KeyMapperService.Get.Link -> {
                response.setHeader(HEADER_NAME, result.link);
                response.status = 302
            }
            is KeyMapperService.Get.NotFound -> {
                response.status = 404
            }
        }

      /*  if(key.equals("aAdBcCdD")) {
            response.setHeader(HEADER_NAME, "http://www.mail.com")
            response.status = 302
        } else
        {
            response.status = 404
        }*/
    }
    companion object {
        private val HEADER_NAME = "Location"
    }
}