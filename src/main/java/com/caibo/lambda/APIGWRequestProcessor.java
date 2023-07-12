package com.caibo.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class APIGWRequestProcessor
        implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        // TODO Auto-generated method stub
        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
        try {
            LambdaLogger logger = context.getLogger();
            logger.log("Input: " + input);

            responseEvent.setStatusCode(200);
            responseEvent.setBody("Hello World");
            // String response = "{\"statusCode\": 200,\"body\": \"hello world!\"}";
            logger.log("response: " + responseEvent.toString());

        } catch (Exception e) {
            // TODO: handle exception
        }

        return responseEvent;

    }

}
