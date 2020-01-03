package com.project.dashboard.service

import com.project.dashboard.model.Filters


interface FilterService{

    fun getFilters(): Filters
}