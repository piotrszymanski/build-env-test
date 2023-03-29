builds = [
    [ name: "npm-package-env:19",   path: "npm-package-env/19" ],
    [ name: "webclient-env:19",     path: "webclient-env/19" ],
]

// Extract Git properties from the environment.
def gitCredentials = getBinding().getVariables()['GIT_CREDENTIALS']
def gitUrl = getBinding().getVariables()['GIT_URL']
def gitBranch = getBinding().getVariables()['GIT_BRANCH']

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
