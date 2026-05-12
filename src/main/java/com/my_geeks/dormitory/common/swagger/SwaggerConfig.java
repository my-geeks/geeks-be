package com.my_geeks.dormitory.common.swagger;

import com.my_geeks.dormitory.common.exception.errorcode.ErrorCode;
import com.my_geeks.dormitory.common.response.BaseResponse;
import com.my_geeks.dormitory.common.swagger.annotation.ApiErrorResponses;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Info info = new Info()
                .title("geeks API")
                .description("geeks API 입니다.")
                .version("1.0.0");

        Components components = new Components();

        // TODO: 인증 시스템 도입 시 주석 해제
        // String jwt = "Bearer Token";
        // SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwt);
        // components.addSecuritySchemes(jwt, new SecurityScheme()
        //         .type(SecurityScheme.Type.APIKEY)
        //         .in(SecurityScheme.In.HEADER)
        //         .name("Authorization"));

        return new OpenAPI()
                .info(info)
                .components(components);
        // .addSecurityItem(securityRequirement);  // 인증 도입 시 주석 해제
    }

    @Bean
    public OperationCustomizer operationCustomizer() {
        return (operation, handlerMethod) -> {
            ApiErrorResponses apiErrorResponses = handlerMethod.getMethodAnnotation(ApiErrorResponses.class);
            if (apiErrorResponses != null) {
                generateErrorResponseExample(operation, apiErrorResponses.value());
            }
            addResponseBodyWrapperSchema(operation);
            return operation;
        };
    }

    private void addResponseBodyWrapperSchema(Operation operation) {
        ApiResponses responses = operation.getResponses();
        responses.forEach((statusCode, apiResponse) -> {
            if (apiResponse == null || apiResponse.getContent() == null) {
                return;
            }
            Content content = apiResponse.getContent();
            content.keySet().forEach(mediaTypeKey -> {
                MediaType mediaType = content.get(mediaTypeKey);
                mediaType.schema(wrapSchema(Integer.parseInt(statusCode), mediaType.getSchema()));
            });
        });
    }

    private Schema<?> wrapSchema(int statusCode, Schema<?> originalSchema) {
        HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
        Schema<?> wrapperSchema = new Schema<>();
        wrapperSchema.addProperty("httpStatus", new Schema<>().type("string").example(httpStatus.name()));
        wrapperSchema.addProperty("data", originalSchema);
        wrapperSchema.addProperty("code", new Schema<>().type("string").nullable(true));
        wrapperSchema.addProperty("errorMessage", new Schema<>().type("string").nullable(true));
        return wrapperSchema;
    }

    private void generateErrorResponseExample(Operation operation, ErrorCode[] errorCodes) {
        ApiResponses responses = operation.getResponses();

        Map<Integer, List<ExampleHolder>> grouped = Arrays.stream(errorCodes)
                .map(errorCode -> ExampleHolder.builder()
                        .holder(getExample(errorCode))
                        .name(errorCode.name())
                        .code(errorCode.getHttpStatus().value())
                        .message(errorCode.getMessage())
                        .status(errorCode.getHttpStatus())
                        .build())
                .collect(Collectors.groupingBy(ExampleHolder::getCode));

        grouped.forEach((status, holders) -> {
            Content content = new Content();
            MediaType mediaType = new MediaType();
            ApiResponse apiResponse = new ApiResponse();

            holders.forEach(holder -> mediaType.addExamples(holder.getName(), holder.getHolder()));

            String description = holders.stream()
                    .map(ExampleHolder::getName)
                    .collect(Collectors.joining(", "));
            apiResponse.setDescription(description);

            content.addMediaType("application/json", mediaType);
            apiResponse.setContent(content);
            responses.addApiResponse(String.valueOf(status), apiResponse);
        });
    }

    private Example getExample(ErrorCode errorCode) {
        Example example = new Example();
        example.setValue(ErrorDto.from(errorCode));
        return example;
    }
}
