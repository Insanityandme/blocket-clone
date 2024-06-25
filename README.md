<div align="center">
	
# Plocket

[Install](#install) • [Animations](#animations) • [Screenshots](#screenshots)
</div>


### Table of Contents
- [Introduction](#introduction)
- [Installation](#install)
	- [Backend](#backend)
	- [Frontend](#frontend)
- [Contact](#contact)

# Introduction
Plocket is a web application that allows users to buy and sell a wide range of products. The design of the application is heavily inspired by blocket.se (it’s similar to Facebook's marketplace but for Swedish users). The application is separated into two parts, a frontend and a backend.

Here is a short demo of the application, it does not however show all features but most of them: https://youtu.be/csRdmTOqPbI

Features:

- Login/Register an account
- Sell a product by creating a listing with information & images of a product you want to sell
- Watch current ads the user has published
- Subscribe to categories and get notified via your inbox when a new item in that category is posted
- Add products to your cart
- Buy products (without payment logic implemented)
- Search for products using categories, price, condition, and name
- See order history

# Frontend

## Prequisities

NodeJS: [NodeJS](https://nodejs.org/en)

Marketplace-backend: This will not be functional if you haven't done the instructions for the [backend](https://github.com/Insanityandme/marketplace-backend)

## Installation

0. `git clone https://github.com/Insanityandme/marketplace-frontend.git`
1. `cd marketplace-frontend`
2. `npm install`
3. `npm run dev`
4. Your server should now be running at http://localhost:3000 and should look something like this:

![image](https://github.com/Insanityandme/marketplace-frontend/assets/1380257/2db7ab0d-73af-412e-8076-05b77a93b782)


# Backend 

Marketplace-backend is the backend of our application. It is a web application written in Java using the Spring Boot framework. The backend is a RESTful API which utilizes different HTTP methods for communication. Server sent events are also used, for sending messages from the backend to the frontend. 

This application acts as our web server where we have defined endpoints for our frontend to make use of. 
PostgreSQL is used as the database and digitalocean.com to host content created by users. 
Railway is used to host our database, which provides a containerized environment application.

## Prerequisities 
- Java 21+
- PostgreSQL: [PostgreSQL](https://www.postgresql.org/)
- .env with the following environment variables set:
  - POSTGRES_DB
  - POSTGRES_USER
  - POSTGRES_PASSWORD
  - IMAGE_UPLOAD_DIRECTORY
  - IMAGE_HOST_URL
- (Optional .env variables):
    - MAX_UPLOAD_SIZE_BYTES=10000000
    - SPRING_PROFILES_ACTIVE=production
    - NIXPACKS_JDK_VERSION=21
- Docker for running tests (OPTIONAL): [Docker](https://www.docker.com/)
  
## Installation (TERMINAL)
0. ```git clone https://github.com/Insanityandme/marketplace-backend.git```
1. ```cd marketplace-backend```

WINDOWS:
1. ```gradlew build -x test```
2. ```gradlew bootRun```

LINUX: 
1. ```./gradlew build -x test```
2. ```./gradlew bootRun```

It should look something like this: ![image](https://github.com/Insanityandme/marketplace-backend/assets/1380257/a8389660-41be-4928-ba69-f040fb17d574)

## Installation (IntelliJ)
0. ```git clone https://github.com/Insanityandme/marketplace-backend.git```
1. Open the project in IntelliJ
2. Let IntelliJ install all required dependencies
3. Press the play button to run the application

It should look something like this: ![image](https://github.com/Insanityandme/marketplace-backend/assets/1380257/5d74f24c-6d7e-444f-9090-50d189b0c7eb)

---
# Contact
You are welcome to contact me at bengtegardbook@gmail.com if you have any questions on how to setup this environment.
There might be some mistakes in our installation guide and I apologize for that if that's the case.
