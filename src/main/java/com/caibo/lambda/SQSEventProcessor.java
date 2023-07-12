package com.caibo.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;;

public class SQSEventProcessor implements RequestHandler<SQSEvent, Void> {
    private int i = 0;

    @Override
    public Void handleRequest(SQSEvent input, Context context) {
        // TODO Auto-generated method stub
        LambdaLogger logger = context.getLogger();
        logger.log("starting in handleRequest");
        if (input == null || input.getRecords() == null || input.getRecords().isEmpty() == true) {
            logger.log("input is null");
            return null;
        }
        try {
            for (SQSMessage msg : input.getRecords()) {

                logger.log("getBody=" + msg.getBody());
                logger.log("getMessageId=" + msg.getMessageId());
                logger.log(msg.getReceiptHandle());
                logger.log("i=" + i++);
            }
            System.out.println("ending in handleRequest");
        } catch (Exception e) {

            logger.log(e.toString());
            // TODO: handle exception
        }

        return null;

    }
}