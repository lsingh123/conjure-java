/*
 * (c) Copyright 2020 Palantir Technologies Inc. All rights reserved.
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

package com.palantir.conjure.java.services.dialogue;

import com.palantir.conjure.java.Generator;
import com.palantir.conjure.java.Options;
import com.palantir.conjure.java.types.DefaultClassNameVisitor;
import com.palantir.conjure.java.types.SafetyEvaluator;
import com.palantir.conjure.java.types.SpecializeBinaryClassNameVisitor;
import com.palantir.conjure.java.types.TypeMapper;
import com.palantir.conjure.java.util.TypeFunctions;
import com.palantir.conjure.spec.ConjureDefinition;
import com.palantir.conjure.spec.TypeDefinition;
import com.palantir.conjure.spec.TypeName;
import com.palantir.dialogue.BinaryRequestBody;
import com.palantir.logsafe.Preconditions;
import com.palantir.logsafe.SafeArg;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import java.io.InputStream;
import java.util.Map;
import java.util.stream.Stream;

// TODO(rfink): Add unit tests for misc edge cases, e.g.: docs/no-docs, auth/no-auth, binary return type.
public final class DialogueServiceGenerator implements Generator {

    private final Options options;

    public DialogueServiceGenerator(Options options) {
        this.options = options;
    }

    @Override
    public Stream<JavaFile> generate(ConjureDefinition conjureDefinition) {
        Map<TypeName, TypeDefinition> types = TypeFunctions.toTypesMap(conjureDefinition);
        SafetyEvaluator safetyEvaluator = new SafetyEvaluator(types);
        DialogueEndpointsGenerator endpoints = new DialogueEndpointsGenerator(options);
        TypeMapper parameterTypes = new TypeMapper(
                types,
                new SpecializeBinaryClassNameVisitor(
                        new DefaultClassNameVisitor(types.keySet(), options),
                        types,
                        ClassName.get(BinaryRequestBody.class)));
        TypeMapper returnTypes = new TypeMapper(
                types,
                new SpecializeBinaryClassNameVisitor(
                        new DefaultClassNameVisitor(types.keySet(), options), types, ClassName.get(InputStream.class)));
        ParameterTypeMapper parameterMapper = new ParameterTypeMapper(parameterTypes, safetyEvaluator);

        DialogueInterfaceGenerator interfaceGenerator =
                new DialogueInterfaceGenerator(options, parameterMapper, new ReturnTypeMapper(returnTypes));

        TypeNameResolver typeNameResolver = typeName -> Preconditions.checkNotNull(
                types.get(typeName), "Referenced unknown TypeName", SafeArg.of("typeName", typeName));

        StaticFactoryMethodGenerator asyncGenerator = new DefaultStaticFactoryMethodGenerator(
                options,
                typeNameResolver,
                parameterMapper,
                new ReturnTypeMapper(returnTypes),
                StaticFactoryMethodType.ASYNC);

        StaticFactoryMethodGenerator blockingGenerator = new DefaultStaticFactoryMethodGenerator(
                options,
                typeNameResolver,
                parameterMapper,
                new ReturnTypeMapper(returnTypes),
                StaticFactoryMethodType.BLOCKING);

        return conjureDefinition.getServices().stream()
                .flatMap(serviceDef -> Stream.of(
                        endpoints.endpointsClass(serviceDef),
                        interfaceGenerator.generateBlocking(serviceDef, blockingGenerator),
                        interfaceGenerator.generateAsync(serviceDef, asyncGenerator)));
    }
}
