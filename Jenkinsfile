node {
label 'windows' 
    withMaven(maven:'maven') {

        stage('Checkout') {
            git url: 'https://github.com/reshmigu/AtoBe.git', credentialsId: 'master', branch: 'master'
        }

		stage('Build') {
            bat 'mvn package shade:shade'
            def pom = readMavenPom file:'pom.xml'
            env.version = pom.version
        }

        stage('Image') {
                bat 'docker stop restassured || exit 0'
				bat 'docker rm restassured || exit 0'
                cmd = "docker rmi restassured:${env.version} || exit 0"
                bat cmd
                bat "docker build -t restassured:${env.version} ."
            
        }

        stage ('Run') {
	   		 bat "docker run -p 8081:8081 -h restassured --name restassured --net host -m=500m restassured:${env.version}"
          
        }

    }

}
