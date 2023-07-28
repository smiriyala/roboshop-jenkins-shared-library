//app_lang variable is returning value from each component - cart, category, payment etc..
//this compile function used in CI.groovy file
def compile() {

    if(app_lang == "nodejs"){
     sh 'npm install --no-fund'
    }
    if(app_lang == "maven"){
        sh 'mvn package'
    }
}


def testcases() {

    // npm test
    // mvn test
    // python -m unittests
    // go test
    
    sh 'echo Testcases Executed'
    // if(app_lang == "nodejs"){
    //     sh 'echo testcases execution'
    // }
    // if(app_lang == "maven"){
    //     sh 'echo testcases execution'
    // }
}

def codequality() {
    withAWSParameterStore(credentialsId: 'PARAM1', naming: 'absolute', path: '/sonarqube', recursive: true, regionName: 'us-east-1') {
        echo 'SonarQube Execution'
        //sh 'sonar-scanner -Dsonar.host.url=http://18.212.40.22:9000 -Dsonar.login=${SONARQUBE_USER} -Dsonar.password=${SONARQUBE_PASS} -Dsonar.projectKey=${component} ${sonar_extra_opts} -Dsonar.qualitygate.wait=true'
    }
}






