package com.mondiamedia.cleanup;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.shell.support.logging.HandlerUtils;

import com.mondiamedia.cleanup.shell.BootShim;

@SpringBootApplication
public class MultiDsApplication {


    public static void main(final String[] args) {
        final ConfigurableApplicationContext run = SpringApplication.run(MultiDsApplication.class, args);

        try {
            final BootShim bs = new BootShim(args, run);
            bs.run();
        } catch (final RuntimeException e) {
            throw e;
        } finally {
            HandlerUtils.flushAllHandlers(Logger.getLogger(""));
        }
    }

}
