## Grading criteria

- 0 point - The work is insufficient
- 0.1 point - The work is done
- 0.2 point - The work is well done (without the need of being perfect)

Maximum grade: 25 points \* 0.2 + 1 = 6

> [!IMPORTANT]
>
> While the grading criteria might not be as detailed as in the previous
> practical works for each section, you **must** continue to apply all the good
> practices you have learned so far.
>
> If elements that are supposed to be acquired through the course or previous
> practical works are omitted, forgotten or poorly implemented, we might
> penalize you.
>
> Remember the UNIX philosophy and the KISS principle: _Keep it simple, silly!_

### Category 1 - Git, GitHub and Markdown

| #   | Criterion                                                                                                                                                                          | Points |
| --- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -----: |
| 1   | The README is well structured and explains the purpose of your network application so new users can understand it                                                                  |    0.2 |
| 2   | The README explains how to use your network application with examples and outputs so a new user/developer can understand your network application without having to run it locally |    0.2 |
| 3   | The README describes explicit commands to clone and build your network application with Git and Maven so new developers can start and develop your project on their own computer   |    0.2 |

### Category 2 - Java, IntelliJ IDEA and Maven

| #   | Criterion                                                                                                                                       | Points |
| --- | ----------------------------------------------------------------------------------------------------------------------------------------------- | -----: |
| 4   | The codebase is well structured, easy to access, easy to understand and is documented so it is easier for new comers to understand the codebase |    0.2 |

### Category 3 - Docker and Docker Compose

| #   | Criterion                                                                                                                                               | Points |
| --- | ------------------------------------------------------------------------------------------------------------------------------------------------------- | -----: |
| 5   | The network application is packaged and published to GitHub Container Registry with Docker so other people can use your network application with Docker |    0.2 |
| 6   | The README describes explicit commands to build and publish your network application with Docker                                                        |    0.2 |
| 7   | The README explains how to use your network application with Docker (`docker run` is enough for this practical work, no need to use Docker Compose)     |    0.2 |

### Category 4 - Define an application protocol

| #   | Criterion                                                                                                      | Points |
| --- | -------------------------------------------------------------------------------------------------------------- | -----: |
| 8   | The repository contains the application protocol that describes your network application                       |    0.2 |
| 9   | The application protocol defines the overview of the network application                                       |    0.2 |
| 10  | The application protocol defines the transport protocol(s) the network application uses                        |    0.2 |
| 11  | The application protocol defines the available messages/actions/commands for the client/server to communicate  |    0.2 |
| 12  | The application protocol defines the success/error codes and their explanations                                |    0.2 |
| 13  | The application protocol is described using successful and unsuccessful examples with one or multiple diagrams |    0.2 |

### Category 5 - Java TCP/UDP programming

| #   | Criterion                                                                                                                | Points |
| --- | ------------------------------------------------------------------------------------------------------------------------ | -----: |
| 14  | The server starts/listens on the defined port(s) by default (you must be able to change it if needed)                    |    0.2 |
| 15  | The client accesses the server on a given host (you must be able to change it)                                           |    0.2 |
| 16  | The client accesses the server on the defined port(s) by default (you must be able to change it if needed)               |    0.2 |
| 17  | The client and server exchange messages/actions/commands to interact with each other                                     |    0.2 |
| 18  | The client and server correctly process the messages/actions/commands and with their edge-cases in case a problem occurs |    0.2 |
| 19  | The client and server are compatible across operating systems/languages                                                  |    0.2 |
| 20  | The client and server correctly manage resources in case a problem occurs                                                |    0.2 |

### Category 6 - Java network concurrency

| #   | Criterion                                                                                | Points |
| --- | ---------------------------------------------------------------------------------------- | -----: |
| 21  | The network application accepts connections from multiple clients at the same time       |    0.2 |
| 22  | The data structures used in the network application are resilient to concurrent accesses |    0.2 |

### Category 7 - Presentation and questions

| #   | Criterion                                                                                                                                                                | Points |
| --- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | -----: |
| 23  | The application is presented and a demo is made as you would do it to a colleague/another team/boss/client/investor so they can understand what you created, why and how |    0.2 |
| 24  | The presentation is clear and well prepared - everyone speaks during the presentation                                                                                    |    0.2 |
| 25  | The answers to the questions are correct                                                                                                                                 |    0.2 |

## Constraints

- The application must be written in Java, compatible with Java 21
- The application must be built using Maven with the `maven-shade-plugin` plugin
- The application must use the picocli dependency
- You can only use the Java classes seen in the course
- Your application must be slightly more complex and slightly different than the
  examples presented during the course (we emphasize the word **slightly**, no
  need to shoot for the moon!)