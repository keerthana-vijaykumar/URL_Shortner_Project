# Project: URL Shortener Web Application (Hobby Project)
# Overview:
This project is a web-based URL shortening service built using Spring Boot that allows users to input long URLs and receive a compact, shortened version. Similar to platforms like Bitly or TinyURL, the primary goal was to improve URL management and sharing efficiency.
# Key Features:

# URL Conversion:
Users can submit long URLs via a simple web interface. The backend generates a unique short key (e.g., abc123) mapped to the original URL and stores this mapping in a database.

# Redirection Logic:
When a user accesses the shortened URL (e.g., myapp.com/abc123), the app redirects them to the original long URL using a fast lookup mechanism.

# Persistent Storage:
Utilized an H2 in-memory database for simplicity during development, but the system is designed to be easily extendable to use databases like MySQL or PostgreSQL for production.

# Spring Boot and REST APIs:
Built using Spring Boot for rapid development and ease of dependency management. Exposed RESTful endpoints for both URL creation and retrieval. Followed MVC pattern for code organization.

# Validation & Error Handling:
Implemented input validation for proper URL format and handled edge cases like duplicate or invalid short keys with user-friendly error responses.
