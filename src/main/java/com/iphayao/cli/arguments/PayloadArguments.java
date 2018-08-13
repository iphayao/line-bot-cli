package com.iphayao.cli.arguments;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Data
@Lazy
@Component
@ConfigurationProperties
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PayloadArguments {
    String json;
    String data;
    String yaml;
}
