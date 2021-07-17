# Payment Reminder
## Frameworks
* Spring Boot (2.5.1)
* Quartz Scheduler (2.3.2)
## Servers
* Embeded Tomcat (9.0.46)
* Apache ActiveMQ Artemis (2.17.0)
## Requirements
As the client needs to be reminded of collecting payments monthly or every other month, we decided to use the _Quartz Scheduler_ framework to do the cron job scheduling, which will send a _PaymentReminder_ message to _Artemis_ when fired or triggered. 
Once our client is logged-in to our system, the system will act as a queue consumer to consume the unread _PaymentReminder_ message.
