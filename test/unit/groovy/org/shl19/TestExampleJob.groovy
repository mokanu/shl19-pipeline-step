package org.shl19

import com.lesfurets.jenkins.unit.BasePipelineTest
import com.lesfurets.jenkins.unit.MethodCall
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

class TestExampleJob extends BasePipelineTest {
        
    @Before
    void setUp() {
        super.setUp()
        binding.setVariable('scm', [:])

    }
        
    @Test
    void should_execute_without_errors() throws Exception {
        def script = loadScript("vars/exampleJob.groovy")
        script.execute()
        //binding.getVariable('currentBuild').result = 'FAILURE'
        //assertJobStatusFailure()
        assertJobStatusSuccess()
        printCallStack()
    }
}