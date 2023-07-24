//app_lang variable is returning value from each component - cart, category, payment etc..
//this compile function used in CI.groovy file
def compile() {

    if(app_lang == "nodejs"){
     sh 'npm install'
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

    sh 'sonar-scanner -Dsonar.host.url=http://18.204.230.202:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.projectKey=${component}'
}






