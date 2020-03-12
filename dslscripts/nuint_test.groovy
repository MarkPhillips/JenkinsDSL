
String repo = 'MarkPhillips/DSLTest'

job("DownloadApp"){
   scm{
      https://github.com/MarkPhillips/NUnitTest.git
   }
   triggers {
      scm 'H/30 * * * *'
   }
}

job("BuildApp"){
   steps{
      msBuild{
         msBuildInstallation('MSBuild 15.0')
         buildfile('NUnitTest.sln')
         args(-t: restore;build)
         passBuildVariables()
      }
   }
}


