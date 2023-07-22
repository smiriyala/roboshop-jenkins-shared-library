//app_lang variable is returning value from each component - cart, category, payment etc..
//this compile function used in CI.groovy file
def compile() {

    if(app_lang == "nodejs"){
     sh 'npm install'
    }
    if(app_lang == "maven"){
        sh 'mvm package'
    }
}


def testcases() {

    if(app_lang == "nodejs"){
        sh 'echo testcases execution'
    }
    if(app_lang == "maven"){
        sh 'echo testcases execution'
    }
}

