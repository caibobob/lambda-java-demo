package com.caibo.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.ApplicationLoadBalancerRequestEvent;
import com.amazonaws.services.lambda.runtime.events.ApplicationLoadBalancerResponseEvent;

public class AlbRequestProcessor
        implements RequestHandler<ApplicationLoadBalancerRequestEvent, ApplicationLoadBalancerResponseEvent> {

    @Override
    public ApplicationLoadBalancerResponseEvent handleRequest(ApplicationLoadBalancerRequestEvent input,
            Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Input: " + input);
        ApplicationLoadBalancerResponseEvent responseEvent = new ApplicationLoadBalancerResponseEvent();
        responseEvent.setStatusCode(200);
        responseEvent.setBody("Hello World");
        // String response = "{\"statusCode\": 200,\"body\": \"hello world!\"}";
        logger.log("response: " + responseEvent.toString());
        return responseEvent;
    }

    public static void main(String[] args) throws Exception {
        AlbRequestProcessor albRequestProcessor = new AlbRequestProcessor();
        ApplicationLoadBalancerRequestEvent event = new ApplicationLoadBalancerRequestEvent();
        event.setBody("Hello World");
        albRequestProcessor.handleRequest(event, null);
    }
}
