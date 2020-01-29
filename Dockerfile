FROM openjdk:8-jdk
        ADD target/universal/stage stage
        CMD ["stage/bin/demoprojectnew", "-Dplay.http.secret.key=abcdefghijk"]