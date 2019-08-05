node (label: 'slave1') {

    withMaven(maven:'maven') {

        stage('Checkout') {
            git url: 'https://github.com/reshmigu/AtoBe_Linux_ver1.1.git', credentialsId: 'master', branch: 'master'
        }

		stage('Build') {
		    sh 'mvn package shade:shade'
            def pom = readMavenPom file:'pom.xml'
            env.version = pom.version
        }

        stage('Image') {
                sh 'docker stop restassured || true && docker rm restassured || true'
                cmd = "docker rmi restassured:${env.version} || true"
                sh cmd
                docker.build "restassured:${env.version}"
        }

        stage ('Run') {
		
			print "${params}"
			
			sh "dir"
			
			if ("${params.modes}" == "DRY_RUN") {
       			 sh "docker run -p 8081:8081 -h restassured --name restassured --net host -m=500m restassured:${env.version} DRY_RUN"
      	     }
      	     else if("${params.modes}" == "RUN") {
	  	 	 	 sh "docker run -p 8081:8081 -h restassured --name restassured --net host -m=500m restassured:${env.version} RUN"
      	     }
      	     else if("${params.modes}" == "FULL_RUN") {
	  	 		 sh "docker run -p 8081:8081 -h restassured --name restassured --net host -m=500m restassured:${env.version} FULL_RUN"
      	     }
            
			sh "docker cp restassured:/test-output ."
		
        def config = [:]
		def subject = config.subject ? config.subject : "${env.JOB_NAME} - Build #${env.BUILD_NUMBER} - ${currentBuild.currentResult}!"      
        // Attach buildlog when the build is not successfull
        def attachLog = (config.attachLog != null) ? config.attachLog : (currentBuild.currentResult != "SUCCESS")
		
        env.ForEmailPlugin = env.WORKSPACE
        emailext mimeType: 'text/html',
		attachLog :attachLog,
		compressLog : true,
        body: '${FILE, path="test-output/emailable-report.html"}',
        subject: subject,	
        to: 'reshmi.g@thinkpalm.com'
		
        }

    }

}
