package com.iphayao.cli;

import com.iphayao.cli.arguments.Arguments;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.richmenu.RichMenuResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.util.concurrent.Futures.getUnchecked;

@Slf4j
@Component
@ConditionalOnProperty(name = "command", havingValue = "richmenu-get")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RichMenuGetCommand implements CliCommand {
    private LineMessagingClient lineMessagingClient;
    private Arguments arguments;

    @Override
    public void execute() throws Exception {
        final String richMenuId = checkNotNull(arguments.getRichMenuId(), "--rich-menu-id= is not set.");
        final RichMenuResponse richMenuResponse = getUnchecked(lineMessagingClient.getRichMenu(richMenuId));
        log.info("Successfully finished.");
        log.info("response = {}", richMenuResponse);
    }
}
