def call() {
    node {
        git branch: 'main', url: "https://github.com/b55-clouddevops/${COMPONENT}.git"
        common.lintChecks()
        env.ARGS="-Dsonar.sources=."
        common.sonarChecks()
        common.testCases()
        common.artifacts()
    }
}
