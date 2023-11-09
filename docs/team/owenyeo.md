---
layout: page
title: Owen Yeo's Project Portfolio Page
---

## Project: TimetaBRO

### Overview

TimetaBRO, your ultimate companion for managing your university life, social interactions, and schedules, made by and made for NUS students. It allows you to save you and your friends' schedules,
set reminders for important events, and check when you and your friends have shared free time!

### Summary of Contributions

**Code contributed:** 
[RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=owenyeo&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22)

**Enhancements implemented:**
* Implemented the User, UserData, and ReadOnlyUserData to save user details and schedule.
    * What is does: Allows user to save their details, such as phone number and email, and schedule.
    * Justification: This feature allows users to save their schedules and view it on the UI. It also allows for comparisons between schedules to find free times between the user and their friends.
    * Highlights: This required knowledge about storage, and was the first thing I implemented, and thus was difficult. However, once I understood how storage worked, the process became a lot faster.
* Implemented the EditUserCommand to allow for editting of user details.
    * What is does: Allows user to edit their details after adding them.
    * Justification: This feature allows users to edit their details saved on the app, giving them flexibility in the event of wrong inputs.
    * Highlights: This command was relatively easy to implement as the tutorial we did was to implement a command. Had to create a EditUserDescriptor class as User was a different object.
* Implemented UserDataStorage and updated Storage, StorageManager to support it.
    * What is does: Allows user to store their details in a Json file (userdata.json).
    * Justification: This feature allows users to save their details on the hard drive in a Json File. Users would not need to re-key their details and schedules into the app every time they start up.
    * Highlights: This feature was tough to implement as I had to understand how JSON files worked, and how to convert models into Json Files. It required an thorough research into what Jackson files are, and learning how AddressBook did it through code tracing.
* Created the follow Json related classes:
    * JsonAdaptedCca
    * JsonAdaptedDatedEvent
    * JsonAdaptedModule
    * JsonAdaptedSchedule
    * JsonAdaptedUser
    * JsonUserDataStorage
    * JsonSerializableUserData.java
* Wrote testcases for storage
* Changed Add and Edit to only require names


**Contributions to the User Guide (UG):**
* Wrote the introduction
* Wrote the About section
* Wrote the Quickstart

**Contributions to the Developer Guide (DG):**
* Adjusted the diagrams in Storage and Model
* Added details about UserDataStorage

**Contributions to team-based tasks:**
* Created issues and assigned them to teammates for v1.3 and v1.4
* Overall IC for code quality.
    * Read and ensured code quality standards were met.
* Kept track of code deadlines

**Contributions beyond the project team:**
* Took part in Bug CATcher stress test
* Took part in PE-D
