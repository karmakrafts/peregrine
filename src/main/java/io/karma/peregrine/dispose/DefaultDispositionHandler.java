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

package io.karma.peregrine.dispose;

import io.karma.peregrine.PeregrineMod;
import io.karma.peregrine.api.dispose.Disposable;
import io.karma.peregrine.api.dispose.DispositionHandler;
import io.karma.peregrine.api.util.Dispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Alexander Hinze
 * @since 29/08/2024
 */
@OnlyIn(Dist.CLIENT)
public final class DefaultDispositionHandler implements DispositionHandler {
    private final ConcurrentLinkedQueue<WeakReference<Disposable>> objects = new ConcurrentLinkedQueue<>();

    @Override
    public void disposeAll() {
        for (final var ref : objects) {
            final var object = ref.get();
            if (object == null) {
                continue; // Object was garbage collected, skip
            }
            if (object.getDisposeDispatcher() == Dispatcher.BACKGROUND) {
                PeregrineMod.EXECUTOR_SERVICE.submit(object::dispose);
                continue;
            }
            object.dispose();
        }
        objects.clear();
    }

    @Override
    public void register(final Disposable disposable) {
        if (objects.stream().anyMatch(ref -> ref.get() == disposable)) {
            return;
        }
        objects.add(new WeakReference<>(disposable));
    }

    @Override
    public void unregister(final Disposable disposable) {
        objects.removeIf(ref -> ref.get() == disposable);
    }

    @Override
    public List<Disposable> getObjects() {
        // @formatter:off
        return objects.stream()
            .map(WeakReference::get)
            .filter(Objects::nonNull)
            .toList();
        // @formatter:on
    }
}
