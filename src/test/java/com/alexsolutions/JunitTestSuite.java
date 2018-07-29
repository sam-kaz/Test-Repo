package com.alexsolutions;

import com.alexsolutions.PathBuilderTest.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        CorrectDataNoDependentCourseTest.class,
        CorrectDataTest.class, CorrectDataTestDeadLockTest.class, CorrectDataTestDependentAllCoursesTest.class, CorrectDataTestSequienceTest.class
})

public class JunitTestSuite {
}