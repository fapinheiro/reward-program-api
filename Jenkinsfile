#!groovy
pipeline {
  agent { label 'maven' }
  options {
    skipDefaultCheckout() // Needed to avoid Git checkout for Docker slave
    disableConcurrentBuilds()
    timeout(time: 1, unit: 'HOURS')
    buildDiscarder(logRotator(daysToKeepStr: '2'))
  }
  environment {
    APP_NAME = "reward-program-api"
  }
  stages {
    stage ('Checkout') {
      steps {
        checkout scm
      }
    }
    stage ('Setup CI/CD Environment') {
      steps {
        script {
           // TODO
        }
      }
    }
    stage ('Build source') {
      environment {
          MAVEN_OPTS = '-Xmx1024m'
      }
      steps {
        // npm
        sh 'mvn clean package'

        stash name: 'app'
      }
    }

    stage ('Tests') {
      steps {
        sh 'mvn clean verify'
      }
    }

    stage ('Code Analysis') {
        steps {
            // TODO
        }
    }

    stage ('Dependency Check') {
        environment {
            SONAR_SCANNER_OPTS='-Xmx512m' // Java heap memory size - limit set to 1/4 available memory
        }
        steps {
            // TODO dependency check
        }
    }
    stage ('Build and Upload Image') {
      agent { label 'docker' }
      steps {
        unstash("app")
        // TODO build and upload Doker image to registry
      }
    }

    stage ('Verify Image') {

      steps {
        // TODO Image vulnerability
      }
    }

    stage ('Deploy Image') {
      steps {
        // TODO 
      }
    }

    stage ('Verify Service') {
      steps {
        // TODO Liveness
      }
    }

 }
}
