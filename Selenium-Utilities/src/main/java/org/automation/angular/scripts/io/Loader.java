package org.automation.angular.scripts.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Loads client side scripts from classpath.
 */
public final class Loader {
    /**
     * File name in classpath.
     */
    private final transient String filename;
    private final transient String folder="/jprotector/";

    /**
     * Ctor.
     * @param file File name in classpath.
     */
    public Loader(final String file) {
        this.filename = file + ".js";
    }

    public String content() {
        try (
            final InputStream stream = Loader.class
                .getClassLoader().getResourceAsStream(folder+this.filename)
        ) {
            if (stream == null) {
                throw new JsLoadException(this.filename);
            }
            final byte[] bytes = new byte[stream.available()];
            stream.read(bytes);
            return new String(bytes, "UTF-8");
        } catch (final IOException err) {
            throw new JsLoadException(err, this.filename);
        }
    }
}
