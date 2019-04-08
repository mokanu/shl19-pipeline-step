package org.shl19

import com.lesfurets.jenkins.unit.BasePipelineTest
import com.lesfurets.jenkins.unit.BaseRegressionTest
import com.lesfurets.jenkins.unit.MethodCall
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain


/**
 * Mock Docker class from docker-workflow plugin.
 */


class Docker implements Serializable {

    public Image image(String id) {
        new Image(this, id)
    }

    public static class Image implements Serializable {

        private Image(Docker docker, String id) {}
        public <V> V inside(String args = '', Closure<V> body) {}

    }
}


class BuildWrapperTest extends BaseRegressionTest {

    @Override
    @Before
    void setUp() {
        super.setUp()
        binding.setVariable('scm', [:])
        //helper.registerAllowedMethod('sh', [String.class], null)
        binding.setProperty('docker', new Docker())
    }

  @Test
  public void callStack() throws Exception {

    def script = loadScript('vars/buildWrapper.groovy')

    script.call({
      settings = "dummy.xml"
    })

    List<MethodCall> stages = helper.getCallStack().findAll { call -> call.getMethodName() == "stage" }
    Assert.assertEquals("Checkout",               stages[0].getArgs()[0].toString())

    printCallStack()
    testNonRegression("callStack")
  }
}