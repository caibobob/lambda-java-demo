package com.caibo.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;

public class S3EventProcessor implements RequestHandler<S3Event, String> {

    @Override
    public String handleRequest(S3Event event, Context context) {
        String response = new String("200 OK");
        try {
            LambdaLogger logger = context.getLogger();
            // TODO Auto-generated method stub
            logger.log("S3EventProcessor.handleRequest start");

            logger.log("Received event getBucket: " + event.getRecords().get(0).getS3().getBucket().getName());

            logger.log("Received event getKey: " + event.getRecords().get(0).getS3().getObject().getKey());

            logger.log("S3EventProcessor.handleRequest end");

            throw new UnsupportedOperationException("Unimplemented method 'handleRequest'");
        } catch (Exception e) {
        }
        return response;
    }

}
