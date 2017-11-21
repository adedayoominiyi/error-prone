/*
 * Copyright 2017 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.errorprone.bugpatterns;

import com.google.errorprone.BugCheckerRefactoringTestHelper;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** {@link SwitchDefault}Test */
@RunWith(JUnit4.class)
public class SwitchDefaultTest {
  private final BugCheckerRefactoringTestHelper testHelper =
      BugCheckerRefactoringTestHelper.newInstance(new SwitchDefault(), getClass());

  @Test
  public void refactoring_groupAndCase() throws IOException {
    testHelper
        .addInputLines(
            "in/Test.java", //
            "class Test {",
            "  void f(int i) {",
            "    switch (i) {",
            "      default:",
            "      case 0:",
            "        return;",
            "      case 1:",
            "        return;",
            "    }",
            "  }",
            "}")
        .addOutputLines(
            "out/Test.java", //
            "class Test {",
            "  void f(int i) {",
            "    switch (i) {",
            "      case 1:",
            "        return;",
            "      case 0:",
            "      default:",
            "        return;",
            "    }",
            "  }",
            "}")
        .doTest();
  }

  @Test
  public void refactoring_case() throws IOException {
    testHelper
        .addInputLines(
            "in/Test.java", //
            "class Test {",
            "  void f(int i) {",
            "    switch (i) {",
            "      case 2:",
            "        return;",
            "      case 1:",
            "      case 0:",
            "      default:",
            "        return;",
            "    }",
            "  }",
            "}")
        .addOutputLines(
            "out/Test.java", //
            "class Test {",
            "  void f(int i) {",
            "    switch (i) {",
            "      case 2:",
            "        return;",
            "      case 1:",
            "      case 0:",
            "      default:",
            "        return;",
            "    }",
            "  }",
            "}")
        .doTest();
  }

  @Test
  public void refactoring_group() throws IOException {
    testHelper
        .addInputLines(
            "in/Test.java", //
            "class Test {",
            "  void f(int i) {",
            "    switch (i) {",
            "      case 0:",
            "      default:",
            "        return;",
            "      case 1:",
            "        return;",
            "    }",
            "  }",
            "}")
        .addOutputLines(
            "out/Test.java", //
            "class Test {",
            "  void f(int i) {",
            "    switch (i) {",
            "      case 1:",
            "        return;",
            "      case 0:",
            "      default:",
            "        return;",
            "    }",
            "  }",
            "}")
        .doTest();
  }
}
