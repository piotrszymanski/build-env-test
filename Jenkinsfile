pipeline {
    agent {
        label 'iaccess-builder'
    }

    stages {
        stage('Configure jobs') {
            script {
                jobDsl targets: "job_seed.groovy",
                    removedJobAction: 'DELETE',
                    removedViewAction: 'DELETE',
                    lookupStrategy: 'SEED_JOB'
            }
        }
    }
}
