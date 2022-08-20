## Start server

```shell
./gradlew bootRun
```

### H2 Console

http://localhost:8080/h2-console
<br/>
username: sa
<br/>
password: <empty>

## Example cURL

```shell
curl --location --request GET 'http://localhost:8080/businesses/locations?latitude=10.774180595634656&longitude=106.70411351828957&radius=3000'
```