package com.example.order.server.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface StreamClient {
    String INPUT="myMessage-input";
    String OUTPUT="myMessage-output";

    String CALLBACK_INPUT="callback-input";
    String CALLBACK_OUTPUT="callback-output";

    @Input(INPUT)
    SubscribableChannel input();

    @Output(OUTPUT)
    MessageChannel output();

    @Input(CALLBACK_INPUT)
    SubscribableChannel callback_input();

    @Output(CALLBACK_OUTPUT)
    MessageChannel callback_output();
}
