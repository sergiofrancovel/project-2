pipeline {
    environment{
        registry = 'keoffor/project-2'
        dockerHubCreds = 'Docker_hub'
        dockerImage = ''
        deploymentFile = 'k8s/deployment.yml'
    }
  agent any
  stages {
    stage('Test') {
        when{
        branch 'Features'
        }
      steps {
        sh 'ls $WORKSPACE '
        dir("project2") {
        sh 'echo "Hello World"'
          withMaven {
            sh 'mvn test'
          }
         }
        }
       }
          stage('Build') {
               when {
                   branch 'main'
               }
               steps {
                    sh 'ls $WORKSPACE '
                            dir("project2") {
                            sh 'echo "Hello World"'
                   withMaven {
                       sh 'mvn clean package -DskipTests'
                   }
               }
               }
           }
            stage('Docker Build') {
                   when {
                       branch 'main'
                   }
                   steps {
                        dir("project2") {
                       script {
                           echo "$registry:$currentBuild.number"
                           dockerImage = docker.build "$registry"
                       }
                   }
               }
             }
             stage('Docker Deliver') {
                     when {
                         branch 'main'
                     }
                     steps {
                             dir("project2") {
                         script {
                             docker.withRegistry('', dockerHubCreds) {
                                 dockerImage.push("$currentBuild.number")
                                 dockerImage.push("latest")
                             }
                         }
                     }
                 }
               }
           stage('Deploy to GKE') {
                   when {
                       branch 'main'
                   }
                   steps{
                      sh 'sed -i "s/%TAG%/$BUILD_NUMBER/g" ./k8s/deployment.yml'
                      sh 'cat ./k8s/deployment.yml'
                       step([$class: 'KubernetesEngineBuilder',
                           projectId: 'macro-key-339512',
                           clusterName: 'macro-key-339512-gke',
                           zone: 'us-central1',
                           manifestPattern: 'k8s/',
                           credentialsId: 'macro-key-339512',
                           verifyDeployments: true
                       ])

                       cleanWs()

                       discordSend description: "Build #$currentBuild.number",
                           link: BUILD_URL, result: currentBuild.currentResult,
                           title: JOB_NAME,
                           webhookURL: "https://discord.com/api/webhooks/946097550514061343/7IRGxvAsw24cbGPIHXE15gtxCvzQQtRl3e5DEcm7arQpC6x3cVJPXXWZo7UWHKyJumuW"
                   }
               }
}
}


