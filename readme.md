
# Start up sample config server
1. update search location based on the server's location in your file system
    ```yml
      cloud:
        config:
            server:
                native:
                    searchLocations: file:///D:/Projects D/test-spring-boot-cloud-config/configs/${app-env}
    ```
1. run service (default port 8100)
1. check server start up: 
    ```
    > curl localhost:8100
    hello world
    ```

# Start up sample config client
1. run service (default port 8101)
1. check client start up:
    ```
    > curl localhost:8101
    hello world from client
    ```

# Verify config loaded successfully

1. Default setup case:
    expect API response "default", because client's spring.cloud.config.enabled=false
    ```properties
    server.port=8101
    spring.cloud.config.enabled=false
    #spring.config.import=configserver:http://localhost:8100
    #spring.application.name=test-spring-boot-cloud-config-client
    loaded-config=default
    ```
    ```
    > curl localhost:8101/configs
    default
    ```

1. DEV case:
    1. update client's properties to following:
        ```properties
        server.port=8101
        spring.config.import=configserver:http://localhost:8100
        spring.application.name=test-spring-boot-cloud-config-client
        loaded-config=default
        ```
    1. restart client
    1. expect API response "cloud-dev-config"
        ```
        > curl localhost:8101/configs
        cloud-dev-config
        ```

1. INT case:
    1. update server's app-env config to int
        ```
        app-env: int
        ```
    1. restart server
    1. restart client
    1. expect API response "cloud-int-config"
        ```
        > curl localhost:8101/configs
        cloud-int-config
        ```
