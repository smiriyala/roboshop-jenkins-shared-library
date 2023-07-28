// //BUILD Strategy to Achieve is : COMBILE, UNITTEST, PACKAGE, CODE QUALITY, ARTIFACTS. 
//  MAIN    ==== COMPILE.
//  BRANCH  ==== COMPILE,UNIT TESTS,
//  TAG     ==== COMBILE, PACKAGE, ARTIFACTS
//  PR      ==== COMPILE, UNITTEST, CODE QUALITY

def call() {

    if (!env.sonar_extra_opts) {
        env.sonar_extra_opts = ""
    }

    if (env.TAG_NAME ==~ ".*"){
        env.GTAG = "true"
    }

    node('workstation') {
        try{
            stage('Checkout Code') {
                cleanWs()
                git branch: 'main' , url: 'https://github.com/smiriyala/cart'
            }

            if (env.BRANCH_NAME != "main") {
                stage('Compile/Build') {
                    common.compile()
                }
            }
            
            if (env.GTAG != "true" && env.BRANCH_NAME != "main") {
                stage ('Test Execution') {
                    common.testcases()
                }
            }
            
            stage('Code Quality') {
                common.codequality()
            }
        } catch (e){
            mail body: "<h1> ${component} - Pipeline Failed \n ${BUILD_URL}</h1>", from: 'callsubbu@gmail.com', subject: "${component} - Pipeline Failed", to: 'callsubbu@gmail.com', mimeType: 'text/html'

        }
    }
}