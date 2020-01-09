package com.cuebiq.audiencebuilder.container

import com.github.dockerjava.api.command.InspectContainerResponse
import org.slf4j.LoggerFactory
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.ext.ScriptUtils
import java.io.File

class KPostgresSQLContainer(private val baseDir: String): PostgreSQLContainer<KPostgresSQLContainer>() {
    private val log = LoggerFactory.getLogger(KPostgresSQLContainer::class.java)

    override fun containerIsStarted(containerInfo: InspectContainerResponse?) {
        val resource = this.javaClass.classLoader.getResource(baseDir) ?: throw IllegalArgumentException("Could not find $baseDir folder...")
        val folder = File(resource.toURI())
        if (!folder.isDirectory) throw IllegalArgumentException("$baseDir must be a folder...")
        val sqlFiles = folder.listFiles() ?: emptyArray()
        if (sqlFiles.isEmpty()) {
            log.info("Could not find any file in directory $baseDir, container will be empty")
        }
        log.info("Found ${sqlFiles.size} init scripts...")
        sqlFiles.map { "$baseDir/${it.name}" }.sorted().forEach { initScript ->
            log.info("Executing script $initScript...")
            ScriptUtils.runInitScript(databaseDelegate, initScript)
        }
    }
}