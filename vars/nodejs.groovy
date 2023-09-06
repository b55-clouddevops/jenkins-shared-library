def lintChecks() {
        sh "echo Installing JSlist"
        sh "npm i jslint"
        sh "echo starting linkChecks for ${COMPONENT}"
        sh "node_modules/jslint/bin/jslint.js server.js || true"
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
                    sh "echo Generating Artifiacts...."
                    sh "npm install"
                    }
                }
            }
        }
    }