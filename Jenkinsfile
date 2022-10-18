pipeline {
    agent any

    tools {
        maven "maven 3"
    }

    stages {
        stage('Build') {
            steps {
                git branch: 'master', url: 'https://github.com/MichaelMironov/examen.git'

                sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
    }

    post {
        always{
            allure([
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'autotest-rest/target/allure-results']]
                results: [[path: 'autotest-web/target/allure-results']]
            ])
        }
    }
}