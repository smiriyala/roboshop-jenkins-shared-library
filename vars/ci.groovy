def call() {

    pipeline {

        agent any

        stages {
            stage('Compile/Build') {
                steps {
                    echo "Compile/Build"
                }
            }

            stage('Test Execution') {
                steps {
                    echo "Test Cases"
                }
            }
        }

    }
}