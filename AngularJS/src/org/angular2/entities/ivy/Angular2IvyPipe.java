// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.angular2.entities.ivy;

import com.intellij.lang.javascript.psi.ecma6.TypeScriptField;
import com.intellij.lang.javascript.psi.ecma6.TypeScriptFunction;
import com.intellij.psi.util.CachedValueProvider;
import org.angular2.entities.Angular2EntityUtils;
import org.angular2.entities.Angular2Pipe;
import org.angular2.lang.Angular2Bundle;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

import static com.intellij.util.ObjectUtils.notNull;
import static org.angular2.entities.ivy.Angular2IvyUtil.PIPE_DEF;

public class Angular2IvyPipe extends Angular2IvyDeclaration implements Angular2Pipe {

  public Angular2IvyPipe(@NotNull TypeScriptField defField) {
    super(defField);
  }

  @NotNull
  @Override
  public String getName() {
    return notNull(PIPE_DEF.getName(myDefField), () -> Angular2Bundle.message("angular.description.unnamed"));
  }

  @NotNull
  @Override
  public Collection<? extends TypeScriptFunction> getTransformMethods() {
    return getCachedValue(() -> CachedValueProvider.Result.create(
      Angular2EntityUtils.getPipeTransformMethods(getTypeScriptClass()), getClassModificationDependencies())
    );
  }
}
