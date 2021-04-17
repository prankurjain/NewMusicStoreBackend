FROM java:8

EXPOSE 8080

Add target/MusicStoreApp.jar music-store-app.jar

ENTRYPOINT ["java","-jar","music-store-app.jar"]