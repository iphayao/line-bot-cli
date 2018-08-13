package com.iphayao.cli;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;

@Slf4j
@SpringBootApplication
public class Application {
    public static void main(final String... args) {
        try(ConfigurableApplicationContext context = SpringApplication.run(Application.class, args)) {
            log.info("Arguments {}", Arrays.asList(args));

            try {
                context.getBean(Application.class).run(context);
            } catch (Exception e) {
                log.error("Exception in command executions", e);
            }
        }
    }

    private void run(ConfigurableApplicationContext context) throws Exception {
        final Map<String, CliCommand> commandMap = context.getBeansOfType(CliCommand.class);

        log.info(commandMap.toString());

        if(commandMap.isEmpty()) {
            log.warn("No command resolved. Available command are follow");
            printSupportCommand();
            return;
        }

        if(commandMap.size() > 1) {
            throw new RuntimeException("Multiple commands matched, maybe bug");
        }

        CliCommand command = commandMap.values().iterator().next();

        log.info("\"--command\" resolved to > {}", command.getClass());

        command.execute();
    }

    private void printSupportCommand() {
        for(Class<?> clazz: new Class<?>[] {
                RichMenuCreateCommand.class,
                RichMenuGetCommand.class,
                RichMenuListCommand.class,
                RichMenuDeleteCommand.class,
                RichMenuImageUploadCommand.class,
                RichMenuImageDownloadCommand.class,
                RichMenuLinkRichMenuIdToUserCommand.class,
                RichMenuUnlinkRichMenuIdFromUserCommand.class,
                RichMenuGetRichMenuIdOfUserCommand.class
        });
    }
}
