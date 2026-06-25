
# Notification System Design

## Stage 1 – Functional Requirements

### Objective

The objective of this system is to provide students with important notifications related to placements, examination results, and college events. Notifications should be displayed based on their importance so that students never miss critical updates.

### Functional Requirements

* Receive notifications from multiple services.
* Categorize notifications into:

  * Placement
  * Result
  * Event
* Display only unread notifications.
* Prioritize notifications based on category.
* If two notifications have the same priority, display the most recent one first.
* Return only the top 10 notifications.

---

## Stage 2 – Non-Functional Requirements

The system should satisfy the following non-functional requirements:

* High Availability
* Low Response Time
* Scalability
* Reliability
* Secure API Communication
* Easy Maintainability



## Stage 3 – High-Level Design

### Components

* Student Application
* Notification Service
* Placement Service
* Result Service
* Event Service
* Notification Database

### Architecture

```text
                     Student Application
                             |
                             |
                    Notification Service
                   /          |          \
                  /           |           \
        Placement Service  Result Service  Event Service
                  \           |           /
                   \          |          /
                    Notification Database
```

### Workflow

1. Placement, Result and Event services generate notifications.
2. Notifications are stored in the Notification Database.
3. The Notification Service fetches unread notifications.
4. Notifications are sorted according to priority.
5. The top 10 notifications are returned to the student.



## Stage 4 – Database Design

### Notification Table

| Field     | Type     | Description                    |
| --------- | -------- | ------------------------------ |
| ID        | UUID     | Unique notification identifier |
| UserID    | UUID     | Student identifier             |
| Type      | String   | Placement, Result or Event     |
| Message   | String   | Notification content           |
| Timestamp | DateTime | Notification creation time     |
| IsRead    | Boolean  | Read/Unread status             |

### Why this Design?

* UUID ensures every notification is unique.
* Timestamp helps display recent notifications.
* IsRead allows filtering unread notifications.
* UserID associates notifications with a specific student.



## Stage 5 – Prioritization Algorithm

Each notification type is assigned a priority.

| Notification Type | Priority |
| ----------------- | -------- |
| Placement         | 3        |
| Result            | 2        |
| Event             | 1        |

### Algorithm

1. Fetch all unread notifications.
2. Assign a priority based on notification type.
3. Sort notifications by:

   * Higher priority first.
   * If priorities are equal, newer notifications first.
4. Return the first 10 notifications.

### Example

Input:


Placement 08:30 AM
Placement 08:00 AM
Result 09:00 AM
Event 10:00 AM


Output:


Placement 08:30 AM
Placement 08:00 AM
Result 09:00 AM
Event 10:00 AM


### Time Complexity

* Fetch Notifications: **O(n)**
* Sorting: **O(n log n)**
* Return Top 10: **O(10)**

Overall Time Complexity:


O(n log n)




## Scalability

For large-scale deployments:

* Use a Priority Queue (Max Heap) for efficient prioritization.
* Cache frequently accessed notifications using Redis.
* Partition the database based on User ID.
* Use Kafka or RabbitMQ for asynchronous notification processing.
* Deploy multiple Notification Service instances behind a Load Balancer.



## Security

* All APIs are protected using Bearer Token authentication.
* Every request is validated before processing.
* Sensitive credentials such as Client ID and Client Secret should be stored securely using configuration files or environment variables.
* HTTPS should be used for secure communication.



## Advantages

* Simple and easy to maintain.
* Fast notification retrieval.
* Displays important notifications first.
* Easily scalable.
* Reliable and secure.
* Easy to extend by adding new notification categories.



## Conclusion

The proposed notification system efficiently manages student notifications by prioritizing important updates and displaying the most relevant notifications first. The design is scalable, secure, reliable, and suitable for handling a large number of users while maintaining high performance.
