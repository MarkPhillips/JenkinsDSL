
String repo = 'https://github.com/MarkPhillips/NUnitTest.git'

job("DownloadApp"){
   scm{
      github  repo
   }

   triggers {
      scm 'H/30 * * * *'
   }
}

/*
job("BuildApp"){
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


