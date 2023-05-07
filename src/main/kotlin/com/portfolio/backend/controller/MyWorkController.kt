package com.portfolio.backend.controller
import com.portfolio.backend.data.workType
import org.jdbi.v3.core.*
import org.jdbi.v3.sqlobject.*
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("/API")
class MyWorkController(private val jdbi: Jdbi) {
    @GetMapping("/GetProjects")
    fun sendWork(): List<workType> {
        return jdbi.open().use { handle ->
            handle.createQuery("select * from myWorks")
                    .mapTo(workType::class.java)
                    .list()
        }
    }
    @PostMapping("/PostProject", consumes = ["application/json"])
    fun postProjects(@RequestBody request: workType) {
        val workcard = workType(
            request.href,
            request.techTitle,
            request.title,
            request.description,
            request.repoLink
        )
        return jdbi.open().use {handle ->
        handle.execute("INSERT INTO myWorks VALUES ('${workcard.href}', '${workcard.techTitle}', '${workcard.title}', '${workcard.description}', '${workcard.repoLink}')")}
    }
}