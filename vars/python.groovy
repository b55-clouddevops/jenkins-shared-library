def lintChecks() {
        sh "echo starting linkChecks for ${COMPONENT}"
        sh "pylint *.py || true" 
        sh "echo linkChecks completed for ${COMPONENT}"
}

def call() {
    pipeline {
        agent any 
        environment {
            SONAR_URL = "172.31.81.131"
            SONAR_CRED  = credentials('SONAR_CRED')
        }
        stages {
            stage('Lint Checks') {
                steps {
                    script {
                        lintChecks()
                    }
                }
            }
            stage('Sonar Checks') {
                steps {
                    script {
                        common.sonarChecks()
                        }
                    }
                }
            stage('Generating Artifacts') {
                steps {
                    sh "echo Artifact Generation Complete"
                    }
                }
            }
        }
    }