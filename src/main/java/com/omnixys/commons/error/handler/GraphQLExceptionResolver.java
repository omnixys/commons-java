package com.omnixys.commons.error.handler;

import com.omnixys.commons.error.BaseOmnixysException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class GraphQLExceptionResolver extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof BaseOmnixysException omnixysEx) {
            Map<String, Object> extensions = new LinkedHashMap<>();
            extensions.put("code", omnixysEx.getErrorCode().name());
            extensions.put("requestId", omnixysEx.getRequestId());
            extensions.put("correlationId", omnixysEx.getCorrelationId());
            extensions.put("timestamp", Instant.now().toString());
            omnixysEx.getTraceId().ifPresent(t -> extensions.put("traceId", t));
            omnixysEx.getActorId().ifPresent(a -> extensions.put("actorId", a));
            omnixysEx.getTenantId().ifPresent(t -> extensions.put("tenantId", t));

            Map<String, Object> metadata = omnixysEx.getMetadata();
            if (metadata != null && !metadata.isEmpty()) {
                extensions.put("metadata", metadata);
            }

            return GraphqlErrorBuilder.newError(env)
                    .message(omnixysEx.getMessage())
                    .extensions(extensions)
                    .build();
        }
        return null;
    }
}
