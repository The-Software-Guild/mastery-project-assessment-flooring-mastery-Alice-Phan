# Flooring Mastery Project

## Architectural

1. Use N-tier development and MVC principles in structuring code to handle products, taxes, and orders appropriately.
2. Use unit tests and integration tests to ensure that the application's data layers and business logic (service layer) code is covered.

## Rules

For an enterprise architecture, the code must have layers:

- The `model` package may only contain classes that have data members (properties).
- The `dao` package contains classes that are responsible for persisting data.
- The `controller` package contains classes that orchestrate the program.
- The `view` package contains classes that interact with the user.
- The `service` package contains the service layer components.
- The `UserIO` class (along with the view component) will handle all console IO for the user.
