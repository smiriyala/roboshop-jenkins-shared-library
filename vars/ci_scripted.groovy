def call() {

    if (!env.sonar_extra_opts) {
        env.sonar_extra_opts = ""
    }

    node('workstation') {
        try{
            stage('Checkout Code'){
                cleanWs()
                git branch: 'main' , url: 'https://github.com/smiriyala/cart'
            }
            if (evn.BRANCH_NAME != "main"){
                stage('Compile/Build'){
                    sh 'env'
                    ommon.compile()
                }
            }
             

            stage('Test Execution') {
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