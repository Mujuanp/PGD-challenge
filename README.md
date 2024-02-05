<h1 align="center">Welcome to Payroll provider 👋</h1>
<p>
  <img alt="Version" src="https://img.shields.io/badge/version-0.1.0-blue.svg?cacheSeconds=2592000" />
  <a href="https://unlicense.org" target="_blank">
    <img alt="License: unlicense" src="https://img.shields.io/badge/License-unlicense-yellow.svg" />
  </a>
</p>

> A PGD Challenge.

## Relevant technologies and tools
### 🤡 [Mockito](https://site.mockito.org/)

> Version 5.10.0
### 🧪 [Junit5-Jupiter](https://junit.org/junit5/)

> Version 5.9.1
### 📦️️ [Gradle](https://gradle.org/)

> Version 8.0

## Install

```sh
./gradlew clean build
```

## Run tests

```sh
./gradlew clean build test
```
You can find the test results at build/reports/tests/index.html



## About the challenge
I think this challenge was about ask for information instead of just code. Sadly, there was no time for that. However, here are the questions I would have asked:

- Is writing the class names in Spanish a must?
- What exactly does "external dependency" mean, is this a multi-layer or multi-tier architectural style?
- What about restrictions on employee information (ID, name, MonthlyAmount). For instance, all of them are non-nullable values?
- What does an "abstract" payroll mean? This, to know if it is an abstract class or interface. 
- What does it mean to cover all scenarios? all possible scenarios? best-case scenarios? worst-case scenarios? average scenarios?  Here I think our best option is to know the constraints and plan the tests. Then, coding.
- The included scenarios are: 
  - "Negative monthly amount" this is about any employee or the sum of all of them?
  - "ID is 0" It means 0 is an invalid ID? would I throw an exception in this case?
  - "Name is empty".  Same question, is it necessary to throw an exception or just ignore it?

- Finally, what about architectural style? there is no enough information to decide witch is the right style for this approach.

## Assumptions
- For the "Negative monthly amount", "ID is 0" and "Name is empty" scenarios I have assumed they are exception scenarios.
- I have assumed "Negative monthly amount" refers to any employee, and it is an exception scenario.
- I have assumed 0 is an invalid ID and exception scenario.
- I have assumed any name empty or null is an exception scenario.
- I have assumed this is a multi-layer and not a multi-tier style.
- I have assumed the total amount could be longer than a max flat (Ex, two employees have float max float as MonthlyAmount).
- I have assumed PayrollMemberProvider is an interface.
- I have assumed it is not necessary to use frameworks like Spring or Micronaut. 

## How can exist different implementations of PayrollMemberProvider without modify code?
- You can turn this provider into a client and store the route in a "Parameter Store" (e.g. AWS Parameter Store) and then simply override the parameter, without modifying the code, to another external implementation.
- You can apply the Decorator pattern to "PayrollMemberProvider" in order to add behavior dynamically and combine it with the Strategy pattern to select an implementation. Finally, implement a solution which read external "rules" in order to combine your implementations and create new ones.




## Author

👤 **Juan Guerrero**

* Github: [@mujuanp](https://github.com/mujuanp)

## Show your support

Give a ⭐️ if this project helped you!

## 📝 License

Copyright © 2024 [Juan Guerrero](https://github.com/mujuanp). <br/>
This project is [unlicense](https://unlicense.org) licensed.

***
_This README was generated with ❤️ by [readme-md-generator](https://github.com/kefranabg/readme-md-generator)_