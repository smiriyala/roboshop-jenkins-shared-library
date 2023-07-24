def call() {

    if (!env.sonar_extra_opts) {
        env.sonar_extra_opts = ""
    }

    pipeline {

        agent any

        stages {
            stage('Compile/Build') {
                steps {
                    sh 'env'
                    script{

                        common.compile()
                    }
                }
            }

            stage('Test Execution') {
                steps {
                    script{
                        common.testcases()
                    }
                }
            }

            stage('Code Quality') {
                steps {
                    script{
                        common.codequality()
                    }
                }
            }
        }

        post {
            failure {
                mail body: "<h1> ${component} - Pipeline Failed \n ${BUILD_URL}</h1>", from: 'callsubbu@gmail.com', subject: "${component} - Pipeline Failed", to: 'callsubbu@gmail.com', mimeType: 'text/html'
            }
        }

    }
}