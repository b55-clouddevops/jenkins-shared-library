def lintChecks() {
        sh "echo starting linkChecks for ${COMPONENT}"
        sh "pylint *.py || true" 
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
        }
    }
}