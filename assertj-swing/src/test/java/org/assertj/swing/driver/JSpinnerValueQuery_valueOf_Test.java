/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.swing.driver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.swing.edt.GuiActionRunner.execute;

import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;

import org.assertj.swing.annotation.RunsInEDT;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.test.core.MethodInvocations;
import org.assertj.swing.test.core.RobotBasedTestCase;
import org.assertj.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests for {@link JSpinnerValueQuery#valueOf(JSpinner)}.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JSpinnerValueQuery_valueOf_Test extends RobotBasedTestCase {
  private MySpinner spinner;

  @Override
  protected void onSetUp() {
    MyWindow window = MyWindow.createNew();
    spinner = window.spinner;
  }

  @Test
  public void should_Return_Value_In_JSpinner() {
    spinner.startRecording();
    assertThat(JSpinnerValueQuery.valueOf(spinner)).isEqualTo("Two");
    spinner.requireInvoked("getValue");
  }

  private static class MyWindow extends TestWindow {
    final MySpinner spinner = new MySpinner("One", "Two");

    @RunsInEDT
    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        @Override
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private MyWindow() {
      super(JSpinnerValueQuery_valueOf_Test.class);
      spinner.setValue("Two");
      addComponents(spinner);
    }
  }

  private static class MySpinner extends JSpinner {
    private boolean recording;
    private final MethodInvocations methodInvocations = new MethodInvocations();

    public MySpinner(Object... values) {
      super(new SpinnerListModel(values));
    }

    @Override
    public Object getValue() {
      if (recording) {
        methodInvocations.invoked("getValue");
      }
      return super.getValue();
    }

    void startRecording() {
      recording = true;
    }

    MethodInvocations requireInvoked(String methodName) {
      return methodInvocations.requireInvoked(methodName);
    }
  }
}
