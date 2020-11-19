package com.mbds.newsletter.data.service

import com.mbds.newsletter.models.SourceQuery

interface SourceService {
    fun getSources(): SourceQuery
}