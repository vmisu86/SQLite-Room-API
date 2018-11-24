package com.vmisu.databases.apiDatabase


import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
/*
* XML Pull Parser is an interface that defines parsing functionality provided in XMLPULL V1 API
*
* */


class ParseApplications {
    private val TAG = "ParseApplications"
    val applications = ArrayList<FeedEntry>()

    fun parse(xmlData: String): Boolean {
        Log.d(TAG, "parse called with $xmlData")
        var status = true
        var inEntry = false
        var textValue = ""

        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val xpp = factory.newPullParser()
            xpp.setInput(xmlData.reader())
            var eventType = xpp.eventType
            var currentRecord = FeedEntry()
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = xpp.name?.toLowerCase()
                when (eventType) {

                    XmlPullParser.START_TAG -> {
                        Log.d(TAG, "parse: Starting tag for " + tagName)
                        //the begin TAG of the section
                        if (tagName == "entry") {
                            inEntry = true
                        }
                    }

                    XmlPullParser.TEXT -> textValue = xpp.text

                    XmlPullParser.END_TAG -> {
                        Log.d(TAG, "parse: Ending tag for " + tagName)
                        if (inEntry) {
                            when (tagName) {
                                "entry" -> {
                                    applications.add(currentRecord)
                                    inEntry = false
                                    currentRecord = FeedEntry()   // create a new object
                                }
                                //Select the TAG names to show
                                "title" -> currentRecord.title = textValue
                                "published" -> currentRecord.published = textValue
                                "link" -> currentRecord.link = textValue
                                "summary" -> currentRecord.summary = textValue
                            }
                        }
                    }
                }

                // Nothing else to do.
                eventType = xpp.next()
            }

        } catch (e: Exception) {
            e.printStackTrace()
            status = false
        }

        return status
    }
}