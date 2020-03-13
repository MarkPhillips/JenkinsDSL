job('job-dsl-checkout'){
   scm{
      github("MarkPhillips/java-hello-world", 'master')
   }

   publishers{
      publishCloneWorkspace '**', '', 'Any', 'TAR', true, null
      downstream 'job-dsl-compile', 'SUCCESS'
   }
}

mavenJob('job-dsl-compile'){
      scm{
         cloneWorkspace 'job-dsl-checkout'
      }

      mavenInstallation('Maven 3.6.3')
      goals('compile')

      publishers {
         publishCloneWorkspace '**', '', 'Any', 'TAR', true, null
         downstream 'job-dsl-package', 'SUCCESS'
      }
}

mavenJob('job-dsl-package'){
      scm{
         cloneWorkspace 'job-dsl-checkout'
      }

      mavenInstallation('Maven 3.6.3')
      goals('package')
}

/*

job('job-dsl-deploy') {
    description 'Deploy app to the demo server'

    steps{
             shell 'sshpass -p "123456" scp /var/lib/jenkins/workspace/job-dsl-checkout/target/hello-world-war-1.0.0.war release@10.12.108.11:/opt/tomcat/webapps/'
      }
}
*/



listView('Listview'){
   jobs{
      regex('job-dsl-.+')~
   }
   columns{
      status()
      nameSuccess()
      lastFailure()
      lastDuration()
      buildButton()
   }
}