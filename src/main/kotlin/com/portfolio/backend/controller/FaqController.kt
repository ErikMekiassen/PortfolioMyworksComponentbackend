package com.portfolio.backend.controller
import com.portfolio.backend.data.faqType
import org.jdbi.v3.core.*
import org.jdbi.v3.sqlobject.*
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/FaqAPI")
class FaqController(private val jdbi: Jdbi) {
    @GetMapping("/getFaq")
    fun getFaq(): List<faqType> {
        return jdbi.open().use { handle ->
            handle.createQuery("SELECT * FROM FaqPage.dbo.FAQData;".trimIndent())
                .mapTo(faqType::class.java)
                .list()
        }
    }
    @PostMapping("/postFAQTopic", consumes = ["application/json"])
    fun postFAQTopic(@RequestBody request: faqType) {
        val workcard = faqType(
            request.id,
            request.header1,
            request.pharagraph,
        )
        return jdbi.open().use {handle ->
            handle.execute("INSERT INTO FaqPage.dbo.FAQData (header1, pharagraph) VALUES ('${workcard.header1}', '${workcard.pharagraph}')".trimIndent())}
    }
}