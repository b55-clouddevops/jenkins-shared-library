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
                        env.ARGS="-Dsonar.sources=."
                        common.sonarChecks()
                        }
                    }
                }
            stage('Test Cases') {
                parallel {
                    stage('Unit Testing') {
                        steps {
                            sh "echo Starting Unit Testing"
                            sh "echo Unit Testing Completed"
                        }
                    }
                    stage('Integration Testing') {
                        steps {
                            sh "echo Starting Integration Testing"
                            sh "echo Integration Testing Completed"
                        }
                    }
                    stage('Functional Testing') {
                        steps {
                            sh "echo Starting Functional Testing"
                            sh "echo Functional Testing Completed"
                        }
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