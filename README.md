# Introduction
Testing suite for Meetup December 2019

Maven execusion:
    test -DrunOn=grid -Dbrowsers=firefox59,chrome78 -Denv=prod -DgroupsToRun=BMITrackerCalculation

    runOn
        grid
        safari
        chrome
        firefox

    browsers:
        Check in browsersConfig.json

    env (see variables in yml files)
        dev
        prod
        qa

     groupsToRun
        Test that you want to run. The groups should be configured in each test.
