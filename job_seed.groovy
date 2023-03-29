builds = [
    [ name: "npm-package-env:19",   path: "npm-package-env/19" ],
    [ name: "webclient-env:19",     path: "webclient-env/19" ],
]

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
                            credentials(env.GIT_CREDENTIALS)
                            url(env.GIT_URL)
                        }
                        branch(env.GIT_BRANCH)

                        extensions {
                            pathRestriction {
                                includedRegions("${it.path}/.*")
                                excludedRegions("")
                            }

                            sparseCheckoutPaths {
                                sparseCheckoutPaths {
                                    sparseCheckoutPath {
                                        path("/${it.path}")
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
