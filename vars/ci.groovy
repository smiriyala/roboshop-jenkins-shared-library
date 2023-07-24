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

    }
}