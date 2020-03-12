
String repo = 'https://github.com/MarkPhillips/NUnitTest.git'
String branch = 'master'

job("job-checkout-testapp"){
   scm{
      git(repo, branch)
   }

   triggers {
      scm '* * * * *'
   }

   /**/

   publishers {
      downstream 'job-compile-testapp', 'SUCCESS'
   }

}

/**/
job("job-compile-testapp"){
   steps{
      msBuild{
         msBuildInstallation('MSBuild 15.0')
         buildfile("$WORKSPACE/NUnitTest.sln")
         args("restore")
         args("build")

         passBuildVariables(true)
         continueOnBuildFailure(false)
      }
   }
}



