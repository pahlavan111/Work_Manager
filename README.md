# Work_Manager

What is workmanager?
- The Workmanager api makes it easy to spesify deferrable, asynchronous tasks and when they shoud run.
- These APIs let you create a task and hand it off to workmanager to run immediately or at an appropriate time.

Why workmanager?
- workmanager chooses the appropriate way to run your task based on such factor as the device API level and the app state.
- it provides us to clean interface, so no complex coding thing.
- it guarantees the execution.

--------------------------------
The Classes
- Worker : The work needed to be done is defined here.
- WorkRequest : it define a work, like which worker class is going to be executed.
                The workRequest is an abstract class so we will use the direct subclasses,
                OneTimeWorkRequst or PeriodicWorkRequest.
- WorkManager : It enqueues and manages the work request.
- WorkInfo : It cantains information about the work.
