# Tracker
[![Build Status](https://travis-ci.org/o-gen18/job4j_tracker.svg?branch=master)](https://travis-ci.org/o-gen18/job4j_tracker)
[![codecov](https://codecov.io/gh/o-gen18/job4j_tracker/branch/master/graph/badge.svg)](https://codecov.io/gh/o-gen18/job4j_tracker)

This is a simple console application called "Tracker".

### Functionality
- Creating a string-valued item with the assigned id;
- Storing data in RAM or database;
- Editing or deleting an item;
- Validation against irrelevant input;

### Technologies used
- Simple architecture based upon OOP principles;
- PostgreSQL database for storing data;
- Liquibase for integration testing;

### Demonstration

First, choose the way to store data. 
Then, you can create a new Item inputting the corresponding menu point.

![img](./img/enterItem.png)

Show all items.

![img](./img/showAll.png)

Validation when trying to input irrelevant option.

![img](./img/editingAndValidation.png)

Finding the item by its name.

![img](./img/findByName.png)

Successful editing of the existing item.

![img](./img/successfulEditing.png)

The database storage option. 

![img](./img/dbStorageDemo.png)
