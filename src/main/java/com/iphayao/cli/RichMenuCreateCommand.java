package com.iphayao.cli;

import com.iphayao.cli.arguments.PayloadProvider;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.richmenu.RichMenu;
import com.linecorp.bot.model.richmenu.RichMenuIdResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import static com.google.common.util.concurrent.Futures.getUnchecked;

@Slf4j
@Component
@ConditionalOnProperty(name = "command", havingValue = "richmenu-create")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RichMenuCreateCommand implements CliCommand {
    private LineMessagingClient lineMessagingClient;
    private PayloadProvider payloadProvider;

    @Override
    public void execute() throws Exception {
        RichMenu richMenu = payloadProvider.read(RichMenu.class);
        RichMenuIdResponse richMenuResponse = getUnchecked(lineMessagingClient.createRichMenu(richMenu));
        log.info("Successfully finished. {}", richMenuResponse);
    }
}
