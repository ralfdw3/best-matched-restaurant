# Best Matched Restaurant Finder

A Spring Boot application that helps users find the best-matched restaurants based on various criteria such as cuisine
type, customer rating, distance, and price.

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── io/challenge/bestmatched/
│   │       └── restaurants/
│   │           ├── config/
│   │           ├── controller/
│   │           ├── dto/
│   │           ├── infra/
│   │           ├── model/
│   │           ├── repository/
│   │           └── service/
│   └── resources/
│       ├── application.yml
│       ├── cuisines.csv
│       └── restaurants.csv
└── test/
    ├── java/
    │   └── io/challenge/bestmatched/
    │       └── restaurants/
    │           ├── config/
    │           ├── controller/
    │           ├── functionaltests/
    │           ├── service/
    │           └── util/
    └── resources/
        └── application-test.yml
```

## Running the Application

1. Clone the repository:

   ```bash
   git clone https://github.com/ralfdw3/best-matched-restaurant.git
   cd best-matched-restaurant
   ```

2. Run docker:

   ```bash
   docker compose up -d
   ```

3. Access the swagger:

   ```html
   https://localhost:8080/swagger-ui.html
   ```

The application will start on port 8080 by default.

## Testing the Application

### Unit Tests and Functional Tests

Run all unit tests:

```bash
./gradlew test
```

The functional tests use RestAssured to test the REST API endpoints and use the same CSV files as the main application.

## API Documentation

### Search Restaurants

**Endpoint:** `POST /v1/search/restaurants`

**Request Body:**

```json
{
  "restaurant": "Deliciousgenix",
  "customerRating": 4,
  "distance": 1,
  "price": 10,
  "cuisine": "Spanish"
}
```

All fields are optional. The search will return restaurants that match all provided criteria.

**Response:**

```json
[
  {
    "restaurant": "Deliciousgenix",
    "customerRating": 4,
    "distance": 1,
    "price": 10,
    "cuisine": "Spanish"
  }
]
```

### Validation Rules

- `customerRating`: Integer between 1 and 5
- `distance`: Integer between 1 and 10
- `price`: BigDecimal between 1 and 50
- `restaurant`: String (case-insensitive partial match)
- `cuisine`: String (case-insensitive partial match)

## Data Files

The application uses two CSV files for data:

1. `cuisines.csv`: Contains cuisine types
2. `restaurants.csv`: Contains restaurant information

These files are located in `src/main/resources/` and are automatically loaded when the application starts.

## Logging

The application uses SLF4J with Logback for logging. Log levels can be configured in `application.yml`:

```yaml
logging:
  level:
    org.springframework: INFO
    io.challenge.bestmatched: DEBUG
```

