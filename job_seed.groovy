builds = [
    [ name: "npm-package-env:19",   path: "npm-package-env/19" ],
    [ name: "webclient-env:19",     path: "webclient-env/19" ],
]

// gitUrl
// gitBranch
// gitCredentials

builds.each {
    pipelineJob(it.name) {
        properties {
            disableConcurrentBuilds()
        }

        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            credentials(gitCredentials)
                            url(gitUrl)
                        }
                        branch(gitBranch)

                        extensions {
                            pathRestriction {
                                includedRegions("${it.path}/.*")
                            }

                            sparseCheckoutPaths {
                                sparseCheckoutPaths {
                                    sparseCheckoutPath {
                                        path(it.path)
                                    }
                                }

                            } 
                        }
                    }
                }
                scriptPath("Jenkinsfile")
            }
        }

        triggers {
            scm('H/5 * * * *')
        }

        logRotator {
            artifactNumToKeep(1)
        }
    }
}
