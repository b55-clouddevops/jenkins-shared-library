def lintChecks() {
        sh "echo starting linkChecks for ${COMPONENT}"
        sh "mvn checkstyle:check || true"
        sh "echo linkChecks completed for ${COMPONENT}"
}

def call() {
    pipeline {
        agent any 
        stages {
            stage('Lint Checks') {
                steps {
                    script {
                        lintChecks()
                    }
                }
            }
            stage('Generating Artifacts') {
                steps {
                    sh "echo Generating Artifiacts for $COMPONENT"
                    sh "mvn clean compile"
                    }
                }
            }
        }
    }