FROM openjdk:17-alpine AS jre-build
RUN jlink --verbose \
--compress 2 \
--strip-java-debug-attributes \
--no-header-files \
--no-man-pages \
--output jre \
--add-modules java.se
FROM alpine:latest
COPY --from=jre-build jre jre