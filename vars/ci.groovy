def call() {

    pipeline {

        agent any

        stages {
            stage('Compile/Build') {
                steps {
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