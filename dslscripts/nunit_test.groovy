
String repo = 'https://github.com/MarkPhillips/NUnitTest.git'
String branch = 'master'

job("job-checkout-testapp"){
   scm{
      git(repo, branch)
   }

   triggers {
      scm 'H/30 * * * *'
   }

   /*

   publishers {
      downstream 'job-build-testapp', 'SUCCESS'
   }
   */
}

/*
job("job-compile-testapp"){
   steps{
      msBuild{
         msBuildInstallation('MSBuild 15.0')
         buildfile("NUnitTest.sln")
         args("restore")
         args("build")

         passBuildVariables(true)
         continueOnBuildFailure(false)
      }
   }
}
*/


