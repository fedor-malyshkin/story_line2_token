#!groovy
node {
   def projectName = 'token'
   def gradleHome = tool name: 'gradle', type: 'gradle'
   stage('Checkout') { // for display purposes
      // Get some code from a GitHub repository
	  // parent directory
	  checkout changelog: true, poll: false, scm: [$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'CleanBeforeCheckout']], submoduleCfg: [], userRemoteConfigs: [[url: 'story_line2_build.github.com:fedor-malyshkin/story_line2_build.git']]]
	  // project itself
	  checkout changelog: true, poll: true, scm: [$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: "${projectName}"]], submoduleCfg: [], userRemoteConfigs: [[url: "story_line2_${projectName}.github.com:fedor-malyshkin/story_line2_${projectName}.git"]]]
	  // deployment
	  checkout changelog: true, poll: false, scm: [$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'deployment']], submoduleCfg: [], userRemoteConfigs: [[url: 'story_line2_deployment.github.com:fedor-malyshkin/story_line2_deployment.git']]]
   }
   stage('Assemble') {
      // Run the maven build
     sh "'${gradleHome}/bin/gradle' -Pstand_type=test ${projectName}:assemble"
   }
   stage('Test') {
	   	try {
      		sh "'${gradleHome}/bin/gradle' -Pstand_type=test ${projectName}:test"
		}
		finally {
			junit '**/target/*.xml'
		}
   }
}
