# Scientific calculator

This is a simple Java application designed to perform basic arithmetic operations using the interpreter design pattern. It's a console-based program built with Java 17.

## Table of Contents

1. [Features](#features)
2. [Prerequisites](#prerequisites)
3. [Project Structure](#project-structure)
4. [Setup Instructions](#setup-instructions)
5. [Usage](#usage)
6. [Testing](#testing)
7. [Contributing](#contributing)
8. [License](#license)
9. [Acknowledgments](#acknowledgments)


## Features

- Basic arithmetic operations: addition, subtraction, multiplication, and division.
- User-friendly console interface.
- Lightweight and simple code structure.
- Comprehensive JUnit 5 tests.

## Prerequisites

- Java 17 (or a compatible JDK) installed.
- An IDE like IntelliJ IDEA (recommended) or any other Java-supported editor.
- Maven (for dependency management and building).

## Project Structure
- **`Main.java`**: The entry point of the application. Initializes and starts the `Calculator`.
- **`Calculator.java`**: Contains the core logic for performing calculations.
- **`Interpreter.java`**: The class is responsible for evaluating mathematical or
logical expressions provided as strings.
- **`Tokenizer.java`**: Defines the contract for tokenizing a mathematical or logical expression represented as a string into a list of string tokens.
- **`BaseTokenizer.java`**: The base implementation of the `Tokenizer` class.
- **`TokenParser.java`**: Responsible for parsing a list of tokens and constructing an abstract syntax tree.
- **`TokenParserFactory.java`**: Class is responsible for creating instances of the `TokenParser` class.
- **`ExpressionType.java`**: Enum representing different types of expressions used in an interpreter pattern.
- **`Expression.java`**: The Expression interface represents a component in the Interpreter design pattern.
- **`ExpressionFactory.java`**: Factory for creating specific expressions based on provided operator and operands.
- **`ExpressionFactoryHelper.java`**: Helper class for retrieving types of expressions available in `ExpressionFactoryStore`.
- **`ExpressionFactoryStore.java`**: The class is responsible for providing and managing various factories for creating mathematical expression objects compliant with the Interpreter design pattern.
- **`interpreter/expression/expressions/*`**: Different implementation of `Expression` representing mathematical and logical expressions in the Interpreter design pattern.

## Setup Instructions

1. Clone the repository: `git clone <repository_url>`
2. Open the project in your IDE.
3. Ensure Java 17 is set as the project SDK.
4. Build the project using Maven: `mvn clean install` or Gradle: `gradlew build`

## Usage

1. Run the application from your IDE or using the command line: `mvn exec:java`.
2. Follow the prompts in the console to perform calculations

## Testing

The project includes JUnit 5 tests to ensure the correctness of the `Calculator` class.
Run the tests using your IDE or via Maven: `mvn test`.

## Contributing

1. Fork the repository.
2. Create a feature branch: `git checkout -b <branch_name>`.
3. Commit your changes: `git commit -m "<commit_message>"`.
4. Push to the branch: `git push origin <branch_name>`.
5. Submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Developed by [Sebastian Kubalski](mailto:sebastian.kubalski@gmail.com)
- Built with IntelliJ IDEA.
