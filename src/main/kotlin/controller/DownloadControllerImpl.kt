package com.project.dashboard.controller


import com.project.dashboard.model.Download
import org.springframework.stereotype.Controller
import javax.ws.rs.Path

@Controller
@Path("downloads")
class DownloadControllerImpl : DownloadController {

    override fun getAll() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createDownload(download: Download) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}