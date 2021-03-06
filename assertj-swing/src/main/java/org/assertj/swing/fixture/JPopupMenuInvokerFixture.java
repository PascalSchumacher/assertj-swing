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
package org.assertj.swing.fixture;

import java.awt.Point;

import javax.annotation.Nonnull;

import org.assertj.swing.exception.ComponentLookupException;

/**
 * Simulates user input on {@code JComponent}s capable of invoking {@code JPopupMenu}s.
 * 
 * @author Alex Ruiz
 */
public interface JPopupMenuInvokerFixture {
  /**
   * Shows a pop-up menu using this fixture's {@code Component} as the invoker of the pop-up menu.
   * 
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's {@code Component} is disabled.
   * @throws IllegalStateException if this fixture's {@code Component} is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  @Nonnull
  JPopupMenuFixture showPopupMenu();

  /**
   * Shows a pop-up menu at the given point using this fixture's {@code Component} as the invoker of the pop-up menu.
   * 
   * @param p the given point where to show the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's {@code Component} is disabled.
   * @throws IllegalStateException if this fixture's {@code Component} is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  @Nonnull
  JPopupMenuFixture showPopupMenuAt(@Nonnull Point p);
}
