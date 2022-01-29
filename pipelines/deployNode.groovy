pipeline {
    agent any
    stages {
        stage('Clean Jenkins Workspace') {
            steps {
                cleanWs()
            }
        }
        
        stage('Check Vars') {
            steps {
                sh "echo check first if vars are compliant"
                sh "echo ENV: $ENV"
                sh "echo GIT_BRANCH: $GIT_BRANCH"
            }
        }
        
        stage('Checkout git') {
            steps {
                git(
                    url: 'https://github.com/lcshlr/react-jenkins.git',
                    branch: "${GIT_BRANCH}"
                )
                sh "pwd"
                sh "ls"
                sh "docker build -t reactdeploy:${ENV} ."
            }
        }
    }
}