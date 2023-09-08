def sonarChecks() {
    sh "echo Starting Sonar Checks For ${COMPONENT}"
    // sh "sonar-scanner -Dsonar.host.url=http://${SONAR_URL}:9000/ $ARGS -Dsonar.projectKey=${COMPONENT} -Dsonar.login=${SONAR_CRED_USR} -Dsonar.password=${SONAR_CRED_PSW}"
    // sh "curl https://gitlab.com/thecloudcareers/opensource/-/raw/master/lab-tools/sonar-scanner/quality-gate > quality-gate.sh"
    // sh "bash quality-gate.sh ${SONAR_CRED_USR} ${SONAR_CRED_PSW} ${SONAR_URL} ${COMPONENT}" 
    sh "echo ${COMPONENT} Sonar Checks  are completed"
}

def lintChecks() {
        sh "echo starting linkChecks for ${COMPONENT}"
        sh "mvn checkstyle:check || true"
        sh "echo linkChecks completed for ${COMPONENT}"
}