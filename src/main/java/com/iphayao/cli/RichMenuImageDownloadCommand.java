package com.iphayao.cli;

import com.iphayao.cli.arguments.Arguments;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.client.MessageContentResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.FileOutputStream;
import java.io.OutputStream;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.util.concurrent.Futures.getUnchecked;

@Slf4j
@Component
@ConditionalOnProperty(name = "command", havingValue = "richmenu-download")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RichMenuImageDownloadCommand implements CliCommand {
    private LineMessagingClient lineMessagingClient;
    private Arguments arguments;

    @Override
    public void execute() throws Exception {
        final String richMenuId = checkNotNull(arguments.getRichMenuId(), "--rich-menu-id= is not set");
        final String out = checkNotNull(arguments.getOut(), "--out= is not set");
        final MessageContentResponse messageContentResponse = getUnchecked(lineMessagingClient.getRichMenuImage(richMenuId));

        log.info("Request successfully finished.");
        log.info("response: {}", messageContentResponse);

        try(OutputStream os = new FileOutputStream(out)) {
            StreamUtils.copy(messageContentResponse.getStream(), os);
        }

        log.info("Successfully finished.");
        log.info("output: {}", out);

    }
}
