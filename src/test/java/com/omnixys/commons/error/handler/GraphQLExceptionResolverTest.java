package com.omnixys.commons.error.handler;

import com.omnixys.commons.error.BaseOmnixysException;
import com.omnixys.commons.error.ErrorCode;
import graphql.GraphQLError;
import graphql.execution.ExecutionStepInfo;
import graphql.execution.MergedField;
import graphql.execution.ResultPath;
import graphql.language.Field;
import graphql.schema.DataFetchingEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GraphQLExceptionResolverTest {

    private GraphQLExceptionResolver resolver;
    private DataFetchingEnvironment env;

    @BeforeEach
    void setUp() {
        resolver = new GraphQLExceptionResolver();
        Field field = new Field("testField");
        MergedField mergedField = MergedField.newMergedField(field).build();
        env = new DataFetchingEnvironment() {
            @Override public MergedField getMergedField() { return mergedField; }
            @Override public ExecutionStepInfo getExecutionStepInfo() {
                return ExecutionStepInfo.newExecutionStepInfo()
                        .path(ResultPath.rootPath())
                        .type(graphql.Scalars.GraphQLString)
                        .build();
            }
            @Override public <T> T getSource() { return null; }
            @Override public Map<String, Object> getArguments() { return Map.of(); }
            @Override public boolean containsArgument(String name) { return false; }
            @Override public <T> T getArgument(String name) { return null; }
            @Override public <T> T getArgumentOrDefault(String name, T defaultValue) { return defaultValue; }
            @Override public <T> T getContext() { return null; }
            @Override public graphql.GraphQLContext getGraphQlContext() { return null; }
            @Override public <T> T getLocalContext() { return null; }
            @Override public <T> T getRoot() { return null; }
            @Override public graphql.schema.GraphQLFieldDefinition getFieldDefinition() { return null; }
            @Override public java.util.List<Field> getFields() { return java.util.List.of(); }
            @Override public Field getField() { return field; }
            @Override public graphql.schema.GraphQLOutputType getFieldType() { return null; }
            @Override public graphql.schema.GraphQLType getParentType() { return null; }
            @Override public graphql.schema.GraphQLSchema getGraphQLSchema() { return null; }
            @Override public Map<String, graphql.language.FragmentDefinition> getFragmentsByName() { return Map.of(); }
            @Override public graphql.execution.ExecutionId getExecutionId() { return null; }
            @Override public graphql.schema.DataFetchingFieldSelectionSet getSelectionSet() { return null; }
            @Override public graphql.execution.directives.QueryDirectives getQueryDirectives() { return null; }
            @Override public <K, V> org.dataloader.DataLoader<K, V> getDataLoader(String name) { return null; }
            @Override public org.dataloader.DataLoaderRegistry getDataLoaderRegistry() { return null; }
            @Override public java.util.Locale getLocale() { return null; }
            @Override public graphql.language.OperationDefinition getOperationDefinition() { return null; }
            @Override public graphql.language.Document getDocument() { return null; }
            @Override public Map<String, Object> getVariables() { return Map.of(); }
        };
    }

    @Test
    void shouldReturnNullForNonOmnixysException() {
        GraphQLError result = resolver.resolveToSingleError(new RuntimeException("generic"), env);
        assertNull(result);
    }

    @Test
    void shouldIncludeCodeExtension() {
        var ex = new BaseOmnixysException(ErrorCode.USER_NOT_FOUND, "User not found");
        GraphQLError error = resolver.resolveToSingleError(ex, env);

        assertNotNull(error);
        Map<String, Object> extensions = error.getExtensions();
        assertNotNull(extensions);
        assertEquals("USER_NOT_FOUND", extensions.get("code"));
    }

    @Test
    void shouldIncludeRequestIdAndCorrelationId() {
        var ex = new BaseOmnixysException(ErrorCode.VALIDATION_ERROR, "Invalid");
        GraphQLError error = resolver.resolveToSingleError(ex, env);

        assertNotNull(error);
        Map<String, Object> extensions = error.getExtensions();
        assertEquals("unscoped", extensions.get("requestId"));
        assertEquals("unscoped", extensions.get("correlationId"));
    }

    @Test
    void shouldSetMessageFromException() {
        var ex = new BaseOmnixysException(ErrorCode.FORBIDDEN, "Access denied");
        GraphQLError error = resolver.resolveToSingleError(ex, env);

        assertNotNull(error);
        assertEquals("Access denied", error.getMessage());
    }

    @Test
    void shouldIncludeTimestampInExtensions() {
        var ex = new BaseOmnixysException(ErrorCode.USER_NOT_FOUND, "User not found");
        GraphQLError error = resolver.resolveToSingleError(ex, env);

        assertNotNull(error);
        Map<String, Object> extensions = error.getExtensions();
        assertNotNull(extensions.get("timestamp"));
        assertInstanceOf(String.class, extensions.get("timestamp"));
    }

    @Test
    void shouldNotIncludeMetadataWhenAbsent() {
        var ex = new BaseOmnixysException(ErrorCode.SERVICE_UNAVAILABLE, "Down");
        GraphQLError error = resolver.resolveToSingleError(ex, env);

        assertNotNull(error);
        Map<String, Object> extensions = error.getExtensions();
        assertNull(extensions.get("metadata"));
    }
}
