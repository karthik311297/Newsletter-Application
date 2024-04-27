1. podman pull gcr.io/google.com/cloudsdktool/google-cloud-cli:emulators

2. podman run --rm -p 8085:8085 gcr.io/google.com/cloudsdktool/google-cloud-cli:emulators /bin/bash -c "gcloud beta emulators pubsub start --project=test-project --host-port='0.0.0.0:8085'"

3. curl -X GET "http://localhost:8085/v1/projects/test-project/topics"

4. curl -X GET "http://localhost:8085/v1/projects/test-project/subscriptions"

5. JAVA_HOME=/d/Java_jdk_all_versions/zulu17.48.15-ca-jdk17.0.10-win_x64/zulu17.48.15-ca-jdk17.0.10-win_x64 mvn clean install -DskipTests

6. Refer this link for spring boot and google smtp integration - https://www.geeksforgeeks.org/spring-boot-sending-email-via-smtp/