def call() {

    if (!env.sonar_extra_opts) {
        env.sonar_extra_opts = ""
    }

    node('workstation') {
        try{
            stage('Checkout Code') {
                cleanWs()
                git branch: 'staging' , url: 'https://github.com/smiriyala/cart'
            }

            stage('Compile/Build') {
                if (env.BRANCH_NAME != "staging") {
                        common.compile()
                    }
                }
            }
             

            stage ('Test Execution') {
                common.testcases()
            }

            stage('Code Quality') {
                common.codequality()
            }
        } catch (e){
            mail body: "<h1> ${component} - Pipeline Failed \n ${BUILD_URL}</h1>", from: 'callsubbu@gmail.com', subject: "${component} - Pipeline Failed", to: 'callsubbu@gmail.com', mimeType: 'text/html'

        }
    }
}