# GitHub Repos API (Quarkus)

REST API application fetching non-fork repositories of provided GitHub user and return list of their branches with SHA of last commit.

## Tech Stack

- Java 21
- Quarkus
- Quarkus REST Client
- Quarkus REST Jackson

## How to run
1. Clone repository: https://github.com/jania1857/github-api.git
2. Build and run application in dev mode:
```sh 
./mvnw compile quarkus:dev
```
3. Application is running on: 
```
http://localhost:8080
```

## API Endpoints

### Fetching user repositories

#### GET ```/api/v1/github/{username}```
Gets list of user's repositories, filtering forks.
#### Example execution: 
```sh
curl -X GET http://localhost:8080/api/v1/github/octocat
```
#### Expected response: 
```json
[
    {
      "repositoryName": "Hello World",
      "ownerLogin": "octocat",
      "branches": [
        {
          "name": "main",
          "lastCommitSha": "b33a9c7c02ad93f621fa38f0e9fc9e867e12fa0e"
        }
      ]
    }
]
```

#### Error handling

- Not existing user -> ```404 NOT FOUND```
```json
{
  "status": 404,
  "message": "User with provided login not found."
}
```

## Author
- Hubert Janiuk
- Email: hubert.janiuk.2ti@gmail.com
- GitHub: https://github.com/jania1857
