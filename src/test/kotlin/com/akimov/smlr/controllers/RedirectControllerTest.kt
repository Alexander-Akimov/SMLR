package com.akimov.smlr.controllers

import com.akimov.smlr.SmlrApplication
import org.junit.Before
import org.junit.Test
import org.junit.experimental.results.ResultMatchers
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders.*

import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(classes = [SmlrApplication::class])
@WebAppConfiguration

class RedirectControllerTest {

    @Autowired
    lateinit var webApplicationContext: WebApplicationContext

    lateinit var mockMvc: MockMvc

    @Before
    fun init() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build()
    }

    private val HEADER_NAME = "Location"
    private val REDIRECT_STATUS: Int = 302
    private val HEADER_VALUE = "http://www.mail.com"
    private val PATH = "/aAdBcCdD"
    @Test
    fun controllerMustRedirectUsWhenRequestIsSuccessful() {
        mockMvc.perform(get(PATH))
                .andExpect(status().`is`(REDIRECT_STATUS)) /// WTF `is` !!!!!
                .andExpect(header().string(HEADER_NAME, HEADER_VALUE))
    }

    private val NOT_FOUND : Int = 404
    private val BAD_PATH = "/olololo"

    @Test
    fun controllerMustReturn404IfBadKey() {
        mockMvc.perform(get(BAD_PATH))
                .andExpect(status().`is`(NOT_FOUND))
    }
}