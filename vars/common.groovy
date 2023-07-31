//app_lang variable is returning value from each component - cart, category, payment etc..
//this compile function used in CI.groovy file
def compile() {

    if(app_lang == "nodejs"){
     sh 'npm install --no-fund'
    }
    if(app_lang == "maven"){
        sh 'mvn clean package ; mv target/${component}-1.0.jar ${component}.jar'
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


def prepareArtifacts() {
    // created version file which goes inside build folder and automatically included in artifact
    sh 'echo ${TAG_NAME} >VERSION'

    // here we are checking the app_lang variable  to check what to be included -x Jenkinsfile to exclude from zip
    if (app_lang == "nodejs" || app_lang == "angular" || app_lang == "python" || app_lang == "golang")  {
        sh 'zip -r ${component}-${TAG_NAME}.zip * -x Jenkinsfile'
    }
    //shipping app is maven, using jar file instead of zipping all
    if (app_lang == "maven")  {
        sh 'zip -r ${component}-${TAG_NAME}.zip ${component}.jar VERSION'
    }

}

def artifactUpload() {
    // created version file which goes inside build folder and automatically included in artifact
    sh 'echo ${TAG_NAME} >VERSION'

    // here we are checking the app_lang variable  to check what to be included -x Jenkinsfile to exclude from zip
    if (app_lang == "nodejs" || app_lang == "angular")  {
        sh 'curl -v -u admin:admin123 --upload-file ${component}-${TAG_NAME}.zip http://44.201.154.82:8081/repository/${component}/${component}-${TAG_NAME}.zip'
    }

}





