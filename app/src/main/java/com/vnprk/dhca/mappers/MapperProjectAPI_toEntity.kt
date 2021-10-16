package com.vnprk.dhca.mappers

import com.vnprk.dhca.di.DateUtils
import com.vnprk.dhca.models.ProjectAPI
import com.vnprk.dhca.models.ProjectEntity

class MapperProjectAPI_toEntity {
    companion object{
        fun convert(projectApi: ProjectAPI) = ProjectEntity(
            id = projectApi.id,
            name = projectApi.name,
            description = projectApi.description,
            datetimeCreate = DateUtils.stringToLong(projectApi.datetimeCreate),
            datetimeFinish = DateUtils.stringToLong(projectApi.datetimeFinish?:""),
            isFinish = projectApi.isFinish==1
        )
    }
}