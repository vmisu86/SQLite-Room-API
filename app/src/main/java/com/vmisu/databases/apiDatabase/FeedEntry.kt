package com.vmisu.databases.apiDatabase

class FeedEntry {
    var title: String = ""
    var published: String = ""
    var link: String = ""
    var summary: String = ""

    override fun toString(): String {
        return """
            Title = $title
            Published = $published
            Summary = $summary
        """.trimIndent()
    }
}