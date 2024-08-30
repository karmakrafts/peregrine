/*
 * Copyright 2024 Karma Krafts & associates
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.karma.peregrine.uniform;

import io.karma.peregrine.shader.ShaderProgram;
import io.karma.peregrine.uniform.MatrixUniform.Mat3Uniform;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Matrix3f;
import org.joml.Matrix3fc;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

/**
 * @author Alexander Hinze
 * @since 30/08/2024
 */
@OnlyIn(Dist.CLIENT)
public final class DefaultMat3Uniform extends AbstractUniform<Matrix3f> implements Mat3Uniform {
    private final Matrix3f value = new Matrix3f();

    DefaultMat3Uniform(final String name, final Object defaultValue) {
        super(name);
        if (!(defaultValue instanceof Matrix3fc matrix)) {
            throw new IllegalArgumentException("Default matrix is not an integer");
        }
        value.set(matrix);
    }

    @Override
    public void apply(final ShaderProgram program) {
        if (!requiresUpdate) {
            return;
        }
        try (final var stack = MemoryStack.stackPush()) {
            GL20.glUniformMatrix3fv(program.getUniformLocation(name), false, value.get(stack.mallocFloat(9)));
        }
        requiresUpdate = false;
    }

    @Override
    public void upload(final long address) {
        if (!requiresUpdate) {
            return;
        }
        try (final var stack = MemoryStack.stackPush()) {
            MemoryUtil.memCopy(MemoryUtil.memAddress(value.get(stack.mallocFloat(9))), address, Float.BYTES * 9);
        }
        requiresUpdate = false;
    }

    @Override
    public void set(final Matrix3f value) {
        if (this.value.equals(value)) {
            return;
        }
        this.value.set(value);
        requiresUpdate = true;
    }

    @Override
    public Matrix3f get() {
        return value;
    }
}
