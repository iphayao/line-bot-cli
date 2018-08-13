package com.iphayao.cli;

import com.iphayao.cli.arguments.Arguments;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.richmenu.RichMenuIdResponse;
import com.linecorp.bot.model.richmenu.RichMenuListResponse;
import com.linecorp.bot.model.richmenu.RichMenuResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.google.common.util.concurrent.Futures.getUnchecked;

@Slf4j
@Component
@ConditionalOnProperty(name = "command", havingValue = "richmenu-list")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RichMenuListCommand implements CliCommand {
    private LineMessagingClient lineMessagingClient;

    @Override
    public void execute() throws Exception {
        final RichMenuListResponse richMenuListResponse = getUnchecked(lineMessagingClient.getRichMenuList());
        log.info("Successfully finished");

        final List<RichMenuResponse> richMenus = richMenuListResponse.getRichMenus();
        log.info("You have {} RichMenus", richMenus.size());
        richMenus.forEach(richMenuResponse -> {
            log.info("{}", richMenuResponse);
        });

    }
}
