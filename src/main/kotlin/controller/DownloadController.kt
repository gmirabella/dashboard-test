package com.project.dashboard.controller

import com.project.dashboard.model.Download

import javax.ws.rs.*
import javax.ws.rs.core.MediaType

interface DownloadController{

    @GET
    @Path("/")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    fun getAll()

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun createDownload(download: Download)

}