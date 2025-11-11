[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

# About the project 📝

This project is an API REST developed in Spring Boot that provides a Notes App.
It allows users to create, edit and delete <b>notes</b>, as well as manage their categories. 
The application uses a MySQL database for data storage and is hosted on the Heroku platform.

<b>Main features: </b>📌

<ul>
    <li>Note creation and administration.</li>
    <li>Secure connection to the database in the cloud.</li>
    <li>Deployment and execution on Heroku.</li>
</ul>

## Built With 🔐

The stack use to develop and deploy this project is:

* Spring Boot 3
* Gradle 8
* Spring Web
* Spring Data JPA
* MySql Driver
* MySQL 8+
* Flyway Migration
* Lombok
* MapStruct 1.5
* OpenAPI 2 (Swagger)
* Heroku

## Getting Started 🚀

Clone the repo:
```bash
git clone https://github.com/ElianaPranzetti/githubJustLearningMX-ensolvers-challenge.git
```

### Pre-requisites 📋

* Go to dir `/backend`
  ```bash
    cd backend
    ```
* Build and compile the project with Gradle 8.
  ```groovy
  gradle build
  ```
* Environment variables required (example values) to run the application locally in the file `application.properties`:
  ```properties
  DB_URL=jdbc:mysql://localhost:3306/
  DB_NAME=noteDB
  DB_USERNAME=admin
  DB_PASSWORD=my_pa$$word
  ```

### Usage ⏯
1. Create a database in MySQL with the name `noteDB`.
2. Run the application from the IDE.
3. Open the browser and go to the URL `http://localhost:8080/swagger-ui/index.html` or use the demo on Heroku shown below on section Demo 👨‍💻.
4. Use the Swagger UI to test the API.
5. Create a note with the endpoint `POST /notes` and the following body:
    ```json
    {
    "title": "My note 1",
    "description": "This is my first note",
    "lastEdited": "2023-08-10T23:46:59.811Z",
    "state": "ACTIVE",
    "deleted": false
    }
    ```
6. Update a note with the endpoint `PUT /notes` and the following body:
    ```json
    {
    "id": 1, 
    "title": "My note 1 updated",
    "description": "This first note was updated",
    "lastEdited": "2023-08-10T23:46:59.811Z",
    "state": "ACTIVE",
    "deleted": false
    }
    ```
10. Archived a note with the endpoint `PUT /notes/archived/{id}`. The `id` path parameter is required.
11. Unarchived a note with the endpoint `PUT /notes/unarchived/{id}`. The `id` path parameter is required.
12. Delete a note (logical) with the endpoint `DELETE /notes/{id}`. The `id` path parameter is required.
13. Get a specific note with the endpoint `GET /notes/{id}`. The `id` path parameter is required.
  ```json
  {
  "id": 5,
  "title": "Mi nota con tags renovado 2",
  "description": "Esta nota con tags fue modificada, agregandosele 2 tags mas",
  "lastEdited": "2023-08-10T17:38:32.680+00:00",
  "state": "ACTIVE",
  "deleted": false,
  "categories": [
    {
      "id": 1,
      "name": "office"
    },
    {
      "id": 2,
      "name": "home"
    }
  ]
  }
  ```
11. Get all the notes with the endpoint `GET /notes`.
12. Create one o more new categories/tags with the endpoint `POST /categories`.
  ```json
  {
      "note_id": 1,
      "categories": 
    [
        {
          "name": "home"
        },
        {
          "name": "office"
        }
    ]
  }
  ```
13. Get all the categories with the endpoint `GET /categories`.

## Demo 👨‍💻
                                                  
- **Application Demo** on Heroku: [notes-app-api](https://notes-app-api-1d668731c17b.herokuapp.com/swagger-ui/index.html)

## Frontend 🖥️

Heroku: [ny-notes-app](https://my-notes-app-1673bce910ea.herokuapp.com/)

## Author ✒️

* **Hiram Chávez** - [JustLearningMX](https://github.com/JustLearningMX)

## Contact 📬

* Twitter: [@hiram_ch](https://twitter.com/hiram_ch)
* Email: [hiramchavezlopez@gmail.com](https://twitter.com/hiram_ch)
* LinkedIn: [Hiram Chávez](https://www.linkedin.com/in/hiram-chavez-24126831/)
* Website: [Hiram Chávez](https://hiramchavez.com)
* GitHub: [JustLearningMX](https://github.com/JustLearningMX)

---

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://choosealicense.com/licenses/mit/
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/hiram-chavez-24126831/
[product-screenshot]: /src/main/resources/static/img/caratula-todolist-app.png